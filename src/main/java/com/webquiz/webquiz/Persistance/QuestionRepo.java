package com.webquiz.webquiz.Persistance;

import com.webquiz.webquiz.Business.dao.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepo extends CrudRepository<Question, Integer> {


}
