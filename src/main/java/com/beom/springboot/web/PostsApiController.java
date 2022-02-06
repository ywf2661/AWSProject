package com.beom.springboot.web;

import com.beom.springboot.domain.posts.PostsRepository;
import com.beom.springboot.service.posts.PostsService;
import com.beom.springboot.web.dto.PostsResponseDto;
import com.beom.springboot.web.dto.PostsSaveRequestDto;
import com.beom.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor //final이선언된 모든 필드를 인자값으로 하는 생성자를 생성 (생성자로 Bean 객체를 받도록함.)
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
