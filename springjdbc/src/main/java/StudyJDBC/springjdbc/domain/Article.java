package StudyJDBC.springjdbc.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Article {
    private Long id;
    private Long writerId;
    private Long boardId;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Article(Long writerId, Long boardId, String title, String content, LocalDateTime createdDate)
    {
        this.writerId = writerId;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }

    public Article(Long id, Long writerId, Long boardId, String title, String content, LocalDateTime createdDate)
    {
        this.id = id;
        this.writerId = writerId;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }


    public void update(Long boardId, String title, String content, String modifiedDate) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.modifiedDate = LocalDateTime.parse(modifiedDate);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getId() {
        return id;
    }

    public Long getWriterId() {
        return writerId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content=content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

}
