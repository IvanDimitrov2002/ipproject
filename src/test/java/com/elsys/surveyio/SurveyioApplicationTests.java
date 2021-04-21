package com.elsys.surveyio;

import com.elsys.surveyio.answer.Answer;
import com.elsys.surveyio.answer.AnswerRepository;
import com.elsys.surveyio.jwt.JwtRequest;
import com.elsys.surveyio.question.CreateQuestionDto;
import com.elsys.surveyio.survey.CreateSurveyDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SurveyioApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SurveyioApplicationTests {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private AnswerRepository answerRepository;

	@Test
	void testAuthentication() throws Exception {
		assertRegister("ivan", "root", true);
		assertRegister("daniel", "root", true);
		assertLogin("daniel", "root", true);
		assertLogin("ivan", "root", true);

		try{
			assertRegister("ivan", "root", false);
			assertRegister("daniel", "root", false);
			assertLogin("daniel", "toor", false);
			assertLogin("ivan", "toor", false);
		} catch (Exception ignored){
		}
	}

	@Test
	void testUserUpdate() throws Exception {
		assertUpdateUser("ivan", "root", "daniel");
		assertUpdateUser("petur", "root", "gabriel");
		assertUpdateUser("svetlin", "root", "koco");
	}

	@Test
	void testSurveyUpdate() throws  Exception {
		ObjectMapper om = new ObjectMapper();

		String userAsString = mvc.perform(post("/user").content(om.writeValueAsBytes(new JwtRequest("ivan", "root")))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		String tokenAsString = mvc.perform(post("/login").content(om.writeValueAsBytes(new JwtRequest("ivan", "root")))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		String token = om.readTree(tokenAsString).get("token").textValue();
		Long userId = om.readTree(userAsString).get("id").asLong();

		assertUpdateSurvey("survey1", "survey2", userId, token);
		assertUpdateSurvey("survey3", "survey4", userId, token);
	}

	@Test
	void testVote() throws  Exception {
		ObjectMapper om = new ObjectMapper();

		List<String> stringAnswers = new ArrayList<>(Arrays.asList(
				"Maybe",
				"Sure",
				"Yes",
				"No",
				"K"
		));
		List<Long> answersIds = new ArrayList<>();
		for(String answer : stringAnswers){
			Answer newAnswer = new Answer();
			newAnswer.setAnswer(answer);
			Answer savedAnswer = answerRepository.save(newAnswer);
			answersIds.add(savedAnswer.getId());
		}

		mvc.perform(post("/vote").content(om.writeValueAsBytes(answersIds))
				.contentType(MediaType.APPLICATION_JSON));

		List<Integer> answers = answerRepository.findAll().stream().map(Answer::getVotes).collect(Collectors.toList());
		Assert.isTrue(!answers.contains(0));

	}

	private void assertRegister(String username, String password, boolean isSuccess)
			throws Exception {
		ObjectMapper om = new ObjectMapper();

		if (!isSuccess) {
			mvc.perform(post("/user").content(om.writeValueAsBytes(new JwtRequest(username, password)))
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isInternalServerError());
		} else {
			mvc.perform(post("/user").content(om.writeValueAsBytes(new JwtRequest(username, password)))
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		}
	}

	private void assertLogin(String username, String password, boolean isSuccess)
			throws Exception {
		ObjectMapper om = new ObjectMapper();

		if (!isSuccess) {
			mvc.perform(post("/login").content(om.writeValueAsBytes(new JwtRequest(username, password)))
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isUnauthorized());
		} else {
			mvc.perform(post("/login").content(om.writeValueAsBytes(new JwtRequest(username, password)))
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		}
	}

	private void assertUpdateUser(String username, String password, String newUsername)
			throws Exception {
		ObjectMapper om = new ObjectMapper();

		String userAsString = mvc.perform(post("/user").content(om.writeValueAsBytes(new JwtRequest(username, password)))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		String tokenAsString = mvc.perform(post("/login").content(om.writeValueAsBytes(new JwtRequest(username, password)))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		String token = om.readTree(tokenAsString).get("token").textValue();
		Long userId = om.readTree(userAsString).get("id").asLong();

		mvc.perform(put("/user/" + userId).content(om.writeValueAsBytes(Map.of("username", newUsername)))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());
		mvc.perform(put("/user/" + userId).content(om.writeValueAsBytes(Map.of("username", newUsername)))
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.username", is(newUsername)));

	}

	private void assertUpdateSurvey(String name, String newName, Long userId, String token)
			throws Exception {
		ObjectMapper om = new ObjectMapper();

		String surveyAsString = mvc.perform(post("/survey").content(om.writeValueAsBytes(Map.of("name", name)))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		Long surveyId = om.readTree(surveyAsString).get("id").asLong();


		mvc.perform(put("/survey/" + surveyId).content(om.writeValueAsBytes(Map.of("name", newName, "userId", userId)))
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is(newName)));
	}
}