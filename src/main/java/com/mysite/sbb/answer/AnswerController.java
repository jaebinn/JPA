package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer/*")
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("create/{id}")
    public String create(@PathVariable("id") Integer id, Model model,
                         @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {

        Question question = this.questionService.getQuestion(id);
        //principal.getName()을 하게되면 로그인한 유저의 정보를 가져온다.
        SiteUser siteUser = this.userService.getUser(principal.getName());
        if(bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "questionDetail";
        }
        this.answerService.create(question, answerForm.getContent(),siteUser);
        //Todo: 답변을 저장한다.
        return String.format("redirect:/question/detail/%s", id);

    }
}
