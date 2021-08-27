package com.webquiz.webquiz.Business;

import com.webquiz.webquiz.Business.Sort.BubbleSort;
import com.webquiz.webquiz.Business.dao.Question;
import com.webquiz.webquiz.Exception.ForbiddenActionException;
import com.webquiz.webquiz.Exception.UserNameAlreadyTakenException;
import com.webquiz.webquiz.Exception.WrongQuestionIdException;
import com.webquiz.webquiz.Persistant.QuestionRepo;
import com.webquiz.webquiz.Persistant.UserRepo;
import com.webquiz.webquiz.Security.SecurityConfig;
import com.webquiz.webquiz.Business.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Manager {

    QuestionRepo questionRepo;
    UserRepo userRepo;
    PasswordEncoder passwordEncoder;
    SecurityConfig securityConfig;

    @Autowired
    public Manager(QuestionRepo questionRepo,
                   UserRepo userRepo,
                   PasswordEncoder passwordEncoder,
                   SecurityConfig securityConfig) {

        this.securityConfig = securityConfig;
        this.questionRepo = questionRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Iterable<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    public User getCurrentUser() {
        return userRepo.getUserByEmail(securityConfig.getCurrentLoggedUserName()).get();
    }

    public void addQuestion(Question question) {
        int userID = userRepo.getUserByEmail(getCurrentUser().getEmail()).get().getId();
        question.setUser_id(userID);
        questionRepo.save(question);
    }

    public Optional<Question> getQuestionById(int id) throws WrongQuestionIdException {
        Optional<Question> question = questionRepo.getQuestionById(id);
        if (question.isPresent()) return questionRepo.findById(id);
        else throw new WrongQuestionIdException("PROVIDED ID ISN'T ASSOCIATED WITH ANY QUESTION.");
    }

    public boolean solveQuiz(List<Integer> userAnswers, List<Integer> correctAnswers) {
        List<Integer> emptyList = List.of();
        if (userAnswers != null && correctAnswers != null)
            return BubbleSort.bubbleSort(userAnswers).equals(BubbleSort.bubbleSort(correctAnswers));
        else
            return (userAnswers != null || emptyList.equals(correctAnswers)) && (correctAnswers != null || emptyList.equals(userAnswers));
    }

    public void deleteQuiz(int id) throws ForbiddenActionException, WrongQuestionIdException {

        int QuestionIDOwner = getQuestionById(id).get().getUser_id();
        int UserId = getCurrentUser().getId();

        if (QuestionIDOwner == UserId) questionRepo.deleteById(id);
        else throw new ForbiddenActionException("NOT ENOUGH PERMISSIONS.");
    }

    public void addUser(User userToAdd) throws UserNameAlreadyTakenException {
        Optional<User> user = userRepo.getUserByEmail(userToAdd.getEmail());
        if (user.isEmpty()) {
            userToAdd.setPassword(passwordEncoder.encode(userToAdd.getPassword()));
            userRepo.save(userToAdd);
        } else
            throw new UserNameAlreadyTakenException("PROVIDED USERNAME IS ALREADY TAKEN");
    }
}
