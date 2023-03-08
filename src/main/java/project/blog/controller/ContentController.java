package project.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.blog.dto.PrivateQuestionList;
import project.blog.dto.ReturnQuestionData;
import project.blog.dto.WriteFormData;
import project.blog.service.QuestionService;
import project.blog.service.UserService;
import project.blog.vo.Question;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ContentController {

    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping("/write")
    public String write( Model model) {
        model.addAttribute("writeFormData",new WriteFormData());
        return "write";
    }

    @PostMapping("/write")
    public String postWrite(@Valid @ModelAttribute WriteFormData writeFormData, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.reject("global error","global error 발생");
            return "write";
        }
        questionService.createQuestion(writeFormData);

        return "redirect:/";
    }

    @GetMapping("/privateWrite/{userId}")
    public String privateWrite(Model model, @PathVariable Long userId) {
        List<Question> privateQuestion = userService.findPrivateQuestion(userId);
        List<ReturnQuestionData> data = PrivateQuestionList.createPrivateQuestion(privateQuestion);
        model.addAttribute("data",data);
        return "privatewrite";
    }


}
