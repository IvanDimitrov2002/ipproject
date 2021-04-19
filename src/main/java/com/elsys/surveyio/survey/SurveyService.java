package com.elsys.surveyio.survey;

import com.elsys.surveyio.user.User;
import com.elsys.surveyio.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Service
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Survey> findAll(){
        return surveyRepository.findAll();
    }

    public Survey findOneById(Long id){
        return surveyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public Survey findOneByPrivateId(String privateId){
        return surveyRepository.findByPrivateId(privateId).orElseThrow(() -> new EntityNotFoundException(privateId));
    }

    public Survey create(CreateSurveyDto surveyDto){
        ModelMapper modelMapper = new ModelMapper();
        Survey newSurvey = modelMapper.map(surveyDto, Survey.class);
        if(surveyDto.getUserId() != null){
            userRepository.findById(surveyDto.getUserId()).ifPresent(newSurvey::setUser);
        }
        return surveyRepository.save(newSurvey);
    }

    public Survey update(Long id, CreateSurveyDto surveyDto){
        ModelMapper modelMapper = new ModelMapper();
        Survey survey = surveyRepository.getOne(id);
        modelMapper.map(surveyDto, survey);
        return surveyRepository.save(survey);
    }
}
