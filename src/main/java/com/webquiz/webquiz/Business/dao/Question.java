package com.webquiz.webquiz.Business.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "QUESTION")
public class Question{

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotBlank
    @Column(name = "QUESTION_NAME")
    private String title;

    @NotBlank
    @Column(name = "QUESTION_TEXT")
    private String text;

    @Size(min = 2)
    @ElementCollection
    @CollectionTable(name = "OPTIONS", joinColumns = @JoinColumn(name = "QUESTION_ID"))
    @Column(name = "OPTIONS")
    private List<String> options = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection
    @CollectionTable(name = "CORRECT_ANSWERS", joinColumns = @JoinColumn(name = "QUESTION_ID"))
    @Column(name = "CORRECT_ANSWER")
    private List<Integer> answer = new ArrayList<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Question(){

    }

    public Question(String Title, String text) {
        this.title = Title;
        this.text = text;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
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
