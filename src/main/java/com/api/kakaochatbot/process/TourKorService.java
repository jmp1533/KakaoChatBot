package com.api.kakaochatbot.process;


import com.api.kakaochatbot.common.util.Utility;
import com.api.kakaochatbot.model.chatbot.ListCardReponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Service
public class TourKorService {
    static String _tour_url = "http://apis.data.go.kr/B551011/KorService";
    static String _areaBase_path = "/areaBasedList";
    static String _serviceKey = "w+aloIJxj+WOIOgQzb6aDZi7OSjvOlKcTKPCbZYxUDW/h1/ngJMTwd6SqoWJe4dwvc19Gw6ryg8PEVfc+CvM9g==";

    public String AreaBaseSearch() throws JsonProcessingException, IOException
    {
        String response = Utility.GetHttp(_tour_url, _areaBase_path, GetParam());

        return response;
    }

    public MultiValueMap<String, String> GetParam(){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("numOfRows", "10");
        params.add("pageNo", "1");
        params.add("MobileOS", "ETC");
        params.add("MobileApp", "ChatBot");
        params.add("ServiceKey", _serviceKey);
        params.add("listYN", "Y");
        params.add("arrange", "Q");
        params.add("contentTypeId", "25");
        params.add("areaCode", "");
        params.add("sigunguCode", "");
        params.add("cat1", "C01");
        params.add("cat2", "");
        params.add("cat3", "");
        params.add("_type", "json");

        return params;
    }
}
