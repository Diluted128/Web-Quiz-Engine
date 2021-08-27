package com.webquiz.webquiz.Persistant;


import com.webquiz.webquiz.Business.dao.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    Optional<User> getUserByEmail(String email);
}
