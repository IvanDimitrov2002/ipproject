package com.elsys.surveyio.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> findAll(){
        return answerRepository.findAll();
    }

    public Answer findOneById(Long id) {
        return answerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public void vote(List<Long> answersIds){
        answersIds.forEach(id -> {
            Answer answer = answerRepository.getOne(id);
            answer.setVotes(answer.getVotes() + 1);
            answerRepository.save(answer);
        });
    }
}
