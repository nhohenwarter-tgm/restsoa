package brinnichHohenwarter.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Repreasentiert einen Datensatz bzw. holt diesen aus der Datenbank
 * und speichert ihn als Search Objekt
 *
 * @author Niklas Hohenwarter
 * @version 2015-12-30
 * @see "http://www.tutorialspoint.com/spring/spring_jdbc_example.htm"
 */
public class SearchMapper implements RowMapper<Search> {
    /**
     * Mapt eine Row auf ein Search Objekt
     * @param rs Result Set der Quary
     * @param rowNum Nummer der Zeile in der DB
     * @return Search Objekt
     * @throws SQLException
     */
    public Search mapRow(ResultSet rs, int rowNum) throws SQLException {
        Search search = new Search();
        search.setEmail(rs.getString("email"));
        search.setBio(rs.getString("bio"));
        return search;
    }
}