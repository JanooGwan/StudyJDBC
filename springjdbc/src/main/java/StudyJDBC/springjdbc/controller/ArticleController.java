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


    @GetMapping
    public ResponseEntity getPostsInBoard(@RequestParam(name="boardId", required=false, defaultValue="4") long boardId) {
        List<ArticleResponse> articles = articleService.getBoard(boardId);
        return ResponseEntity.ok(articles);
    }
    @GetMapping("/{id}")
    public ResponseEntity getArticle(@PathVariable Long id) {
        List<ArticleResponse> article = articleService.getById(id);
        return ResponseEntity.ok(article);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ArticleResponse> createArticle(
            @RequestBody ArticleCreateRequest request
    ) {
        // System.out.println(request);
        ArticleResponse response = articleService.create(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ArticleResponse> updateArticle(
            @PathVariable Long id,
            @RequestBody ArticleUpdateRequest request
    ) {
        ArticleResponse response = articleService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(
            @PathVariable Long id
    ) {
        articleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
