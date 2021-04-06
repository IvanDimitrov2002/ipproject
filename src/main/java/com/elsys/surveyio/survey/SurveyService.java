package com.elsys.surveyio.survey;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    public List<Survey> findAll(){
        return surveyRepository.findAll();
    }

    public Survey findOneById(Long id){
        return surveyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public Survey create(CreateSurveyDto surveyDto){
        ModelMapper modelMapper = new ModelMapper();
        Survey newSurvey = modelMapper.map(surveyDto, Survey.class);
        return surveyRepository.save(newSurvey);
    }
}
