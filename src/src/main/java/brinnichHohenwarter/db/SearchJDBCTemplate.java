package brinnichHohenwarter.db;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

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
    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    /**
     * Erstellt einen neuen Datensatz
     * @param email Email
     * @param bio Bio
     */
    @Override
    public boolean create(String email, String bio) {
        String SQL = "insert into search (email, bio) values (?, ?)";
        try {
            jdbcTemplateObject.update(SQL, email, bio);
        }catch(DataAccessException e){
            return false;
        }

        System.out.println("Created Record Email = " + email + " Bio = " + bio);
        return true;
    }

    /**
     * Holt einen bestimmten Datensatz aus der Datenbank
     * @param email Email
     * @return Search
     */
    @Override
    public Search getPerson(String email) {
        String SQL = "select * from search where email = ?";
        Search search = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{email}, new SearchMapper());
        return search;
    }

    /**
     * Holt Datensaetze aus der Datenbank, die einen bestimmten String enthalten
     * @param s Suchbegriff
     * @return Liste an gefundenen Datensaetzen
     */
    @Override
    public List<Search> listPersonsFilterBy(String s) {
        String SQL = "select * from search where lower(email) like lower('%"+s+"%') or lower(bio) like lower('%"+s+"%')";
        List<Search> search = jdbcTemplateObject.query(SQL, new SearchMapper());
        return search;
    }

    /**
     * Gibt max-min Datensaetze aus der nach E-Mail Adressen geordneten Tabelle zurueck, von Reihennummer min ausgehend,
     * die einen bestimmten String enthalten
     * @param s Suchbegriff
     * @param min Reihennummer des ersten Datensatzes
     * @param max Reihennummer des letzten Datensatzes
     * @return Liste an gefundenen Datensaetzen
     */
    @Override
    public List<Search> listPersonsFilterBy(String s, int min, int max) {
        String SQL = "select * from search where lower(email) like lower('%"+s+"%') or lower(bio) like lower('%"+s+"%') order by email limit ?, ?";
        List<Search> search = jdbcTemplateObject.query(SQL,
                new Object[]{min, max-min}, new SearchMapper());
        return search;
    }

    /**
     * Gibt alle Datensaetze aus der Tabelle zurueck
     * @return Liste aller Datensaetze
     */
    @Override
    public List<Search> listPersons() {
        String SQL = "select * from search";
        List <Search> search = jdbcTemplateObject.query(SQL,
                new SearchMapper());
        return search;
    }

    /**
     * Gibt max-min Datensaetze aus der nach E-Mail Adressen geordneten Tabelle zurueck, von Reihennummer min ausgehend
     * @param min Reihennummer des ersten Datensatzes
     * @param max Reihennummer des letzten Datensatzes
     * @return Liste der Datensaetze
     */
    @Override
    public List<Search> listPersons(int min, int max){
        String SQL = "select * from search order by email limit ?, ?";
        List <Search> search = jdbcTemplateObject.query(SQL,
                new Object[]{min, max-min}, new SearchMapper());
        return search;
    }

    /**
     * Loescht einen Datensatz anhand der uebergebenen E-Mail
     * Adresse
     * @param email Email
     */
    @Override
    public boolean delete(String email){
        String SQL = "delete from search where email = ?";
        int rows = 0;
        try{
            rows = jdbcTemplateObject.update(SQL, email);
        }catch(DataAccessException e){
            return false;
        }
        if(rows <= 0){
            return false;
        }
        System.out.println("Deleted Record with Email = " + email);
        return true;
    }

    /**
     * Updated die Bio anhand der uebergebenen Email Adresse
     * @param email Email
     * @param bio Bio
     */
    @Override
    public boolean update(String email, String bio){
        String SQL = "update search set bio = ? where email = ?";
        int rows = 0;
        try {
            rows = jdbcTemplateObject.update(SQL, bio, email);
        }catch(DataAccessException e){
            return false;
        }
        if(rows <= 0){
            return false;
        }
        System.out.println("Updated Record with Email = " + email );
        return true;
    }

}