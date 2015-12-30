package brinnichHohenwarter.db;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Greift mittels SQL und dem Data Mapper auf die Datenbank zu
 *
 * @author Niklas Hohenwarter
 * @version 2015-12-30
 * @see "http://www.tutorialspoint.com/spring/spring_jdbc_example.htm"
 */
public class SearchJDBCTemplate implements SearchDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    /**
     * Setzt die Datasource
     * @param dataSource DataSource
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    /**
     * Erstellt einen neuen Datensatz
     * @param email Email
     * @param bio Bio
     */
    public void create(String email, String bio) {
        String SQL = "insert into search (email, bio) values (?, ?)";

        jdbcTemplateObject.update(SQL, email, bio);
        System.out.println("Created Record Email = " + email + " Bio = " + bio);
    }

    /**
     * Holt einen bestimmten Datensatz aus der Datenbank
     * @param email Email
     * @return Search
     */
    public Search getPerson(String email) {
        String SQL = "select * from search where email = ?";
        Search search = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{email}, new SearchMapper());
        return search;
    }

    /**
     * Gibt alle Datensaetze aus der Tabelle zurueck
     * @return Liste aller Datensaetze
     */
    public List<Search> listPersons() {
        String SQL = "select * from search";
        List <Search> search = jdbcTemplateObject.query(SQL,
                new SearchMapper());
        return search;
    }

    /**
     * Loescht einen Datensatz anhand der uebergebenen E-Mail
     * Adresse
     * @param email Email
     */
    public void delete(String email){
        String SQL = "delete from search where email = ?";
        jdbcTemplateObject.update(SQL, email);
        System.out.println("Deleted Record with Email = " + email );
    }

    /**
     * Updated die Bio anhand der uebergebenen Email Adresse
     * @param email Email
     * @param bio Bio
     */
    public void update(String email, String bio){
        String SQL = "update search set bio = ? where email = ?";
        jdbcTemplateObject.update(SQL, bio, email);
        System.out.println("Updated Record with Email = " + email );
    }

}