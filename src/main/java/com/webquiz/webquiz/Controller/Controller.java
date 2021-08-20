package com.webquiz.webquiz.Controller;

import com.webquiz.webquiz.Resource.Feedback;
import com.webquiz.webquiz.Model.QuestionContent;
import com.webquiz.webquiz.Resource.Questions;
import com.webquiz.webquiz.Exception.WrongQuestionIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class Controller {

    Questions questions;
    Feedback feedback;

    @Autowired
    Controller(){
        questions = new Questions();
        feedback = new Feedback();
    }

    @PostMapping("api/quizzes/{id}/solve")
    public Map<String,Object> solveQuiz(@PathVariable("id") int id, @RequestParam("answer") int answer) throws WrongQuestionIdException {
        if(questions.solveQuiz(id - 1, answer)) return feedback.goodAnswer();
        else return feedback.wrongAnswer();
    }

    @GetMapping("/api/quizzes")
    public List<QuestionContent> getAllQuizzes(){
        return questions.getQuestionsList();
    }

    @PostMapping("api/quizzes")
    public QuestionContent saveReceivedQuiz(@RequestBody QuestionContent questionContent){
         questions.addQuestion(questionContent);
         return questions.getQuestionsList().get(questions.getQuestionsList().size() - 1);
    }

    @GetMapping("api/quizzes/{id}")
    public QuestionContent getConcreteQuiz(@PathVariable() int id) throws WrongQuestionIdException {
        return questions.getQuestionByID(id - 1);
    }
}
