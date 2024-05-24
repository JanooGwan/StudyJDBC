package StudyJDBC.springjdbc.repository;

import java.util.List;

import StudyJDBC.springjdbc.domain.Article;
public interface ArticleRepository {
    List<Article> findAll();
    Article findById(int id);
    Article insert(Article article);
    Article update(int id, Article article);
    void deleteById(int id);
}
