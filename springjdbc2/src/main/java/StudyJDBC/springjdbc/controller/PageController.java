package StudyJDBC.springjdbc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import StudyJDBC.springjdbc.controller.dto.ArticleResponse;
import StudyJDBC.springjdbc.service.ArticleService;

@Controller
public class PageController {
    private final ArticleService articleService;

    public PageController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        List<ArticleResponse> articles = articleService.getAll();
        model.addAttribute("boardName", "자유게시판");
        model.addAttribute("posts", articles);
        return "article";
    }
}
