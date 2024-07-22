package com.sparta.docucrunchbackend.controller;

import com.sparta.docucrunchbackend.service.HuggingFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/summarise")
public class SummarisationController {
  
     private final HuggingFaceService huggingFaceService;
     private final HuggingFaceService HuggingFaceService;

     @Autowired
     public SummarisationController(HuggingFaceService huggingFaceService, HuggingFaceService HuggingFaceService){
        this.huggingFaceService = huggingFaceService;
        this.HuggingFaceService = HuggingFaceService;
    }

    @PostMapping
    public String summarise(@RequestBody String summaryRequest) throws IOException {
        return HuggingFaceService.summariseText(summaryRequest);
    }


}
