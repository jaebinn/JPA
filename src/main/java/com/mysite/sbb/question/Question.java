package com.mysite.sbb.question;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Question {
    //기본키 설정
    @Id
    //GeneratedValue애너테이션을 적용하면 데이터를 저장할 때 해당 속성에 값을 따로
    //세팅을 하지않아도 1씩 자동으로 증가하여 저장(auto_increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //동일하지않게
    private Integer id;

    //컬럼명(길이제한)
    @Column(length = 200)
    private String subject;

    //컬럼명(Text로 인식)
    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    //CascadeType.REMOVE -> question이 삭제되면 answer도 의미가 없기 때문에
    //모두 함께 삭제하기위해
    //mappedBy는 참조 엔티티의 속성명
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    //여러개 질문이 한 명의 사용자에게 작성될 수 있으므로
    @ManyToOne
    private SiteUser author;

    @ManyToMany
    Set<SiteUser> voter;
}
