package com.webquiz.webquiz.Business.Manager;

import com.webquiz.webquiz.Exception.UserNameAlreadyTakenException;
import com.webquiz.webquiz.Persistant.UserRepo;
import com.webquiz.webquiz.Security.SecurityConfig;
import com.webquiz.webquiz.Business.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManager {

    UserRepo userRepo;
    SecurityConfig securityConfig;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserManager(UserRepo userRepo, PasswordEncoder passwordEncoder,
                       SecurityConfig securityConfig) {

        this.passwordEncoder = passwordEncoder;
        this.securityConfig = securityConfig;
        this.userRepo = userRepo;
    }

    public void addUser(User userToAdd) throws UserNameAlreadyTakenException {
        Optional<User> user = userRepo.getUserByEmail(userToAdd.getEmail());
        if (user.isEmpty()) {
            userToAdd.setPassword(passwordEncoder.encode(userToAdd.getPassword()));
            userRepo.save(userToAdd);
        } else
            throw new UserNameAlreadyTakenException("PROVIDED USERNAME IS ALREADY TAKEN");
    }

    public User getCurrentUser() {
        return userRepo.getUserByEmail(securityConfig.getCurrentLoggedUserName()).get();
    }

}
