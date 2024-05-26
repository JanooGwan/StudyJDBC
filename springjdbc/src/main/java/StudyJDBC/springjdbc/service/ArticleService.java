package StudyJDBC.springjdbc.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import StudyJDBC.springjdbc.controller.dto.*;
import StudyJDBC.springjdbc.domain.*;
import StudyJDBC.springjdbc.repository.*;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public ArticleService(
            ArticleRepository articleRepository,
            MemberRepository memberRepository,
            BoardRepository boardRepository
    ) {
        this.articleRepository = articleRepository;
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
    }

    public List<ArticleResponse> getAll() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(article -> {
                    Member member = memberRepository.findById(article.getWriterId());
                    Board board = boardRepository.findById(article.getBoardId());
                    return ArticleResponse.of(article, member, board);
                }).toList();
    }

    public List<ArticleResponse> getBoard(Long id) { // 의도와 다르게 짜여진 잘못된 메소드
        List<Article> articles = articleRepository.findBoard(id);
        return articles.stream()
                .map(article -> {
                    Member member = memberRepository.findById(article.getWriterId());
                    Board board = boardRepository.findById(article.getBoardId());
                    return ArticleResponse.of(article, member, board);
                }).toList();
    }

    public ArticleResponse getById(Long id) {
        Article article = articleRepository.findById(id);
        Member member = memberRepository.findById(article.getWriterId());
        Board board = boardRepository.findById(article.getBoardId());
        return ArticleResponse.of(article, member, board);
    }

    public ArticleResponse create(ArticleCreateRequest request) {
        Article article = new Article(
                request.writerId(),
                request.boardId(),
                request.title(),
                request.description(),
                LocalDateTime.now()
        );
        Article saved = articleRepository.insert(article);
        Member member = memberRepository.findById(saved.getWriterId());
        Board board = boardRepository.findById(article.getBoardId());
        return ArticleResponse.of(article, member, board);
    }

    public ArticleResponse update(Long id, ArticleUpdateRequest request) {
        Article article = articleRepository.findById(id);
        article.update(request.boardId(), request.title(), request.description());
        Article updated = articleRepository.update(id, article);
        Member member = memberRepository.findById(updated.getWriterId());
        Board board = boardRepository.findById(article.getBoardId());
        return ArticleResponse.of(article, member, board);
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }
}
