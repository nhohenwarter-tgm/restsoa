package brinnichHohenwarter.db;

import javax.sql.DataSource;
import java.util.List;

/**
 * Zugriffsmoeglichkeiten auf die Datenbasis
 *
 * @author Niklas Hohenwarter
 * @version 2015-12-30
 * @see "http://www.tutorialspoint.com/spring/spring_jdbc_example.htm"
 */
public interface SearchDAO {
    /**
     * Initialisiert die Datenbankverbindung
     */
    public void setDataSource(DataSource ds);
    /**
     * Hiermit koennen neue Datensaetze eingefuegt werden
     */
    public boolean create(String email, String bio);
    /**
     * Abfrage eines einzelnen Datensatzes anhand der Email Adresse
     */
    public Search getPerson(String email);
    /**
     * Auflistung aller Datensaetze
     */
    public List<Search> listPersons();
    /**
     * Loechen eines Datensatzes
     */
    public boolean delete(String email);
    /**
     * Zum veraendern eines Datensatzes
     */
    public boolean update(String email, String bio);
}