package StudyJDBC.springjdbc.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import StudyJDBC.springjdbc.controller.dto.*;
import StudyJDBC.springjdbc.service.ArticleService;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /*
    @GetMapping
    public ResponseEntity<List<ArticleResponse>> getArticles() {
        List<ArticleResponse> response = articleService.getAll();
        return ResponseEntity.ok(response);
    }
     */

    @GetMapping
    public ResponseEntity getPostsInBoard(@RequestParam(name="boardId", required=false, defaultValue="4") long boardId, Model model) {
        List<ArticleResponse> articles = articleService.getBoard(boardId);
        return ResponseEntity.ok(articles);
    }
    @GetMapping("/{id}")
    public ResponseEntity getArticle(@PathVariable Long id) {
        List<ArticleResponse> article = articleService.getById(id);
        return ResponseEntity.ok(article);
    }

    @PostMapping
    public ResponseEntity<Void> createArticle(
            @RequestBody ArticleCreateRequest request
    ) {
        ArticleResponse response = articleService.create(request);
        return ResponseEntity.created(URI.create("/articles/" +response.id())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(
            @PathVariable Long id,
            @RequestBody ArticleUpdateRequest request
    ) {
        ArticleResponse response = articleService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateArticle(
            @PathVariable Long id
    ) {
        articleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
