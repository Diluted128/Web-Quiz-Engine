package com.webquiz.webquiz.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class QuestionContent {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotBlank
    private final String title;

    @NotBlank
    private final String text;

    @Size(min = 2)
    @NotNull
    private final List<String> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final List<Integer> answer;

    public QuestionContent(){
        id = 0;
        title = null;
        text = null;
        options = null;
        answer = null;
    }

    public QuestionContent(String Title, String text, List<String> options, List<Integer> answer) {
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

    public List<Integer> getAnswer() {
        return answer;
    }

}
