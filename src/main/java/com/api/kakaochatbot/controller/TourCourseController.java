package com.api.kakaochatbot.controller;

import com.api.kakaochatbot.model.chatbot.ListCardRequest;
import com.api.kakaochatbot.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/*")
@Validated
public class TourCourseController {
    @Autowired
    private TourService tourService;

    @PostMapping(value = "/tour/coursetype", produces = "application/json; charset=UTF8")
    public ResponseEntity RecommendedCourse(@RequestBody ListCardRequest request)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        String response = tourService.CourseTypeSearch(request);

        return ResponseEntity.ok().headers(headers).body(response);
    }

    @PostMapping(value = "/tour/areabase", produces = "application/json; charset=UTF8")
    public ResponseEntity AreaBaseSearch(@RequestBody ListCardRequest request)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        String response = tourService.AreaBaseSearch(request);

        return ResponseEntity.ok().headers(headers).body(response);
    }
}

