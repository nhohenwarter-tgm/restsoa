package brinnichHohenwarter;

import java.util.List;
import javax.sql.DataSource;

/**
 * Zugriffsmoeglichkeiten auf die Datenbasis
 *
 * @author Niklas Hohenwarter
 * @version 2015-12-30
 */
public interface SearchDAO {
    /**
     * Initialisiert die Datenbankverbindung
     */
    public void setDataSource(DataSource ds);
    /**
     * Hiermit koennen neue Datensaetze eingefuegt werden
     */
    public void create(String email, String bio);
    /**
     * Abfrage eines einzelnen Datensatzes anhand der Email Adresse
     */
    public Search getPerson(String email);
    /**
     * Auflistung aller Datensaetze
     */
    public List<Search> listStudents();
    /**
     * Loechen eines Datensatzes
     */
    public void delete(String email);
    /**
     * Zum veraendern eines Datensatzes
     */
    public void update(String email, String bio);
}