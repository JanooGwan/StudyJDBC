package StudyJDBC.springjdbc.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import StudyJDBC.springjdbc.domain.Article;
import StudyJDBC.springjdbc.domain.Board;

public interface BoardRepository {
    List<Board> findAll();
    public Board findById(Long id);
    public Optional<Board> findByIdOp(Long id);
    public String findNameById(Long id) throws SQLException;
    public List<Board> findByIdQR(Long id);
}
