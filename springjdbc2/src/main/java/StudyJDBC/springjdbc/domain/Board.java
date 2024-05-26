package StudyJDBC.springjdbc.domain;

public class Board {
    private Long id;
    private String name;

    public Board(String name) {
        this.name = name;
    }

    public Board() {};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name=name;}
}