package com.webquiz.webquiz.Persistant;

import com.webquiz.webquiz.Business.dao.Question;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface QuestionRepo extends PagingAndSortingRepository<Question, Integer> {
     Optional<Question> getQuestionById(int id);
}
