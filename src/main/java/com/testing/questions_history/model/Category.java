package com.testing.questions_history.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 450)
    private String category_title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Question> questionList;

    public void addQuestionToCategory(Question question){
        if (questionList == null){
            questionList = new ArrayList<>();
        }
        questionList.add(question);
        question.setCategory(this);
    }
}
