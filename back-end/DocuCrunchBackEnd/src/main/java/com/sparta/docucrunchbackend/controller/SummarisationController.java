package com.sparta.docucrunchbackend.controller;

import com.sparta.docucrunchbackend.service.HuggingFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

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
    public ResponseEntity<String>  summarise(@RequestBody Map<String, String> summaryRequest) throws IOException {

         String summaryText = summaryRequest.get("text");
         String summaryType = summaryRequest.get("type");

         String summaryOutput;
         if(summaryType.equalsIgnoreCase("articles")){
             summaryOutput = HuggingFaceService.summariseText(summaryText);
         }else if(summaryType.equalsIgnoreCase("minutes")){
             summaryOutput = HuggingFaceService.summariseMinutes(summaryText);
         }else{
             return ResponseEntity.badRequest().body("Invalid summary type");
         }
       return ResponseEntity.ok(summaryOutput);
    }


}
