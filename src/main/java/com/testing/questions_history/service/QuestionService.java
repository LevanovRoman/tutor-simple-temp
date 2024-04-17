package com.testing.questions_history.service;

import com.testing.questions_history.QuestionNotFoundException;
import com.testing.questions_history.model.Question;
import com.testing.questions_history.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    public void save(Question question){
        questionRepository.save(question);
    }

    public Question getQuestionById(Integer id) throws QuestionNotFoundException {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()){
            return question.get();
        }
        throw new QuestionNotFoundException("Could not find any questions with ID " + id);
    }

    public void deleteQuestionById(Integer id) throws QuestionNotFoundException {
        Long count = questionRepository.countById(id);
        if (count == null || count == 0){
            throw new QuestionNotFoundException("Could not find any questions with ID " + id);
        }
        questionRepository.deleteById(id);
    }
}
