package com.example.securityjpasql;

import com.example.securityjpasql.user.User;
import com.example.securityjpasql.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            User user = userRepo.findByUsername(s);
            return new MyUserDetails(user);
        }
        catch (Exception e){
            throw  new UsernameNotFoundException(" user disappeared ");
        }

    }
}
