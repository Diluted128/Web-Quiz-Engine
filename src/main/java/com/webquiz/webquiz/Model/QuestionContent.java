package com.webquiz.webquiz.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QuestionContent {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    private final String title;

    private final String text;

    private final List<String> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final int answer;

    public QuestionContent(){
        id = 0;
        title = null;
        text = null;
        options = null;
        answer = 0;
    }

    public QuestionContent(String Title, String text, List<String> options, int answer) {
        this.id = 0;
        this.title = Title;
        this.text = text;
        this.answer = answer;
        this.options = options;
    }

    public void setId(int ID){
        id = ID;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getAnswer() {
        return answer;
    }

}
