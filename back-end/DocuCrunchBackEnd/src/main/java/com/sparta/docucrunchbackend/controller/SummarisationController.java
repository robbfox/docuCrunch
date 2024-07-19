package com.sparta.docucrunchbackend.controller;

import com.sparta.docucrunchbackend.service.HuggingFaceService;
import com.sparta.docucrunchbackend.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/summarise")
public class SummarisationController {
  
     private final HuggingFaceService huggingFaceService;
     private final testService testService;

     @Autowired
     public SummarisationController(HuggingFaceService huggingFaceService, testService testService){
        this.huggingFaceService = huggingFaceService;
        this.testService = testService;
    }

    @PostMapping
    public String summarise(@RequestBody String summaryRequest) throws IOException {

        return testService.summariseText(summaryRequest);
    }


}
