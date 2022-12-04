package com.api.kakaochatbot.service;

import com.api.kakaochatbot.common.util.MovieEnum;
import com.api.kakaochatbot.common.util.Utility;
import com.api.kakaochatbot.model.chatbot.ListCardReponse;
import com.api.kakaochatbot.model.chatbot.ListCardRequest;
import com.api.kakaochatbot.process.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
