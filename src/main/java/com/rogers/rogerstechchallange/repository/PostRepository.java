package com.rogers.rogerstechchallange.repository;

import com.rogers.rogerstechchallange.entity.Post;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PostRepository extends ReactiveMongoRepository<Post, String> {
}
