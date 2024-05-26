package StudyJDBC.springjdbc.controller.dto;

import StudyJDBC.springjdbc.domain.Article;
import StudyJDBC.springjdbc.domain.Board;
import StudyJDBC.springjdbc.domain.Member;

public record ArticleCreateRequest(Long author_id, Long board_id, String title, String content) {
}