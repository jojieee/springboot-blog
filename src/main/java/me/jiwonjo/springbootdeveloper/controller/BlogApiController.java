package me.jiwonjo.springbootdeveloper.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jiwonjo.springbootdeveloper.domain.Article;
import me.jiwonjo.springbootdeveloper.dto.AddArticleRequest;
import me.jiwonjo.springbootdeveloper.dto.ArticleResponse;
import me.jiwonjo.springbootdeveloper.dto.UpdateArticleRequest;
import me.jiwonjo.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {

  private final BlogService blogService;


  @PostMapping("/api/articles") // HTTP 메서드가 POST일 때 전달받은 URL과 동일하면 메서드로 매핑
  public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
    Article savedArticle = blogService.save(request); // @RequestBody로 요청 본문 값 매핑
    return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle); // 요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
  }

  @GetMapping("/api/articles")
  public ResponseEntity<List<ArticleResponse>> findAllArticles() {
    List<ArticleResponse> articles = blogService.findAll().stream().map(ArticleResponse::new).toList();

    return ResponseEntity.ok().body(articles);
  }

  @GetMapping("/api/articles/{id}")
  public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
    Article article = blogService.findById(id);

    return ResponseEntity.ok().body(new ArticleResponse(article));
  }

  @DeleteMapping("/api/articles/{id}")
  public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
    blogService.delete(id);

    return ResponseEntity.ok().build();
  }

  @PutMapping("/api/articles/{id}")
  public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody
      UpdateArticleRequest request) {
    Article updateArticle = blogService.update(id, request);

    return ResponseEntity.ok().body(updateArticle);
  }
}
