package com.beom.springboot.domain.posts;

import com.beom.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //스프링 부트 2.0 에서는 GenerationType.IDENTITY를 추가해야만 auto increment됨)
    private Long id;

    @Column(length = 500, nullable = false)//Column을 굳이 선언하지 않아도 됨, 하지만 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사옹
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder//생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
