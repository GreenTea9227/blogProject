package project.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.blog.dto.WriteFormData;
import project.blog.repository.QuestionRepository;
import project.blog.vo.Question;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public void createQuestion(WriteFormData writeFormData) {
        Question question = new Question(writeFormData.getUsername(), writeFormData.getContent()
                , writeFormData.getSubject());

        questionRepository.save(question);
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Page<Question> findPage(PageRequest pageRequest) {
        return questionRepository.findAll(pageRequest);
    }

    public Page<Question> findByParam(String param, PageRequest pageRequest) {
        return questionRepository.findByParamNameLike(param, pageRequest);
    }
}
