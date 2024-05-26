package StudyJDBC.springjdbc.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import StudyJDBC.springjdbc.controller.dto.*;
import StudyJDBC.springjdbc.domain.*;
import StudyJDBC.springjdbc.repository.*;
import org.springframework.transaction.annotation.Transactional;

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

    public List<ArticleResponse> getById(Long id) {
        Optional<Article> articles = articleRepository.findByIdOp(id);
        return articles.stream()
                .map(article -> {
                    Member member = memberRepository.findById(article.getWriterId());
                    Board board = boardRepository.findById(article.getBoardId());
                    return ArticleResponse.of(article, member, board);
                }).toList();
    }

    @Transactional
    public ArticleResponse create(ArticleCreateRequest request) {
        // System.out.println(request.writerId());
        Article article = new Article(
                request.author_id(),
                request.board_id(),
                request.title(),
                request.content(),
                LocalDateTime.now()
        );

        Article saved = articleRepository.insert(article);
        Member member = memberRepository.findById(saved.getWriterId());
        Board board = boardRepository.findById(article.getBoardId());
        return ArticleResponse.of(article, member, board);
    }

    @Transactional
    public ArticleResponse update(Long id, ArticleUpdateRequest request) {
        Article article = articleRepository.findById(id);
        article.setModifiedDate(LocalDateTime.now());
        article.update(request.board_id(), request.title(), request.content(), LocalDateTime.now().toString());
        Article updated = articleRepository.update(id, article);
        Member member = memberRepository.findById(updated.getWriterId());
        Board board = boardRepository.findById(article.getBoardId());
        return ArticleResponse.of(article, member, board);
    }

    @Transactional
    public void delete(Long id) {
        articleRepository.deleteById(id);
    }
}
