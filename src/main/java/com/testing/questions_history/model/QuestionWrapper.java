package com.testing.questions_history.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionWrapper {

    private Integer id;
    private String question_title;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String right_answer;

}
