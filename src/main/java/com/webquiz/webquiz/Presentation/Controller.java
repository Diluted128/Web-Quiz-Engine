package com.webquiz.webquiz.Presentation;

import com.webquiz.webquiz.Business.Answer;
import com.webquiz.webquiz.Business.Feedback;
import com.webquiz.webquiz.Business.Manager;
import com.webquiz.webquiz.Business.dao.Question;
import com.webquiz.webquiz.Business.dao.User;
import com.webquiz.webquiz.Exception.ForbiddenActionException;
import com.webquiz.webquiz.Exception.UserNameAlreadyTakenException;
import com.webquiz.webquiz.Exception.WrongQuestionIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@RestController
public class Controller {

    Manager manager;
    Feedback feedback;

    @Autowired
    public Controller(Manager manager, Feedback feedback) {
        this.manager = manager;
        this.feedback = feedback;
    }

    @PostMapping("/api/register")
    public void addUserCredentials(@Valid @RequestBody User user) throws UserNameAlreadyTakenException {
        manager.addUser(user);
    }

    @PostMapping("api/quizzes/{id}/solve")
    public Map<String, Object> solveQuiz(@PathVariable("id") int id, @RequestBody Answer answer) throws WrongQuestionIdException {
        Question question = manager.getQuestionById(id).get();
        if (manager.solveQuiz(answer.getAnswer(), question.getAnswer())) return feedback.goodAnswer();
        else return feedback.wrongAnswer();
    }

    @GetMapping("/api/quizzes")
    public Iterable<Question> getAllQuizzes() {
        return manager.getAllQuestions();
    }

    @PostMapping("api/quizzes")
    public Question saveReceivedQuiz(@Valid @RequestBody Question question) {
        manager.addQuestion(question);
        return question;
    }

    @GetMapping("api/quizzes/{id}")
    public Question getConcreteQuiz(@PathVariable int id) throws WrongQuestionIdException {
        return manager.getQuestionById(id).get();
    }

    @DeleteMapping("api/quizzes/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable int id) throws WrongQuestionIdException, ForbiddenActionException {
        manager.deleteQuiz(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
