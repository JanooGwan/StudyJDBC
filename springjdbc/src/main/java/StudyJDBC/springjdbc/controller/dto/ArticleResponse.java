package StudyJDBC.springjdbc.controller.dto;

import java.time.LocalDateTime;

import StudyJDBC.springjdbc.domain.Article;
import StudyJDBC.springjdbc.domain.Board;
import StudyJDBC.springjdbc.domain.Member;

public record ArticleResponse(Long id, Long author_id, Long board_id, String title, String content, LocalDateTime created_date, LocalDateTime modified_date) {
    public static ArticleResponse of(Article article, Member member, Board board) {
        return new ArticleResponse(
                article.getId(),
                article.getWriterId(),
                article.getBoardId(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedDate(),
                article.getModifiedDate()
        );
    }
}
