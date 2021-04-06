package com.elsys.surveyio.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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
}
