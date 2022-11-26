package com.api.opendata.service;

import com.api.opendata.common.util.MovieEnum;
import com.api.opendata.common.util.Utility;
import com.api.opendata.model.chatbot.ListCardReponse;
import com.api.opendata.model.chatbot.ListCardRequest;
import com.api.opendata.process.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FoodRecipeService {

    public String Search()
    {
        String response = "";

        try{

        }catch (Exception e){
            response = e.getMessage();
        }

        return response;
    }
}
