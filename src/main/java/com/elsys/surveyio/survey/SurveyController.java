package com.elsys.surveyio.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @GetMapping("/survey")
    public List<Survey> findAll(){
        List<Survey> surveys = surveyService.findAll();
        for(Survey survey : surveys){
            survey.setPrivateId("");
        }
        return  surveys;
    }

    @GetMapping("/survey/{id}")
    public ResponseEntity<Survey> findOneById(@PathVariable("id") Long id){
        try {
            Survey survey = surveyService.findOneById(id);
            survey.setPrivateId("");
            return new ResponseEntity<>(survey, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(Map.of("message", "Survey not found"), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/survey")
    public Survey create(@RequestBody CreateSurveyDto surveyDto, @RequestParam("image") MultipartFile multipartFile){
        return surveyService.create(surveyDto);
    }

    @PutMapping("/survey/{id}")
    public ResponseEntity<Survey> update(@PathVariable("id") Long id, @RequestBody CreateSurveyDto surveyDto){
        try {
            return new ResponseEntity<>(surveyService.update(id, surveyDto), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(Map.of("message", "Survey not found"), HttpStatus.NOT_FOUND);
        }
    }
}
