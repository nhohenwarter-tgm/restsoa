package brinnichHohenwarter.soa.localhost.soa.search;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import brinnichHohenwarter.db.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Datenbasis fuer den SOA Webservice
 *
 * @author Niklas Hohenwarter
 * @version 2016-01-02
 * @see "http://spring.io/guides/gs/producing-web-service/"
 */
@Component
public class SearchRepository {

    private SearchJDBCTemplate searchJDBCTemplate;

    /**
     * Initialisiert die Datenbankverbindung
     */
    @PostConstruct
    public void initData() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        this.searchJDBCTemplate = (SearchJDBCTemplate)context.getBean("studentJDBCTemplate");
    }

    /**
     * Sucht nach einer Person in der Datenbank anhand der E-Mail Adresse
     * @param email E-Mail Adresse
     * @return Personen als Liste
     */
    public List<Search> findPerson(String email) {

        List<brinnichHohenwarter.db.Search> dbresult = searchJDBCTemplate.listPersonsFilterBy(email);
        ArrayList<Search> result = new ArrayList<Search>();

        for(int i = 0; i < dbresult.size(); i++){
            Search entry = new Search();
            entry.setEmail(dbresult.get(i).getEmail());
            entry.setBio(dbresult.get(i).getBio());
            result.add(entry);
        }

        return result;
    }
}