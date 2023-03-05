package project.blog.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.blog.vo.Question;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Question question = Question.builder().username("yohan")
                    .content("yohanasdfasdfsd").subject("hello").build();
            questions.add(question);
        }


        model.addAttribute("questions",questions);
        return "home";
    }

    @GetMapping("/vlog")
    public String vlog() {
        return "vlog";
    }

}
