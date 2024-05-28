package StudyJDBC.springjdbc.controller;

import java.sql.SQLException;
import java.util.List;

import StudyJDBC.springjdbc.controller.dto.BoardResponse;
import StudyJDBC.springjdbc.domain.Board;
import StudyJDBC.springjdbc.service.BoardService;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import StudyJDBC.springjdbc.controller.dto.ArticleResponse;
import StudyJDBC.springjdbc.service.ArticleService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class PageController {
    private final ArticleService articleService;
    private final BoardService boardService;

    public PageController(ArticleService articleService, BoardService boardService) {
        this.articleService = articleService;
        this.boardService = boardService;
    }



    @GetMapping("/post")
    public String getPostsInBoard()  {
        return "board";
    }

    @GetMapping("/posts")
    public String getPostsInBoard(@RequestParam(name="boardId", required=true) long boardId, Model model) throws SQLException {
        List<ArticleResponse> articles = articleService.getBoard(boardId);
        model.addAttribute("posts", articles);
        model.addAttribute("boardName", boardService.getNameById(boardId));
        return "article";
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity getBoard(@PathVariable Long id) {
        List<BoardResponse> board = boardService.getById(id);
        return ResponseEntity.ok(board);
    }
}
