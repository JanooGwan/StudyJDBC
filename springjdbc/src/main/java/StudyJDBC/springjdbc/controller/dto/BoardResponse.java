package StudyJDBC.springjdbc.controller.dto;

import StudyJDBC.springjdbc.domain.Board;


public record BoardResponse(Long id,  String name) {
    public static BoardResponse of(Board board) {
        return new BoardResponse(
                board.getId(),
                board.getName()
        );
    }
}
