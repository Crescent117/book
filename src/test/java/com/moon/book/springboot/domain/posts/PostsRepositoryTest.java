package com.moon.book.springboot.domain.posts;

import org.assertj.core.api.AbstractBigDecimalAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {
    private PostsRepository postsRepository;
    @Autowired
    public PostsRepositoryTest(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    //junit4 @After
    //junit5 @AfterEach
    @AfterEach
    public void cleanup(){
        postsRepository.deleteAll();
    }
    @Test
    public void 게시글저장_불러오기(){
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("moon@gmail.com")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

}