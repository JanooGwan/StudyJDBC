package StudyJDBC.springjdbc.service;


import StudyJDBC.springjdbc.controller.dto.ArticleResponse;
import StudyJDBC.springjdbc.controller.dto.BoardResponse;
import StudyJDBC.springjdbc.domain.Article;
import StudyJDBC.springjdbc.domain.Board;
import StudyJDBC.springjdbc.domain.Member;
import StudyJDBC.springjdbc.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<BoardResponse> getAll() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream().map(BoardResponse::of).toList();
    }

    public List<BoardResponse> getById(Long id) {
        Optional<Board> boards = boardRepository.findByIdOp(id);
        return boards.stream().map(BoardResponse::of).toList();
    }

    public String getNameById(Long id) throws SQLException { // ArticleService.java 의 getBoard() 메소드와 유사
        String boards = boardRepository.findNameById(id);
        return boards;
    }

}
