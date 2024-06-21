package com.testing.questions_history.controller;

import com.testing.questions_history.QuestionNotFoundException;
import com.testing.questions_history.dto.AnswerForQuiz;
import com.testing.questions_history.dto.CompleteResult;
import com.testing.questions_history.model.*;
import com.testing.questions_history.service.CategoryService;
import com.testing.questions_history.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;
    private final CategoryService categoryService;

    private List<QuestionWrapper> questionsForUser;

    @GetMapping("/home")
    public String getQuizHomePage(Model model){
        model.addAttribute("categoryList", categoryService.getAllCategory());
        return "quiz-home";
    }

    @PostMapping("/create")
    public String createQuiz(@RequestParam("quiz-title") String quizTitle,
                             @RequestParam("categoryId") Integer categoryId){
        Quiz quiz = quizService.createQuiz(quizTitle, categoryId);
        return "redirect:/quiz/getQuiz/" + Integer.toString(quiz.getId());
    }

    @GetMapping("/getQuiz/{id}")
    public String getQuizQuestions(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            questionsForUser = quizService.getQuizQuestions(id);
            model.addAttribute("quizObject", questionsForUser);
            int testId = id;
            return "quizList";
        } catch (QuestionNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/quiz/home";
        }
    }

    @PostMapping("/commit")
    public String getAnswer(AnswerForQuiz answer, Model model){
        CompleteResult completeResult = quizService.getResultAnswer(questionsForUser, answer);
        model.addAttribute("completeResult", completeResult);
        return "quiz-result";
    }

    @GetMapping("/allResults")
    public String getAllResults(){
        return "quiz-allResults";
    }


}
