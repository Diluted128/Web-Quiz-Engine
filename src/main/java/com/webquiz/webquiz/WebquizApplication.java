package com.webquiz.webquiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class WebquizApplication {


    public static void main(String[] args) {
        SpringApplication.run(WebquizApplication.class, args);

//        ConfigurableApplicationContext configurableApplicationContext =
//                 SpringApplication.run(WebquizApplication.class, args);
//
//        QuestionRepo questionRepo = configurableApplicationContext.getBean(QuestionRepo.class);
//
//        Question question = new Question("ile kot ma lapekkk","kot");
//        QuestionAnswers questionAnswers1 = new QuestionAnswers("jednaa", question);
//        QuestionAnswers questionAnswers2 = new QuestionAnswers("dwie", question);
//        CorrectAnswer  correctAnswer = new CorrectAnswer(1,question);
//        CorrectAnswer  correctAnswer2 = new CorrectAnswer(2,question);
//
//        List<QuestionAnswers> a = Arrays.asList(questionAnswers1,questionAnswers2);
//
//        List<CorrectAnswer> b = Arrays.asList(correctAnswer, correctAnswer2);
//
//        question.setOptions(a);
//        question.setAnswer(b);
//
//        questionRepo.save(question);

    }

}
