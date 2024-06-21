package com.testing.questions_history.dto;

public record ResultAnswer(String question_title,
                           String right_answer,
                           String user_answer,
                           boolean check
                           ) {}
