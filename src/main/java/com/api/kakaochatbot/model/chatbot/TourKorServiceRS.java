package com.api.kakaochatbot.model.chatbot;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class TourKorServiceRS {

    @Getter
    @Setter
    private Response response;

    @Getter
    @Setter
    public static class Response {
        private Body body;
        private Header header;
    }

    @Getter
    @Setter
    public static class Body {
        private int totalcount;
        private int pageno;
        private int numofrows;
        private Items items;
    }

    @Getter
    @Setter
    public static class Items {
        private List<Item> item;
    }

    @Getter
    @Setter
    public static class Item {
        private String zipcode;
        private String title;
        private String tel;
        private String sigungucode;
        private int readcount;
        private String modifiedtime;
        private String mlevel;
        private String mapy;
        private String mapx;
        private String firstimage2;
        private String firstimage;
        private String createdtime;
        private String contenttypeid;
        private String contentid;
        private String cat3;
        private String cat2;
        private String cat1;
        private String booktour;
        private String areacode;
        private String addr2;
        private String addr1;
    }

    @Getter
    @Setter
    public static class Header {
        private String resultmsg;
        private String resultcode;
    }
}
