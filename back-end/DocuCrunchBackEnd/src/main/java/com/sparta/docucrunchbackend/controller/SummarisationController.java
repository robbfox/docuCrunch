
package com.sparta.docucrunchbackend.controller;

import com.sparta.docucrunchbackend.service.Ai21StudioService;
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
    private final Ai21StudioService ai21StudioService;

    @Autowired
    public SummarisationController(HuggingFaceService huggingFaceService, Ai21StudioService ai21StudioService){
        this.huggingFaceService = huggingFaceService;
        this.ai21StudioService = ai21StudioService;
    }

    @PostMapping
    public ResponseEntity<String>  summarise(@RequestBody Map<String, String> summaryRequest) throws IOException {

        String summaryText = summaryRequest.get("text");
        String summaryType = summaryRequest.get("type");

        String summaryOutput;
        if(summaryType.equalsIgnoreCase("articles")){
            summaryOutput = ai21StudioService.summariseText(summaryText);
        }else if(summaryType.equalsIgnoreCase("minutes")){
            summaryOutput = huggingFaceService.summariseMinutes(summaryText);
        }else{
            return ResponseEntity.badRequest().body("Invalid summary type");
        }
        return ResponseEntity.ok(summaryOutput);
    }


}
