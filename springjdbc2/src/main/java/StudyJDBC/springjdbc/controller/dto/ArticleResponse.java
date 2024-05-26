package StudyJDBC.springjdbc.controller.dto;

import java.time.LocalDateTime;

import StudyJDBC.springjdbc.domain.Article;
import StudyJDBC.springjdbc.domain.Board;
import StudyJDBC.springjdbc.domain.Member;

public record ArticleResponse(Long id, String title, String content, String board, String writer, LocalDateTime date) {
    public static ArticleResponse of(Article article, Member member, Board board) {
        return new ArticleResponse(
                article.getId(),
                article.getTitle(),
                article.getDescription(),
                board.getName(),
                member.getName(),
                article.getCreatedDate()
        );
    }
}
