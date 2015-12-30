package brinnichHohenwarter;


import brinnichHohenwarter.db.Search;
import brinnichHohenwarter.db.SearchJDBCTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;


@RestController
public class SearchController {

    private SearchJDBCTemplate searchJDBCTemplate;

    public SearchController(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        this.searchJDBCTemplate = (SearchJDBCTemplate)context.getBean("studentJDBCTemplate");
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public boolean add(@RequestBody Search person){
        return this.searchJDBCTemplate.create(person.getEmail(), person.getBio());
    }

    @RequestMapping(value="/edit", method=RequestMethod.PUT)
    public boolean edit(@RequestBody Search person){
        return this.searchJDBCTemplate.update(person.getEmail(), person.getBio());
    }

    @RequestMapping(value="/delete/{email}", method=RequestMethod.DELETE)
    public boolean delete(@PathVariable String email) {
        return this.searchJDBCTemplate.delete(email);
    }
}
