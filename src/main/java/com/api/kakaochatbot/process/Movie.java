package com.api.kakaochatbot.process;


import com.api.kakaochatbot.model.chatbot.ListCardReponse;
import org.springframework.stereotype.Service;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Service
public class Movie {
    static String _movie_url = "https://movie.naver.com";
    static String _current_movie_path = "/movie/running/current.naver"; // 네이버영화 현재 상영작
    static String _schdule_movie_path = "/movie/running/premovie.naver"; // 네이버영화 개봉 예정작

    public ArrayList<ListCardReponse.ListCard> CurrentSearch(String type, String typeKR) throws JsonProcessingException, IOException
    {
        ArrayList<ListCardReponse.ListCard> listCards = new ArrayList<>();
        ListCardReponse.ListCard listCard = null;
        ListCardReponse.Header header = new ListCardReponse.Header();
        HashMap<Integer, ArrayList<ListCardReponse.Item>> itemsMap = new HashMap<>();
        ArrayList<ListCardReponse.Item> items = null;

        int movieCount = 0;

        String param = GetParam(type);

        Connection conn = Jsoup.connect(_movie_url + _current_movie_path + "?" + param);
        Document document = conn.get();

        Element movieList = document.selectFirst(".lst_detail_t1");
        Element[] filterMovieList = Arrays.copyOfRange(movieList.children().toArray(new Element[10]), 0, 10);

        for (Element li : filterMovieList) {
            ListCardReponse.Item item = new ListCardReponse.Item();
            ListCardReponse.Link link = new ListCardReponse.Link();

            String movieUrl = li.select(".thumb a").attr("href"); //영화URL
            String imgUrl = li.select(".thumb a img").attr("src"); //이미지URL
            String title = li.select(".lst_dsc .tit a").text(); // 타이틀
            String giveGrades = li.select(".lst_dsc .star .tit_t1").next().select(".num").text(); //네이즌 평점
            String  genre = li.select(".lst_dsc .info_txt1 .tit_t1").next().select(".link_txt a").text(); //개요(장르)
            Element releaseDateElement = li.select(".lst_dsc .info_txt1 .tit_t1").next().first(); //개봉일

            int lastIndex = releaseDateElement.html().indexOf("개봉");
            int startIndex = releaseDateElement.html().lastIndexOf(">", lastIndex);
            String releaseDate = releaseDateElement.html().substring(startIndex+2, lastIndex+2);

            item.setTitle(title);
            //item.setDescription("장르 : " + genre.replace(" ", ",") + "\n" + releaseDate + " | 평점 : " + giveGrades);
            item.setDescription(releaseDate + " / 평점 : " + giveGrades);
            item.setImageUrl(imgUrl);

            link.setWeb(_movie_url + movieUrl);
            item.setLink(link);

            /*System.out.println(title + " | " + genre + " | " + releaseDate);
            System.out.println("평점 : " + giveGrades + "\t" + "예매율 : " + 0 + "%");*/

            int key = movieCount / 5;
            if(itemsMap.containsKey(key)){
                itemsMap.get(key).add(item);
            }else{
                items = new ArrayList<>();
                items.add(item);

                itemsMap.put(key, items);
            }

            movieCount++;
        }

        for(int key : itemsMap.keySet()){
            listCard = new ListCardReponse.ListCard();

            header.setTitle("현재 " + typeKR);

            listCard.setHeader(header);
            listCard.setItems(itemsMap.get(key));

            listCards.add(listCard);
        }

        return listCards;
    }

    public ArrayList<ListCardReponse.ListCard> PremiereSearch() throws JsonProcessingException, IOException{
        ArrayList<ListCardReponse.ListCard> listCards = new ArrayList<>();
        ListCardReponse.ListCard listCard = null;
        ListCardReponse.Header header = new ListCardReponse.Header();
        HashMap<Integer, ArrayList<ListCardReponse.Item>> itemsMap = new HashMap<>();
        ArrayList<ListCardReponse.Item> items = null;

        int movieCount = 0;

        Connection conn = Jsoup.connect(_movie_url + _schdule_movie_path + "?" + "order=open");
        Document document = conn.get();

        Element[] movieList = Arrays.copyOfRange(document.select(".lst_wrap .lst_detail_t1 li").toArray(new Element[10]), 1, 11);

        for(Element movie : movieList){
            ListCardReponse.Item item = new ListCardReponse.Item();
            ListCardReponse.Link link = new ListCardReponse.Link();

            String movieUrl = movie.select(".thumb a").attr("href"); //영화URL
            String imgUrl = movie.select(".thumb a img").attr("src"); //이미지URL
            String title = movie.select(".tit a").text();

            Element releaseDateElement = movie.select(".lst_dsc .info_txt1 .tit_t1").next().first(); //개봉일
            int lastIndex = releaseDateElement.html().indexOf("개봉");
            int startIndex = releaseDateElement.html().lastIndexOf(">", lastIndex);
            String releaseDate = releaseDateElement.html().substring(startIndex+2, lastIndex+2);

            item.setTitle(title);
            item.setDescription(releaseDate);
            item.setImageUrl(imgUrl);

            link.setWeb(_movie_url + movieUrl);
            item.setLink(link);

            /*System.out.println(title + " | " + releaseDate);
            System.out.println("평점 : " + giveGrades + "\t" + "예매율 : " + 0 + "%");*/

            int key = movieCount / 5;
            if(itemsMap.containsKey(key)){
                itemsMap.get(key).add(item);
            }else{
                items = new ArrayList<>();
                items.add(item);

                itemsMap.put(key, items);
            }

            movieCount++;
        }

        for(int key : itemsMap.keySet()){
            listCard = new ListCardReponse.ListCard();

            header.setTitle("개봉 예정작");

            listCard.setHeader(header);
            listCard.setItems(itemsMap.get(key));

            listCards.add(listCard);
        }

        return listCards;
    }

    public String GetParam(String getOrder){
        StringBuffer param = new StringBuffer();

        param.append("view=list");
        param.append("&tab=normal");
        param.append("&order=" + getOrder);

        return param.toString();
    }
}
