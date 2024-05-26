package StudyJDBC.springjdbc.controller.dto;

import StudyJDBC.springjdbc.domain.Article;
import StudyJDBC.springjdbc.domain.Board;
import StudyJDBC.springjdbc.domain.Member;

public record ArticleCreateRequest(Long writerId, Long boardId, String title, String description) {
    public static ArticleCreateRequest of(Article article, Board board) {
        return new ArticleCreateRequest(
                board.getId(),
                article.getId(),
                article.getTitle(),
                article.getContent()
        );
    }
}