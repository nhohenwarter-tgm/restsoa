package brinnichHohenwarter.soa.localhost.soa.search;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import brinnichHohenwarter.db.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SearchRepository {

    private SearchJDBCTemplate searchJDBCTemplate;

    @PostConstruct
    public void initData() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        this.searchJDBCTemplate = (SearchJDBCTemplate)context.getBean("studentJDBCTemplate");
    }

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