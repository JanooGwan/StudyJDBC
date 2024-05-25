package StudyJDBC.springjdbc.repository;

import StudyJDBC.springjdbc.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*
@Repository
public class ArticleRepositoryMemory implements ArticleRepository {

    private static final Map<Long, Article> articles = new HashMap<>();
    private static final AtomicLong autoincrement = new AtomicLong(1);

    @Override
    public List<Article> findAll() {
        return articles.entrySet().stream()
                .map(it -> {
                    Article article = it.getValue();
                    article.setId(it.getKey());
                    return article;
                }).toList();
    }

    @Override
    public Article findById(Long id) {
        return articles.getOrDefault(id, null);
    }

    @Override
    public Article insert(Article article) {
        long id = autoincrement.getAndIncrement();
        articles.put(id, article);
        article.setId(id);
        return article;
    }

    @Override
    public Article update(Long id, Article article) {
        articles.put(id, article);
        article.setId(id);
        return article;
    }

    @Override
    public void deleteById(Long id) {
        articles.remove(id);
    }
}
*/

@Repository
public class ArticleRepositoryMemory implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleRepositoryMemory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Article> findAll() {
        String sql = "SELECT * FROM articles";
        return jdbcTemplate.query(sql, new ArticleRowMapper());
    }

    @Override
    public Article findById(Long id) {
        String sql = "SELECT * FROM articles WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ArticleRowMapper(), id);
    }


    @Override
    public Article update(Long id, Article article) {
        return null;
    }


    public void save(Article article) {
        String sql = "INSERT INTO articles (title, content) VALUES (?, ?)";
        jdbcTemplate.update(sql, article.getTitle(), article.getDescription());
    }


    public void update(Article article) {
        String sql = "UPDATE articles SET title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql, article.getTitle(), article.getDescription(), article.getId());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM articles WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class ArticleRowMapper implements RowMapper<Article> {
        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            Article article = new Article();
            article.setId(rs.getLong("id"));
            article.setTitle(rs.getString("title"));
            article.setDescription(rs.getString("description"));
            return article;
        }
    }
}