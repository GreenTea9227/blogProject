package project.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.blog.dto.foruser.LoginUser;

import javax.validation.Valid;

@Controller
public class LoginController {

    @GetMapping("/login/user")
    public String loginGet(Model model) {
        model.addAttribute("loginUser", new LoginUser());
        return "login";
    }

    @PostMapping("/login/user")
    public String loginPost(@Valid LoginUser loginUser, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        return "home";
    }
}
