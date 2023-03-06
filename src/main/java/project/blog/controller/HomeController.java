package project.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import project.blog.service.QuestionService;
import project.blog.vo.Question;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final QuestionService questionService;

    @GetMapping("/")
    public String home() {

        return "home";
    }

    @GetMapping("/vlog")
    public String vlog(Model model, @RequestParam(defaultValue = "0") int page) {


        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Question> paging = questionService.findPage(pageRequest);
        List<Question> questions = questionService.findAll();
        model.addAttribute("paging",paging);
        model.addAttribute("questions",questions);
        return "vlog";
    }

}
