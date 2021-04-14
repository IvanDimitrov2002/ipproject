package com.elsys.surveyio.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @PostMapping("/vote")
    public ResponseEntity<Map<String, String>> vote(@RequestBody List<Long> answersIds){
        try {
            answerService.vote(answersIds);
            return new ResponseEntity<>(Map.of("message", "Vote successfully executed!"), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(Map.of("message", "Error."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
