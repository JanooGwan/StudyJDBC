package StudyJDBC.springjdbc.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import StudyJDBC.springjdbc.domain.Member;

@Repository
public class MemberRepositoryMemory implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Member> memberRowMapper = (rs, rowNum) -> new Member(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("password")
    );

    @Autowired
    public MemberRepositoryMemory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Member> findAll() {
        String sql = "SELECT * FROM member";
        return jdbcTemplate.query(sql, memberRowMapper);
    }

    @Override
    public Member findById(Long id) {
        String sql = "SELECT * FROM member WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, memberRowMapper, id);
    }



}
