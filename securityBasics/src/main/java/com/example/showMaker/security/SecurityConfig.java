package com.example.showMaker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /* ****************** USERS IN MEMORY **********************

        auth.inMemoryAuthentication()
                .withUser("user1").password("1234").roles("USER")
                .and()
                .withUser("user2").password("1234").roles("USER","ADMIN");

         ************************************************************** */
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username = ?");
                /* ****************** Default schema ******************
                .withDefaultSchema()
                .withUser(
                        User.withUsername("user1")
                        .password("1234")
                        .roles("USER")
                )
                .withUser(
                        User.withUsername("user2")
                        .password("1234")
                        .roles("ADMIN","USER")
                );
               ***************************************************************** */

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/api/students/").permitAll()
                .antMatchers("/api/students/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
