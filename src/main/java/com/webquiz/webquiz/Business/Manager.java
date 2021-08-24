package com.webquiz.webquiz.Business;


import com.webquiz.webquiz.Business.dao.Question;
import com.webquiz.webquiz.Exception.WrongQuestionIdException;
import com.webquiz.webquiz.Persistance.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Manager {

    @Autowired
    QuestionRepo questionRepo;

  public Iterable<Question> getAllQuestions(){
        return questionRepo.findAll();
    }

   public void addQuestion(Question question){
     questionRepo.save(question);
   }

    public Optional<Question> getQuestionById(int id) throws WrongQuestionIdException {
      if(id > questionRepo.count())
          throw new WrongQuestionIdException("Wrong id");
      else{
          return questionRepo.findById(id);
      }
    }

    public boolean solveQuiz(List<Integer> userAnswers, List<Integer> correctAnswers){
        System.out.println(userAnswers);
        System.out.println(correctAnswers);
        List<Integer> emptyList = List.of();

        if(userAnswers != null && correctAnswers != null) {
            return bubbleSort(userAnswers).equals(bubbleSort(correctAnswers));
        }
        else return (userAnswers != null || emptyList.equals(correctAnswers)) && (correctAnswers != null || emptyList.equals(userAnswers));
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
