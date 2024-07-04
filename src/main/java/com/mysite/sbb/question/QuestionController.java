package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/question/*")
public class QuestionController {

    //private final QuestionRepository questionRepository;
    private final QuestionService questionService;


    @GetMapping("list")
    public String list(Model model){
        //List<Question> questionList = this.questionRepository.findAll();
        List<Question> questionList = this.questionService.getList();
        System.out.println("질문: "+questionList);
        model.addAttribute("questionList", questionList);
        return "questionList";
    }

    @GetMapping(value="detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "questionDetail";
    }
    @GetMapping("create")
    public String questionCreate(Model model){
        return "questionForm";
    }

    @PostMapping("create")
    public String questionCreate(@RequestParam(value="subject") String subject
            , @RequestParam(value="content") String content, Model model){
        this.questionService.create(subject, content);
        return "redirect:/question/list";
    }
 }
