package com.api.kakaochatbot.controller;

import com.api.kakaochatbot.service.FoodRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/recipe/*")
@Validated
public class RecipeController {
    @Autowired
    private FoodRecipeService foodRecipeService;

    @PostMapping(value = "/food", produces = "application/json; charset=UTF8")
    public ResponseEntity Search()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        String response = foodRecipeService.Search();

        return ResponseEntity.ok().headers(headers).body(response);
    }
}

