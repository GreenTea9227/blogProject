package project.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.blog.dto.PrivateQuestionList;
import project.blog.dto.ReturnQuestionData;
import project.blog.dto.WriteFormData;
import project.blog.service.QuestionService;
import project.blog.service.UserService;
import project.blog.vo.Question;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class PrivateOwnerController {

    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping("/privateWrite/{userId}")
    public String privateWrite(Model model, @PathVariable Long userId) {
        List<Question> privateQuestion = userService.findPrivateQuestion(userId);
        List<ReturnQuestionData> data = PrivateQuestionList.createPrivateQuestion(privateQuestion);
        model.addAttribute("data", data);
        return "privatewrite";
    }

    @GetMapping("/privateWrite/update/{questionId}")
    public String privateUpdateGet(@PathVariable Long questionId,Model model) {
        Question findQuestion = questionService.findById(questionId);
        model.addAttribute("question",findQuestion);
        return "private/privateUpdate";
    }

    @PostMapping("/privateWrite/update/{questionId}")
    public String privateUpdatePost(@Valid @ModelAttribute WriteFormData writeFormData
            , BindingResult bindingResult,@PathVariable Long questionId ,RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "private/privateUpdate";
        }
        questionService.update(questionId,writeFormData);
        redirectAttributes.addAttribute("questionId",questionId);
        return "redirect:/privateWrite/update/{questionId}";
    }



}
