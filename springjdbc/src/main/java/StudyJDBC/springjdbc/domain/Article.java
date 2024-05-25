package StudyJDBC.springjdbc.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Article {
    private Long id;
    private Long writerId;
    private Long boardId;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Article(Long writerId, Long boardId, String title, String description, LocalDateTime createdDate)
    {
        this.writerId = writerId;
        this.boardId = boardId;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
    }

    public Article() {
    }

    public void update(Long boardId, String title, String description) {
        this.boardId = boardId;
        this.title = title;
        this.description = description;
        this.modifiedDate = LocalDateTime.now();
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

}
