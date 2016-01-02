package brinnichHohenwarter.rest;

import brinnichHohenwarter.db.Search;
import brinnichHohenwarter.db.SearchJDBCTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

/**
 * Stellt einen einfachen RestController dar, der die URLs des Restful Webservices verwaltet
 *
 * @author Selina Brinnich
 * @version 2016-01-02
 */
@RestController
public class SearchController {

    private SearchJDBCTemplate searchJDBCTemplate;

    /**
     * Initialisiert die Datenbankverbindung
     */
    public SearchController(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        this.searchJDBCTemplate = (SearchJDBCTemplate)context.getBean("studentJDBCTemplate");
    }

    /**
     * Fügt ein per POST-Request übergebenes Search-Objekt in die Datenbank hinzu
     * @param person ein JSON-String mit den Attributen "email" und "bio"
     * @return ein JSON-String mit dem Attribut "success": entweder true oder false, je nachdem, ob das Einfügen
     *           in die Datenbank erfolgreich war oder nicht
     */
    @RequestMapping(value="/api/add", method= RequestMethod.POST, produces = "application/json")
    public String addGui(@RequestBody Search person){
        return "{\"success\":"+this.searchJDBCTemplate.create(person.getEmail(), person.getBio())+"}";
    }

    /**
     * Fügt ein per GET-Request übergebenes Search-Objekt in die Datenbank hinzu
     * @param email die E-Mail des zu erstellenden Objektes
     * @param bio die Biografie des zu erstellenden Objektes
     * @return ein String, der angibt, ob das Einfügen in die Datenbank erfolgreich war oder nicht
     */
    @RequestMapping(value="/api/add/'{email}'/'{bio}'", method= RequestMethod.GET)
    public String add(@PathVariable String email, @PathVariable String bio){
        boolean res = this.searchJDBCTemplate.create(email, bio);
        if(res){
            return "Person successfully inserted!";
        }else{
            return "An error occured! Person could not be inserted!";
        }
    }

    /**
     * Verändert ein per PUT-Request übergebenes Search-Objekt in der Datenbank
     * @param person ein JSON-String mit den Attributen "email" und "bio" ("email" muss eine bereits in der Datenbank
     *               bestehende E-Mail Adresse sein, bei der die "bio" verändert werden soll)
     * @return ein JSON-String mit dem Attribut "success": entweder true oder false, je nachdem, ob das Ändern
     *           in der Datenbank erfolgreich war oder nicht
     */
    @RequestMapping(value="/api/edit", method=RequestMethod.PUT, produces = "application/json")
    public String editGui(@RequestBody Search person){
        return "{\"success\":"+this.searchJDBCTemplate.update(person.getEmail(), person.getBio())+"}";
    }

    /**
     * Verändert ein per GET-Request übergebenes Search-Objekt in der Datenbank
     * @param email die E-Mail des zu verändernden Objektes
     * @param bio die neue Biografie der Person mit übergebener E-Mail Adresse
     * @return ein String, der angibt, ob das Ändern in der Datenbank erfolgreich war oder nicht
     */
    @RequestMapping(value="/api/edit/'{email}'/'{bio}'", method= RequestMethod.GET)
    public String edit(@PathVariable String email, @PathVariable String bio){
        boolean res = this.searchJDBCTemplate.update(email, bio);
        if(res){
            return "Person successfully updated!";
        }else{
            return "An error occured! Person could not be updated!";
        }
    }

    /**
     * Löscht ein per DELETE-Request übergebenes Search-Objekt
     * @param email die E-Mail Adresse des zu löschenden Objektes
     * @return ein JSON-String mit dem Attribut "success": entweder true oder false, je nachdem, ob das Löschen
     *           in der Datenbank erfolgreich war oder nicht
     */
    @RequestMapping(value="/api/delete/'{email}'", method=RequestMethod.DELETE, produces = "application/json")
    public String deleteGui(@PathVariable String email) {
        return "{\"success\":"+this.searchJDBCTemplate.delete(email)+"}";
    }

    /**
     * Löscht ein per GET-Request übergebenes Search-Objekt
     * @param email die E-Mail Adresse des zu löschenden Objektes
     * @return ein String, der angibt, ob das Löschen in der Datenbank erfolgreich war oder nicht
     */
    @RequestMapping(value="/api/delete/'{email}'", method= RequestMethod.GET)
    public String delete(@PathVariable String email){
        boolean res = this.searchJDBCTemplate.delete(email);
        if(res){
            return "Person successfully deleted!";
        }else{
            return "An error occured! Person could not be deleted!";
        }
    }
}
