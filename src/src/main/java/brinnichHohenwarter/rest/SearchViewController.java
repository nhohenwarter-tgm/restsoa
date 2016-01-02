package brinnichHohenwarter.rest;

import brinnichHohenwarter.db.Search;
import brinnichHohenwarter.db.SearchJDBCTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Stellt einen einfachen Controller dar, der die URLs für die GUI-Schnittstelle des Restful Webservices verwaltet
 *
 * @author Selina Brinnich
 * @version 2016-01-02
 */
@Controller
public class SearchViewController {

    private SearchJDBCTemplate searchJDBCTemplate;

    /**
     * Initialisiert die Datenbankverbindung
     */
    public SearchViewController(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        this.searchJDBCTemplate = (SearchJDBCTemplate)context.getBean("studentJDBCTemplate");
    }

    /**
     * Leitet auf Seite 1 der Anzeige weiter
     * @return ein Redirect-String zu "/page=1"
     */
    @RequestMapping(value = "/", method= RequestMethod.GET, produces = "text/html")
    public String index(){
        return "redirect:page=1";
    }

    /**
     * Gibt ein HTML-Template zurück, das eine bestimmte Seite (100 Einträge pro Seite) anzeigt.
     * Sollte die Seite kleiner als 1 sein, wird auf Seite 1 weitergeleitet.
     * @param page die anzuzeigende Seite
     * @param model ein Model, das die Variablen für das HTML-Template verwaltet
     * @return ein String, der auf ein HTML-Template verweist
     */
    @RequestMapping(value = "/page={page}", method= RequestMethod.GET, produces = "text/html")
    public String showPersons(@PathVariable int page, Model model){
        if(page <= 0){
            return "redirect:page=1";
        }
        List<Search> persons = this.searchJDBCTemplate.listPersons(page*100-100, page*100);
        model.addAttribute("persons", persons);
        return "index";
    }

    /**
     * Gibt ein HTML-Template zurück, das eine bestimmte Seite (100 Einträge pro Seite) anzeigt, wobei die Einträge
     * vorher durch einen Such-String gefiltert werden.
     * Sollte die Seite kleiner als 1 sein, wird auf Seite 1 weitergeleitet.
     * @param search ein String, nach dem in der Datenbank gesucht werden soll
     * @param page die anzuzeigende Seite
     * @param model ein Model, das die Variablen für das HTML-Template verwaltet
     * @return ein String, der auf ein HTML-Template verweist
     */
    @RequestMapping(value = "/search='{search}'/page={page}", method= RequestMethod.GET, produces = "text/html")
    public String showPersonsFilter(@PathVariable String search, @PathVariable int page, Model model){
        if(page <= 0){
            return "redirect:search="+search+"/page=1";
        }
        List<Search> persons = this.searchJDBCTemplate.listPersonsFilterBy(search, page * 100 - 100, page * 100);
        model.addAttribute("persons", persons);
        return "index";
    }

    /**
     * Gibt ein HTML-Template zurück, in dem ein neuer Eintrag für die Datenbank erstellt werden kann.
     * @return ein String, der auf ein HTML-Template verweist
     */
    @RequestMapping(value = "/add", method= RequestMethod.GET, produces = "text/html")
    public String add(){
        return "add";
    }

    /**
     * Gibt ein HTML-Template zurück, in dem die Biografie eines bereits existierenden Eintrages in der Datenbank
     * bearbeitet werden kann.
     * @param email die E-Mail Adresse des zu Bearbeitenden Eintrages
     * @param model ein Model, das die Variablen für das HTML-Template verwaltet
     * @return ein String, der auf ein HTML-Template verweist
     */
    @RequestMapping(value = "/edit/'{email}'", method= RequestMethod.GET, produces = "text/html")
    public String edit(@PathVariable String email, Model model){
        Search person = this.searchJDBCTemplate.getPerson(email);
        model.addAttribute("person", person);
        return "edit";
    }
}
