package brinnichHohenwarter;

public class Search {
    private final String email;
    private final String bio;

    public Search(String email, String bio) {
        this.email = email;
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public String getContent() {
        return bio;
    }
}
