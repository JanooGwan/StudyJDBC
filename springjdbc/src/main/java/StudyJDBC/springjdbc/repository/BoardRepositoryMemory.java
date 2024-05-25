package StudyJDBC.springjdbc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import StudyJDBC.springjdbc.domain.Board;

@Repository
public class BoardRepositoryMemory implements BoardRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BoardRepositoryMemory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Board> findAll() {
        String sql = "SELECT * FROM boards";
        return jdbcTemplate.query(sql, new BoardRowMapper());
    }

    @Override
    public Board findById(Long id) {
        String sql = "SELECT * FROM boards WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BoardRowMapper(), id);
    }



    public void save(Board board) {
        String sql = "INSERT INTO boards (name) VALUES (?, ?)";
        jdbcTemplate.update(sql, board.getName());
    }

    private static class BoardRowMapper implements RowMapper<Board> {
        @Override public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
            Board board = new Board();
            board.setId(rs.getLong("id"));
            board.setName(rs.getString("name"));
            return board;
        }
    }
}
