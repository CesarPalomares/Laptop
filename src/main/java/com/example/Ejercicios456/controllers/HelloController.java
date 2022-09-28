package com.example.Ejercicios456.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${app.devMessage}")
    String devMessage;

    @Value("${app.testMessage}")
    String testMessage;

    @GetMapping("/")
    public String inicio(){
        return "Bienvenido a mi aplicacion!!!";
    }

    @GetMapping("/hola")
    public String holaMundo(){
        return devMessage;
    }

    @GetMapping("/hola2")
    public String holaMundo2(){
        return testMessage;
    }
}
