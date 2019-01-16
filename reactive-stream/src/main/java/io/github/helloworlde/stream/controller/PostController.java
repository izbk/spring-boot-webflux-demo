package io.github.helloworlde.stream.controller;

import io.github.helloworlde.stream.model.OnlineAmount;
import io.github.helloworlde.stream.model.Post;
import io.github.helloworlde.stream.repository.PostRepository;
import io.github.helloworlde.stream.util.PostGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author HelloWood
 * @date 2019-01-08 15:22
 */
@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostGenerator postGenerator;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("")
    public Flux<Post> list() {
        return postRepository.findAll();
    }

    @GetMapping(value = "/online/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<OnlineAmount> online() {
        return postGenerator.fetchPostStream(Duration.ofSeconds(1))
                .share();
    }

}