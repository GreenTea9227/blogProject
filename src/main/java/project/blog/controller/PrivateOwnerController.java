package project.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.blog.dto.*;
import project.blog.service.QuestionService;
import project.blog.service.UserService;
import project.blog.vo.Question;

import javax.validation.Valid;
import java.util.List;

@Slf4j
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
    public String privateUpdatePost(@Valid @ModelAttribute UpdateFormData updateFormData
            , BindingResult bindingResult,@PathVariable Long questionId ,RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "private/privateUpdate";
        }
        questionService.update(questionId,updateFormData);
        redirectAttributes.addAttribute("questionId",questionId);
        return "redirect:/privateWrite/update/{questionId}";
    }

    @PostMapping("/privateWrite/delete/{questionId}")
    public String privateDelete(@PathVariable Long questionId) {
        questionService.removeQuestion(questionId);
        //TODO - userid세션에 저장하기
        return "redirect:/privateWrite/{userId}";
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("formUser",new FormUser());
        return "private/join";
    }

    @PostMapping("/join")
    public String postJoin(@Valid @ModelAttribute FormUser formUser,BindingResult bindingResult) {
        //TODO 아이디 중복 체크 / 닉네임 중복 체크


        boolean checkUser = userService.checkUser(formUser.getNickname(), formUser.getEmail());
        if (!checkUser) {
            bindingResult.reject("idOrNickname","이메일 또는 닉네임이 중복입니다.");
        }

        if (bindingResult.hasErrors()) {
            return "private/join";
        }

        userService.saveUser(formUser);

        //TODO /login 구현
        return "redirect:/login";

    }



}
