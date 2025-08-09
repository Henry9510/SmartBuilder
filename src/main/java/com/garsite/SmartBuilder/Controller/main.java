package com.garsite.SmartBuilder.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class main {


    @GetMapping("/venturi_view")
    public String venturiPage() {
        return "venturi_view"; 
    }
     @GetMapping("/index")
    public String indexPage() {
        return "index"; 
    }

     @GetMapping("/builder")
    public String build() {
        return "builder"; 
    }
}
