package com.mysite.sbb.answer;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(Question question, String content, SiteUser author){
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
    }

    public Answer getAnswer(Integer id){
        Optional<Answer> answer = this.answerRepository.findById(id);
        if(answer.isPresent()){
            return answer.get();
        }
        else{
            throw new DataNotFoundException("answer not found");
        }
    }

    public void answerModify(Answer answer, String content){
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

    public void answerDelete(Answer answer){
        this.answerRepository.delete(answer);
    }
}
