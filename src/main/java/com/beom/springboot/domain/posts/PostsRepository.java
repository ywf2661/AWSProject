package com.beom.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//JpaRepository를 상속하면 기본적인 CRUD 메소드가 자동적으로 생성됨
//@Repository를 추가할 필요도 없음. 대신 entity클래스와 entityrepository는 같이 있어야함
public interface PostsRepository extends JpaRepository<Posts, Long> {
    
    //실제 규모가 있는 프로젝트에서는 조회용 프레임워크는 따로 씀(querydsl,jooq,MyBatis 등)
    //CUD는 SpringDataJpa를 통해 진행
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
