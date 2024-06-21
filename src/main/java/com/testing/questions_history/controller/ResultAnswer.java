package com.testing.questions_history.controller;

public record ResultAnswer(String question_title,
                           String right_answer,
                           String user_answer,
                           boolean check
                           ) {}
