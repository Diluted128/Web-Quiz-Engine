package com.webquiz.webquiz.Persistant;

import com.webquiz.webquiz.Business.dao.SolvedQuestion;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SolvedQuestionRepo  extends PagingAndSortingRepository<SolvedQuestion, Integer> {
}
