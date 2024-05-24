package StudyJDBC.springjdbc.repository;

import java.util.List;

import StudyJDBC.springjdbc.domain.Article;
public interface ArticleRepository {
    List<Article> findAll();
    Article findById(Long id);
    Article insert(Article article);
    Article update(Long id, Article article);
    void deleteById(Long id);
}
