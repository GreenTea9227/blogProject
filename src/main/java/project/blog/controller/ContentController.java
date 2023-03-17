package project.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.blog.dto.forquestion.WriteFormData;
import project.blog.service.QuestionService;
import project.blog.service.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class ContentController {

    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("writeFormData", new WriteFormData());
        return "write";
    }

    @PostMapping("/write")
    public String postWrite(@Valid @ModelAttribute WriteFormData writeFormData, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.reject("global error", "global error 발생");
            return "write";
        }
        questionService.createQuestion(writeFormData);

        return "redirect:/";
    }
}
