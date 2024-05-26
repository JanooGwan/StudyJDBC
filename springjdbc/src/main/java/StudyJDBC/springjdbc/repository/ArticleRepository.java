package StudyJDBC.springjdbc.repository;

import java.util.List;
import java.util.Optional;

import StudyJDBC.springjdbc.domain.Article;
import StudyJDBC.springjdbc.domain.Board;
import StudyJDBC.springjdbc.domain.Member;

public interface ArticleRepository {
    List<Article> findAll();
    Article findById(Long id);
    Optional<Article> findByIdOp(Long id);
    List<Article> findBoard(Long id);
    Optional<Article> findByBoardIdOp(Long id);
    Article update(Long id, Article article);
    void deleteById(Long id);

    Article insert(Article article);
}
