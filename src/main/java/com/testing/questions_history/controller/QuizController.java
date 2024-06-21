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
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;
    private final CategoryService categoryService;

    private static int testId;
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
//            List<QuestionWrapper> questionsForUser = quizService.getQuizQuestions(id);
            questionsForUser = quizService.getQuizQuestions(id);
            model.addAttribute("quizObject", questionsForUser);
            testId = id;
            return "quizList";
        } catch (QuestionNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/quiz/home";
        }
    }


    @PostMapping("/commit")
    public String getAnswer(AnswerForQuiz answer,
                            Model model, RedirectAttributes redirectAttributes) {
        try {
//            List<String> resultList = List.of(answer.answer1(), answer.answer2(), answer.answer3());
            int result = quizService.calculateResult(testId, answer);
            model.addAttribute("result", result);

            List<ResultAnswer> resultAnswer = quizService.getResultAnswer(questionsForUser, answer);
//            model.addAttribute("questionsForUser", quizService.getQuizById(testId).get());
            model.addAttribute("resultAnswer", resultAnswer);
            return "quiz-result";
        } catch (QuestionNotFoundException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/quiz/home";
        }

    }

    @GetMapping("/allResults")
    public String getAllResults(){
        return "quiz-allResults";
    }


}
