package com.api.kakaochatbot.service;

import com.api.kakaochatbot.common.util.CourseType;
import com.api.kakaochatbot.common.util.MovieEnum;
import com.api.kakaochatbot.common.util.Utility;
import com.api.kakaochatbot.model.chatbot.ListCardReponse;
import com.api.kakaochatbot.model.chatbot.ListCardRequest;
import com.api.kakaochatbot.process.Movie;
import com.api.kakaochatbot.process.TourKorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TourService {
    @Autowired
    private TourKorService tourKorService;

    public String CourseTypeSearch(ListCardRequest request)
    {
        ListCardReponse listCardRS = new ListCardReponse();
        ListCardReponse.Template Template = new ListCardReponse.Template();
        ArrayList<ListCardReponse.QuickReplies> quickReplies = new ArrayList<>();

        String type = "";
        String typeKR = "";
        String response = "";

        try{
            CourseType[] courseTypes = CourseType.CreateCourseCode();

            for(CourseType courseType : courseTypes){
                quickReplies.add(GetQuickReplies(courseType.GetCodeName(), courseType.GetCodeName()));
            }

            Template.setQuickReplies(quickReplies);

            listCardRS.setVersion("2.0");
            listCardRS.setTemplate(Template);

            response = Utility.JsonSerialize(listCardRS);

        }catch (Exception e){
            response = e.getMessage();
        }

        return response;
    }

    public String AreaBaseSearch(ListCardRequest request)
    {
        ListCardReponse listCardRS = new ListCardReponse();
        ListCardReponse.Template Template = new ListCardReponse.Template();
        ArrayList<ListCardReponse.QuickReplies> quickReplies = new ArrayList<>();

        String type = "";
        String typeKR = "";
        String response = "";

        try{
            response = tourKorService.AreaBaseSearch();

        }catch (Exception e){
            response = e.getMessage();
        }

        return response;
    }


    public ListCardReponse.QuickReplies GetQuickReplies(String label, String message){
        ListCardReponse.QuickReplies quickReplies = new ListCardReponse.QuickReplies();

        quickReplies.setLabel(label);
        quickReplies.setAction("message");
        quickReplies.setMessageText(message);

        return quickReplies;
    }
}
