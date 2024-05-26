package StudyJDBC.springjdbc.repository;

import StudyJDBC.springjdbc.domain.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;



@Repository
public class ArticleRepositoryMemory implements ArticleRepository {

    private static final Map<Long, Article> articles = new HashMap<>();

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Article> articleRowMapper = (rs, rowNum) -> new Article(
            rs.getLong("id"),
            rs.getLong("author_id"),
            rs.getLong("board_id"),
            rs.getString("title"),
            rs.getString("content"),
            rs.getObject("created_date", LocalDateTime.class)
    );


    public ArticleRepositoryMemory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Article findById(Long id) {
        Article results = jdbcTemplate.queryForObject("SELECT * FROM article WHERE id = ?", articleRowMapper, id);
        return results;
    }

    @Override
    public List<Article> findAll() {
        String sql = "SELECT * FROM article";
        return jdbcTemplate.query(sql, articleRowMapper);
    }

    @Override
    public Optional<Article> findByIdOp(Long id) {
        List<Article> results = jdbcTemplate.query("SELECT * FROM article WHERE id = ?", articleRowMapper, id);
        return results.stream().findAny();
    }

    public List <Article> findBoard(Long id) {
        List<Article> results = jdbcTemplate.query("SELECT * FROM article WHERE board_id = ?", articleRowMapper, id);
        return results;
    }

    @Override
    public Optional<Article> findByBoardIdOp(Long id) {
        List<Article> results = jdbcTemplate.query("SELECT * FROM article WHERE board_id = ?", articleRowMapper, id);
        return results.stream().findAny();
    }

    @Transactional
    @Override
    public Article update(Long id, Article article) {
        String sql = "UPDATE article SET board_id = ?, title = ?, content = ? WHERE id = ?";
        // System.out.println(article.getContent());
        jdbcTemplate.update(sql, article.getBoardId(), article.getTitle(), article.getContent(), article.getId());
        return article;
    }

    @Transactional
    public Article insert(Article article) {
        jdbcTemplate.update("INSERT INTO article (author_id, board_id, title, content) VALUES (?, ?, ?, ?)",
                article.getWriterId(), article.getBoardId(), article.getTitle(), article.getContent());
        return article;
    }


    @Transactional
    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM article WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


}