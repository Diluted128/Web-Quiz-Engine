package com.webquiz.webquiz.Business.Manager;

import com.webquiz.webquiz.Exception.ForbiddenActionException;
import com.webquiz.webquiz.Exception.WrongQuestionIdException;
import com.webquiz.webquiz.Persistant.QuestionRepo;
import com.webquiz.webquiz.Persistant.SolvedQuestionRepo;
import com.webquiz.webquiz.Persistant.UserRepo;
import com.webquiz.webquiz.Presentation.QuestionsPage;
import com.webquiz.webquiz.Security.SecurityConfig;
import com.webquiz.webquiz.Business.Sort.BubbleSort;
import com.webquiz.webquiz.Business.dao.Question;
import com.webquiz.webquiz.Business.dao.SolvedQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionManager {

    SolvedQuestionRepo solvedQuestionRepo;
    UserManager userManager;
    QuestionRepo questionRepo;
    UserRepo userRepo;
    PasswordEncoder passwordEncoder;
    SecurityConfig securityConfig;

    @Autowired
    public QuestionManager(QuestionRepo questionRepo,
                           UserRepo userRepo,
                           PasswordEncoder passwordEncoder,
                           SecurityConfig securityConfig,
                           UserManager userManager,
                           SolvedQuestionRepo solvedQuestionRepo) {

        this.solvedQuestionRepo = solvedQuestionRepo;
        this.userManager = userManager;
        this.securityConfig = securityConfig;
        this.questionRepo = questionRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Iterable<Question> getAllQuestions(int page) {
        QuestionsPage questionsPage = new QuestionsPage(page, 10);
        Pageable pageable = PageRequest.of(questionsPage.getPageNumber(), questionsPage.getPageSize());
        return questionRepo.findAll(pageable);
    }

    public void addQuestion(Question question) {
        int userID = userRepo.getUserByEmail(userManager.getCurrentUser().getEmail()).get().getId();
        question.setUser_id(userID);
        questionRepo.save(question);
    }

    public Iterable<SolvedQuestion> getCompletedQuizzes(int page) {
        QuestionsPage questionsPage = new QuestionsPage(page, 10);
        Sort sort = Sort.by(questionsPage.getSortDirection(), questionsPage.getSortBy());
        Pageable pageable = PageRequest.of(questionsPage.getPageNumber(), questionsPage.getPageSize(), sort);
        return solvedQuestionRepo.findAll(pageable);
    }

    public Optional<Question> getQuestionById(int id) throws WrongQuestionIdException {
        Optional<Question> question = questionRepo.getQuestionById(id);
        if (question.isPresent()) return question;
        else throw new WrongQuestionIdException("PROVIDED ID ISN'T ASSOCIATED WITH ANY QUESTION.");
    }

    public void deleteQuiz(int id) throws ForbiddenActionException, WrongQuestionIdException {

        int QuestionIDOwner = getQuestionById(id).get().getUser_id();
        int UserId = userManager.getCurrentUser().getId();

        if (QuestionIDOwner == UserId) questionRepo.deleteById(id);
        else throw new ForbiddenActionException("NOT ENOUGH PERMISSIONS.");
    }

    public boolean solveQuiz(List<Integer> userAnswers, int id) throws WrongQuestionIdException {

        Question question = getQuestionById(id).get();
        List<Integer> correctAnswers = question.getAnswer();
        List<Integer> emptyList = List.of();
        boolean result;

        System.out.println(userAnswers);
        System.out.println(correctAnswers);

        if (userAnswers != null && correctAnswers != null)
            result = BubbleSort.bubbleSort(userAnswers).equals(BubbleSort.bubbleSort(correctAnswers));
        else
            result = (userAnswers != null || emptyList.equals(correctAnswers)) && (correctAnswers != null || emptyList.equals(userAnswers));

        if (result) {
            solvedQuestionRepo.save(new SolvedQuestion(id, LocalDateTime.now()));
        }

        System.out.println(result);
        return result;
    }
}
