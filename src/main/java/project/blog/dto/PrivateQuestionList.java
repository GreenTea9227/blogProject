package project.blog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import project.blog.vo.Question;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class PrivateQuestionList {
    private String username;

    private String content;
    private String subject;
    private String tag;

    public static List<ReturnQuestionData> createPrivateQuestion(List<Question> question) {
        ArrayList<ReturnQuestionData> list = new ArrayList<>();
        for (Question question1 : question) {
            list.add(createPrivateQuestion(question1));
        }
        return list;
    }

    public static ReturnQuestionData createPrivateQuestion(Question question) {

        return ReturnQuestionData.builder()
                .username(question.getUsername())
                .content(question.getContent())
                .tag(question.getTag())
                .subject(question.getSubject()).build();
    }
}
