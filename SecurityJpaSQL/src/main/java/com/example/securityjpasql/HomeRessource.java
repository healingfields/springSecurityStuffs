package com.example.securityjpasql;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRessource {

    @GetMapping("/")
    public String index(){
        return "<h1> home page </h1>";
    }

    @GetMapping("/admin")
    public String admin(){
        return "<h1> admin area </h1>";
    }

    @GetMapping("/user")
    public String user(){
        return "<h1> user area </h1>";
    }

}
