package com.mysite.sbb.question;

import com.mysite.sbb.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String detail(@PathVariable("id") Integer id, Model model, AnswerForm answerForm){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "questionDetail";
    }
    @GetMapping("create")
    public String questionCreate(QuestionForm questionForm){
        return "questionForm";
    }

    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "questionForm";
        }
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }
 }
