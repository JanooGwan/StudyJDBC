package StudyJDBC.springjdbc.repository;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import StudyJDBC.springjdbc.domain.Article;
import com.mysql.cj.protocol.Resultset;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import StudyJDBC.springjdbc.domain.Board;

@Repository
public class BoardRepositoryMemory implements BoardRepository {
    private final JdbcTemplate jdbcTemplate;
    String url = "jdbc:mysql://localhost:3306/bcsd?serverTimezone=Asia/Seoul";
    Connection con = DriverManager.getConnection(url, "janoogwan39", "dnldtkd15^^");
    ResultSet rs;

    private static final Map<Long, Board> boards = new HashMap<>();

    private final RowMapper<Board> boardRowMapper = (rs, rowNum) -> new Board(
            rs.getLong("id"),
            rs.getString("name")
    );



    @Autowired
    public BoardRepositoryMemory(JdbcTemplate jdbcTemplate) throws SQLException {
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
    public Optional<Board> findByIdOp(Long id) {
        List<Board> results = jdbcTemplate.query("SELECT * FROM board WHERE id = ?", boardRowMapper, id);
        return results.stream().findAny();
    }

    @Override
    public List<Board> findByIdQR(Long id) {
        List<Board> results = jdbcTemplate.query("SELECT * FROM board WHERE id = ?", boardRowMapper, id);
        return results;
    }

    @Override
    public String findNameById(Long id) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM board WHERE id = ?");
        stmt.setLong(1, Long.valueOf(id).intValue());
        rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getString("name");
        } else {
            return null;
        }
    }

    @Override
    public Board findById(Long id) {
        return boards.getOrDefault(id, null);
    }


}
