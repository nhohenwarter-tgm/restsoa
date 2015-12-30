package brinnichHohenwarter.db;

/**
 * Repraesentiert die Tabelle search in der Datenbasis
 *
 * @author Niklas Hohenwarter
 * @version 2015-12-30
 * @see "http://www.tutorialspoint.com/spring/spring_jdbc_example.htm"
 */
public class Search {
    private String email;
    private String bio;

    /**
     * Getter fuer die E-Mail Adresse
     * @return E-Mail Adresse
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter fuer die Bio
     * @return Bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * Setter fuer die E-Mail Adresse
     * @param email E-Mail Adresse
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter fuer die Bio
     * @param bio Bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }
}
