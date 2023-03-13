package project.blog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@Profile(value = "apiTest")
@SpringBootTest
public class ApiTest {

    @Autowired
    private Environment environment;
    @Autowired
    private RestTemplate restTemplate;

    @TestConfiguration
    static class hello {
        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }

    @Test
    public void test1() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        User user = restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/1", User.class);

        System.out.println(user);
    }

    @Test
    public void test2() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<User> responseEntity =
                restTemplate.exchange("https://jsonplaceholder.typicode.com/todos/1"
                        , HttpMethod.GET, httpEntity, User.class);

        System.out.println(responseEntity.getBody());
    }

    @Test
    public void test3() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        PostUser postUser = new PostUser("안녕하세요", "반갑습니다", "1");

        String url = "https://jsonplaceholder.typicode.com/users";
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(url, String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void test4() {
        String url = "https://jsonplaceholder.typicode.com/users";
        HttpHeaders httpHeaders = restTemplate.headForHeaders(url);
        assertThat(httpHeaders.getContentType().includes(MediaType.APPLICATION_JSON)).isTrue();
    }

    @Test
    void test5() {
        String url = "https://jsonplaceholder.typicode.com/users";
        HttpEntity<PostUser> postUserHttpEntity =
                new HttpEntity<>(new PostUser("hello world", "contents", "yohan"));
        User user = restTemplate.postForObject(url, postUserHttpEntity, User.class);
        assertThat(user).isNotNull();
        assertThat(user.id).isEqualTo(11L);
    }

    @Test
    void test6() {

        String url = "https://jsonplaceholder.typicode.com/users";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.add("id", "1");

        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(valueMap, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        System.out.println(responseEntity.getBody());
    }
}

class PostUser {
    public String title;
    public String contents;
    public String userId;

    @Override
    public String toString() {
        return "PostUser{" +
                "title='" + title + '\'' +
                ", content='" + contents + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public PostUser() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PostUser(String title, String contents, String userId) {
        this.title = title;
        this.contents = contents;
        this.userId = userId;
    }
}

class User {
    String userId;
    Long id;
    String title;
    boolean completed;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}

