package StudyJDBC.springjdbc.domain;

public class Member {
    private Long id;
    private String name;
    private String email;
    private String password;

    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Member() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName() { this.name = name; }

    public String getName() {
        return name;
    }

    public void setEmail(String email) { this.email = email; }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) { this.password=password;}

    public String getPassword() {
        return password;
    }
}
