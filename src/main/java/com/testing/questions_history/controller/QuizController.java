package com.testing.questions_history.controller;

import com.testing.questions_history.QuestionNotFoundException;
import com.testing.questions_history.model.*;
import com.testing.questions_history.service.CategoryService;
import com.testing.questions_history.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    private final CategoryService categoryService;

    private static int testId;

    @GetMapping("/quiz-home")
    public ModelAndView getQuizHomePage(){
        List<Category> categoryList = categoryService.getAllCategory();
        return new ModelAndView("quiz-home", "categoryList", categoryList);
    }

    @PostMapping("/quiz/create")
    public String createQuiz(@RequestParam("quiz-title") String quizTitle,
                             @RequestParam("cat") String cat){
        Quiz quiz = quizService.createQuiz(quizTitle, cat);
        return "redirect:/quiz/getQuiz/" + Integer.toString(quiz.getId());
    }

    @GetMapping("/quiz/getQuiz/{id}")
    public String getQuizQuestions(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            List<QuestionWrapper> questionsForUser = quizService.getQuizQuestions(id);

            model.addAttribute("quizObject", questionsForUser);
            testId = id;
            return "quizList";
        } catch (QuestionNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/quiz-home";
        }
    }


    @PostMapping("/quiz/commit")
    public String getAnswer(@RequestParam("answer1") String answer1,
                            @RequestParam("answer2") String answer2,
                            @RequestParam("answer3") String answer3,
                            Model model, RedirectAttributes redirectAttributes) {
        try {
            List<String> resultList = List.of(answer1, answer2, answer3);
            System.out.println(resultList);
            System.out.println(testId);
            int result = quizService.calculateResult(testId, resultList);
            System.out.println(result);
            model.addAttribute("result", result);

            return "quiz-result";
        } catch (QuestionNotFoundException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/quiz-home";
        }

    }

    @GetMapping("/quiz-allResults")
    public String getAllResults(){
        return "quiz-allResults";
    }


}
