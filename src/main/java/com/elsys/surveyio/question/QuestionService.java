package com.elsys.surveyio.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> finaAll(){
        return questionRepository.findAll();
    }

    public Question findOneById(Long id){
        return questionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }
}
