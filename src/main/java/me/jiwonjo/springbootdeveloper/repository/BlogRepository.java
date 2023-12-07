package me.jiwonjo.springbootdeveloper.repository;

import me.jiwonjo.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {

}
