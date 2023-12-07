package me.jiwonjo.springbootdeveloper.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // 엔티티로 지정
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
  @Id // id 필드를 기본키로 지정
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동으로 1씩 증가
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(name = "title", nullable = false) // title이라는 not null 컬럼과 매핑
  private String title;

  @Column(name = "content", nullable = false)
  private  String content;

  @Builder // 빌더 패턴으로 객체 생성
  public Article(String title, String content) {
    this.title = title;
    this.content = content;
  }

  protected Article() { // 기본 생성자

  }

  // 게터
  // @Getter, @NoArgsConstructor 어노테이션으로 하단의 반복되는 코드를 사용하지 않고 접근자 메서드를 만들수 있게 되었음.

//  public Long getId() {
//    return id;
//  }
//
//  public String getTitle() {
//    return title;
//  }
//
//  public String getContent() {
//    return content;
//  }
}
