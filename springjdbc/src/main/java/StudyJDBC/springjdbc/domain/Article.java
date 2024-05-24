package StudyJDBC.springjdbc.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Article {
    private int id;
    private int writerId;
    private int boardId;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Article(int writerId, int boardId, String title, String description, LocalDateTime createdDate)
    {
        this.writerId = writerId;
        this.boardId = boardId;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
    }

    public void update(int boardId, String title, String description) {
        this.boardId = boardId;
        this.title = title;
        this.description = description;
        this.modifiedDate = LocalDateTime.now();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getId() {
        return id;
    }

    public int getWriterId() {
        return writerId;
    }

    public int getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

}
