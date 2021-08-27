package com.webquiz.webquiz.Business;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class Feedback {

    @Bean
    public Map<String,Object> goodAnswer(){
        Map<String,Object> goodAnswer = new LinkedHashMap<>();
        goodAnswer.put("success",true);
        goodAnswer.put("feedback","Congratulations, you're right!");
        return goodAnswer;
    }

    @Bean
    public Map<String,Object> wrongAnswer(){
        Map<String,Object> wrongAnswer = new LinkedHashMap<>();
        wrongAnswer.put("success",false);
        wrongAnswer.put("feedback","Wrong answer! Please, try again.");
        return wrongAnswer;
    }
}
