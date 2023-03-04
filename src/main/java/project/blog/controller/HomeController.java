package project.blog.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/vlog")
    public String vlog() {
        return "vlog";
    }

    @GetMapping("/write")
    public String write() {
        return "write";
    }
}
