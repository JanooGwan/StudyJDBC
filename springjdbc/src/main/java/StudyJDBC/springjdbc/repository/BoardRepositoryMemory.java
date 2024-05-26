package StudyJDBC.springjdbc.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import StudyJDBC.springjdbc.domain.Board;

@Repository
public class BoardRepositoryMemory implements BoardRepository {
    private final JdbcTemplate jdbcTemplate;

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
        String sql = "SELECT * FROM board";
        return jdbcTemplate.query(sql, boardRowMapper);
    }

    @Override
    public Board findById(Long id) {
        String sql = "SELECT * FROM board WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, boardRowMapper, id);
    }


    public void save(Board board) {
        String sql = "INSERT INTO board (name) VALUES (?, ?)";
        jdbcTemplate.update(sql, board.getName());
    }

}
