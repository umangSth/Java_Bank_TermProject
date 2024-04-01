package Bank_Term_Project.Bank_TP.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}