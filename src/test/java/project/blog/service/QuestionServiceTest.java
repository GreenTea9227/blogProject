package project.blog.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import project.blog.dto.WriteFormData;
import project.blog.repository.QuestionRepository;
import project.blog.vo.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class QuestionServiceTest {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionService questionService;
    @Test
    void createQuestion() {
//        Question question = Question.builder()
//                .subject("hello")
//                .content("hello world")
//                .username("yohan")
//                .build();

        WriteFormData writeFormData = WriteFormData.builder().subject("hello")
                .content("hello world")
                .username("yohan").build();
        questionService.createQuestion(writeFormData);

        List<Question> all = questionService.findAll();
        Assertions.assertThat(all).size().isEqualTo(1);
    }


    @Test
    void findPage() {
    }

    @Test
    void findByParam() {

    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void removeQuestion() {
    }
}