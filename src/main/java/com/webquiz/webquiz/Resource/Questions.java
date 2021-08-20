package com.webquiz.webquiz.Resource;

import com.webquiz.webquiz.Exception.WrongQuestionIdException;
import com.webquiz.webquiz.Model.QuestionContent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Questions {

    public List<QuestionContent> questions;

    public Questions(){
        questions = new ArrayList<>();
    }

    @Bean
    public Questions getQuestions(){
        return new Questions();
    }

    public List<QuestionContent> getQuestionsList(){
        return questions;
    }

    public boolean solveQuiz(int id, int answer) throws WrongQuestionIdException {
        try {
            return questions.get(id).getAnswer() == answer;
        }
        catch (IndexOutOfBoundsException e){
            throw new WrongQuestionIdException("BAD ID");
        }
    }

    public void addQuestion(QuestionContent questionContent){
        questionContent.setId(questions.size() + 1);
        questions.add(questionContent);
    }

    public QuestionContent getQuestionByID(int id) throws WrongQuestionIdException {
        try {
            return questions.get(id);
        }
        catch (IndexOutOfBoundsException e){
            throw new WrongQuestionIdException("BAD ID");
        }
    }
}
