package com.example.ejercicio101112;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value="/hola")
    public String saludo(){
        return "Diego es bonito";
    }
}
