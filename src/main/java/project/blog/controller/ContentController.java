package project.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.blog.dto.WriteFormData;

import javax.validation.Valid;

@Controller
public class ContentController {

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
        return "redirect:/";
    }
}
