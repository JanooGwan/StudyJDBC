package StudyJDBC.springjdbc.repository;

import java.util.List;
import StudyJDBC.springjdbc.domain.Board;

public interface BoardRepository {
    List<Board> findAll();
    Board findById(int id);
    void insert(Board board);
}
