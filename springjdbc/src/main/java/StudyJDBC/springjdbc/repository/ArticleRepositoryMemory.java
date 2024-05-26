package StudyJDBC.springjdbc.repository;

import StudyJDBC.springjdbc.domain.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public Article findById(Long id){return findById(id);}

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


    @Override
    public Article update(Long id, Article article) {
        return null;
    }


    public Article save(Article article) {
        jdbcTemplate.update("INSERT INTO article (title, content) VALUES (?, ?)",
                article.getTitle(), article.getContent());
        return article;
    }


    public void update(Article article) {
        String sql = "UPDATE article SET title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql, article.getTitle(), article.getContent(), article.getId());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM article WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Article insert(Article article) {
        return null;
    }

}