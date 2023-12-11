package me.jiwonjo.springbootdeveloper.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jiwonjo.springbootdeveloper.domain.Article;
import me.jiwonjo.springbootdeveloper.dto.AddArticleRequest;
import me.jiwonjo.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 등록
public class BlogService {

  private final BlogRepository blogRepository;

  // 블로그 글 추가 메서드
  public Article save(AddArticleRequest request) {
    return blogRepository.save(request.toEntity());
  }

  // 블로그 글 전체 조회
  public List<Article> findAll() {
    return blogRepository.findAll();
  }

  // 블로그 글 하나 조회
  public Article findById(long id) {
    return blogRepository.findById(id)
        // findById 메서드를 사용해 ID를 받아 엔티티를 조회하고 없을 경우 IllegalArgumentException 예외를 발생 시킴
        .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
  }

  // 블로그 글 삭제
  public void delete(long id) {
    blogRepository.deleteById(id);
  }
}
