package com.webquiz.webquiz.Security;

import com.webquiz.webquiz.Persistant.UserRepo;
import com.webquiz.webquiz.Business.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    UserRepo userRepo;

    @Autowired
    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.getUserByEmail(username);

        optionalUser.orElseThrow(() -> new UsernameNotFoundException("not found: " + username));
        UserDetails a = optionalUser.map(MyUserDetails::new).get();

        System.out.println(a.getUsername());
        System.out.println(a.getPassword());
        System.out.println(a.getAuthorities());
        return optionalUser.map(MyUserDetails::new).get();
    }
}
