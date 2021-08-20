package com.webquiz.webquiz.Resource;

import com.webquiz.webquiz.Exception.WrongQuestionIdException;
import com.webquiz.webquiz.Model.QuestionContent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public boolean solveQuiz(int id, List<Integer> answer) throws WrongQuestionIdException {

        List<Integer> emptyList = List.of();
        try {
            List<Integer> list = questions.get(id).getAnswer();

            if(answer != null && list != null) {
                return bubbleSort(list).equals(bubbleSort(answer));
            }
            else {
                assert answer != null;
                return Objects.equals(emptyList, answer);
            }
        }
        catch (IndexOutOfBoundsException e){
            throw new WrongQuestionIdException("Wrong ID");
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
    static List<Integer> bubbleSort(List<Integer> arr) {
        if(arr.size() != 0){
        int n = arr.size();
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr.get(j - 1) > arr.get(j)) {
                    temp = arr.get(j - 1);
                    arr.add(j - 1, arr.get(j));
                    arr.add(j, temp);
                }
            }
        }
        }
        return arr;
    }
}
