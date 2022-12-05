package com.api.kakaochatbot.process;

import com.api.kakaochatbot.common.util.Utility;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class TourKor {
    static String _tour_url = "http://apis.data.go.kr/B551011/KorService";
    static String _areaBase_path = "/areaBasedList";
    static String _serviceKey = "w+aloIJxj+WOIOgQzb6aDZi7OSjvOlKcTKPCbZYxUDW/h1/ngJMTwd6SqoWJe4dwvc19Gw6ryg8PEVfc+CvM9g==";

    public String AreaBaseSearch() throws JsonProcessingException, IOException
    {
        String response = Utility.GetHttp(_tour_url, _areaBase_path, GetParam());

        return response;
    }

    public Map<String, String> GetParam(){
        Map<String, String> params = new HashMap<>();

        params.put("numOfRows", "10");
        params.put("pageNo", "1");
        params.put("MobileOS", "ETC");
        params.put("MobileApp", "ChatBot");
        params.put("ServiceKey", _serviceKey);
        params.put("listYN", "Y");
        params.put("arrange", "Q");
        params.put("contentTypeId", "25");
        params.put("areaCode", "");
        params.put("sigunguCode", "");
        params.put("cat1", "C01");
        params.put("cat2", "");
        params.put("cat3", "");
        params.put("_type", "json");

        return params;
    }
}
