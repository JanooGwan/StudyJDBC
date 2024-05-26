package StudyJDBC.springjdbc.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import StudyJDBC.springjdbc.domain.Board;

@Repository
public class BoardRepositoryMemory implements BoardRepository {
    private final JdbcTemplate jdbcTemplate;

    private static final Map<Long, Board> boards = new HashMap<>();
    private static final AtomicLong autoincrement = new AtomicLong(1);

    private final RowMapper<Board> boardRowMapper = (rs, rowNum) -> new Board(
            rs.getLong("id"),
            rs.getString("name")
    );

    @Autowired
    public BoardRepositoryMemory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Board> findAll() {
        return boards.entrySet().stream()
                .map(it -> {
                    Board board = it.getValue();
                    board.setId(it.getKey());
                    return board;
                }).toList();
    }

    @Override
    public Board findById(Long id) {
        return boards.getOrDefault(id, null);
    }


    public void insert(Board board) {
        boards.put(autoincrement.getAndIncrement(), board);
    }

}
