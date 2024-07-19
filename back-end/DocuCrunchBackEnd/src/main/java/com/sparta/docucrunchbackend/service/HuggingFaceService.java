package com.sparta.docucrunchbackend.service;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HuggingFaceService {

    @Value("${huggingface.api.token}")
    private String apiToken;

    private static final String URL = "https://api-inference.huggingface.co/models/facebook/bart-large-cnn";

    public String summarizeMeeting(String inputText) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(URL);

            // Set headers
            request.addHeader("Authorization", "Bearer " + apiToken);
            request.addHeader("Content-Type", "application/json");

            // Set input data
            StringEntity inputEntity = new StringEntity("{\"inputs\": \"" + inputText + "\"}");
            request.setEntity(inputEntity);

            // Send the request
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    HttpEntity responseEntity = response.getEntity();
                    return EntityUtils.toString(responseEntity);
                } else {
                    throw new RuntimeException("Request failed with status code: " + response.getStatusLine().getStatusCode());
                }
            }
        }
    }


}

