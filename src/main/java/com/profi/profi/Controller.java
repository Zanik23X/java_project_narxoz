package com.profi.profi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class Controller {

    @GetMapping("/hi")
    public String hi() {
        return "Hi ! ! !";
    }

    @GetMapping("/bye")
    public String bye() {
        return "Bye ! ! !";
    }
}