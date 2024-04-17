package com.testing.questions_history.repository;

import com.testing.questions_history.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Long countById(Integer id);

    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ",
            nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
