package com.api.kakaochatbot.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Map;

public class Utility {
    static public String GetHttp(String url, String urlPath, Map<String, String> params) throws IOException {
        URL _url = null;
        HttpURLConnection con = null;
        BufferedReader in = null;
        String param = "";
        StringBuffer response = new StringBuffer();
        int status = -1;

        try{
            param = GetParamsString(params);

            _url = new URL(url + urlPath + "?" + param);
            con = (HttpURLConnection) _url.openConnection();
            con.setRequestMethod("GET");

            //데이터를 OutputStream으로 넘겨 주겠다는 설정
            //con.setDoOutput(true);

            //데이터 전송
            //DataOutputStream out = new DataOutputStream(con.getOutputStream());
            //out.writeBytes(param);
            //out.flush();

            //Setting Request Headers
            //con.setRequestProperty("Content-Type", "application/json");
            //con.setRequestProperty("Content-Type", "application/x-www-form-urlencode");

            //Configuring Timeouts
            //con.setConnectTimeout(5000);
            //con.setReadTimeout(5000);

            status = con.getResponseCode();
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

        }catch (Exception e){

        }finally {
            //out.close();
            if(null != in) in.close();
            if(null != con) con.disconnect();
        }

        return response.toString();
    }

    public static String GetParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }

    /*
     * Utility.JsonSerialize(listCard);
     */
    static public String JsonSerialize(Object value)  throws JsonProcessingException
    {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(value);
    }

    /*
     * Utility.JsonDeSerialize(rankMovie, ListCardReponse.class);
     */
    static public <T> T JsonDeSerialize(String value, Class<T> obj)  throws JsonProcessingException
    {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(value, obj);
    }

    static public Object GetDecimalFormat(String value, String format){
        DecimalFormat decimalFormat = new DecimalFormat(format);

        return decimalFormat.format(Integer.parseInt(value));
    }
}
