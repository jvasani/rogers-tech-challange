package com.rogers.rogerstechchallange.initializer;

import com.rogers.rogerstechchallange.entity.Post;
import com.rogers.rogerstechchallange.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class DataInitializer {

    @Autowired
    private final PostRepository posts;
    public DataInitializer(PostRepository posts) {
        this.posts = posts;
    }
    @EventListener(value = ContextRefreshedEvent.class)
    public void init() {
        initPosts();
    }

    private void initPosts() {
        log.info("start data initialization  ...");
        this.posts
                .deleteAll()
                .thenMany(
                        Flux
                                .just("Post one", "Post two")
                                .flatMap(
                                        title -> this.posts.save(Post.builder().title(title).content("content of " + title).build())
                                )
                )
                .log()
                .subscribe(
                        null,
                        null,
                        () -> log.info("done initialization...")
                );
    }
}
