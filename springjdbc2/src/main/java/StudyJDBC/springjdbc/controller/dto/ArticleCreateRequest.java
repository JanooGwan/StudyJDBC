package StudyJDBC.springjdbc.controller.dto;

public record ArticleCreateRequest(Long writerId, Long boardId, String title, String description) {

}