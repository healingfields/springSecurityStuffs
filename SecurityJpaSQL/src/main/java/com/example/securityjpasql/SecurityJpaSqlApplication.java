package com.example.securityjpasql;

import com.example.securityjpasql.user.User;
import com.example.securityjpasql.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepo.class)
public class SecurityJpaSqlApplication implements CommandLineRunner {

    @Autowired
    UserRepo userRepo;

    public static void main(String[] args) {
        SpringApplication.run(SecurityJpaSqlApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        userRepo.save(new User(1,"user1","1234",true,"ROLE_ADMIN"));
        userRepo.save(new User(2,"user2","1234",true,"ROLE_ADMIN,ROLE_USER"));
    }
}
