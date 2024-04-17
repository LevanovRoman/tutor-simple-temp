package com.testing.questions_history.service;

import com.testing.questions_history.model.Category;
import com.testing.questions_history.model.Question;
import com.testing.questions_history.repository.CategoryRepository;
import com.testing.questions_history.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}