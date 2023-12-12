package me.jiwonjo.springbootdeveloper.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.jiwonjo.springbootdeveloper.domain.Article;
import me.jiwonjo.springbootdeveloper.dto.ArticleListViewResponse;
import me.jiwonjo.springbootdeveloper.dto.ArticleViewResponse;
import me.jiwonjo.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

  private final BlogService blogService;

  @GetMapping("/articles")
  public String getArticles(Model model) {
    List<ArticleListViewResponse> articles = blogService.findAll().stream()
        .map(ArticleListViewResponse::new).toList();
    model.addAttribute("articles", articles);

    return "articleList";
  }

  @GetMapping("/articles/{id}")
  public String getArticle(@PathVariable Long id, Model model){
    Article article = blogService.findById(id);
    model.addAttribute("article", new ArticleViewResponse(article));

    return "article";
  }
}
