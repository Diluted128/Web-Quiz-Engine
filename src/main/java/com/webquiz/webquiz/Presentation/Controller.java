package com.webquiz.webquiz.Presentation;

import com.webquiz.webquiz.Business.Answer;
import com.webquiz.webquiz.Business.Feedback;
import com.webquiz.webquiz.Exception.ForbiddenActionException;
import com.webquiz.webquiz.Exception.UserNameAlreadyTakenException;
import com.webquiz.webquiz.Exception.WrongQuestionIdException;
import com.webquiz.webquiz.Business.Manager.QuestionManager;
import com.webquiz.webquiz.Business.Manager.UserManager;
import com.webquiz.webquiz.Business.dao.Question;
import com.webquiz.webquiz.Business.dao.SolvedQuestion;
import com.webquiz.webquiz.Business.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@RestController
public class Controller {

    UserManager userManager;
    QuestionManager questionManager;
    Feedback feedback;

    @Autowired
    public Controller(UserManager userManager, QuestionManager questionManager, Feedback feedback) {
        this.userManager = userManager;
        this.questionManager = questionManager;
        this.feedback = feedback;
    }

    @PostMapping("/api/register")
    public void addUserCredentials(@Valid @RequestBody User user) throws UserNameAlreadyTakenException {
        userManager.addUser(user);
    }

    @GetMapping("/api/quizzes")
    public Iterable<Question> getAllQuizzes(@RequestParam int page) {
        return questionManager.getAllQuestions(page);
    }

    @PostMapping("api/quizzes")
    public Question saveReceivedQuiz(@Valid @RequestBody Question question) {
        questionManager.addQuestion(question);
        return question;
    }

    @GetMapping("api/quizzes/completed")
    public Iterable<SolvedQuestion> getCompletedQuizzes(@RequestParam(required = false, defaultValue = "0") int page) {
        return questionManager.getCompletedQuizzes(page);
    }

    @GetMapping("api/quizzes/{id}")
    public Question getConcreteQuiz(@PathVariable int id) throws WrongQuestionIdException {
        return questionManager.getQuestionById(id).get();
    }

    @DeleteMapping("api/quizzes/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable int id) throws ForbiddenActionException, WrongQuestionIdException {
        questionManager.deleteQuiz(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("api/quizzes/{id}/solve")
    public Map<String, Object> solveQuiz(@PathVariable("id") int id, @RequestBody Answer answer) throws WrongQuestionIdException {
        if (questionManager.solveQuiz(answer.getAnswer(), id)) return feedback.goodAnswer();
        else return feedback.wrongAnswer();
    }
}
