package com.elsys.surveyio.survey;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @GetMapping("/survey")
    public List<Survey> findAll(){
        return  surveyService.findAll();
    }

    @GetMapping("/survey/{id}")
    public ResponseEntity<Survey> findOneById(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(surveyService.findOneById(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(Map.of("message", "Survey not found"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/survey")
    public Survey create(@RequestBody CreateSurveyDto surveyDto){
        return surveyService.create(surveyDto);
    }
}
