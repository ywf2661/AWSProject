package com.beom.springboot.service.posts;

import com.beom.springboot.domain.posts.Posts;
import com.beom.springboot.domain.posts.PostsRepository;
import com.beom.springboot.web.dto.PostsListResponseDto;
import com.beom.springboot.web.dto.PostsResponseDto;
import com.beom.springboot.web.dto.PostsSaveRequestDto;
import com.beom.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id,PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }


    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" +id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly=true) //readOnly는 트랜잭션 범위는 유지하고 조회속도는 올려준다.
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) //map(posts -> new PostsListResponseDto(posts)) : repository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListReponseDto로 변화-> List로 반환
                .collect(Collectors.toList());
    }
}
