package brinnichHohenwarter.rest;


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

    @RequestMapping(value="/api/add", method= RequestMethod.POST, produces = "application/json")
    public String addGui(@RequestBody Search person){
        return "{\"success\":"+this.searchJDBCTemplate.create(person.getEmail(), person.getBio())+"}";
    }

    @RequestMapping(value="/api/add/'{email}'/'{bio}'", method= RequestMethod.GET)
    public String add(@PathVariable String email, @PathVariable String bio){
        boolean res = this.searchJDBCTemplate.create(email, bio);
        if(res){
            return "Person successfully inserted!";
        }else{
            return "An error occured! Person could not be inserted!";
        }
    }

    @RequestMapping(value="/api/edit", method=RequestMethod.PUT, produces = "application/json")
    public String editGui(@RequestBody Search person){
        return "{\"success\":"+this.searchJDBCTemplate.update(person.getEmail(), person.getBio())+"}";
    }

    @RequestMapping(value="/api/edit/'{email}'/'{bio}'", method= RequestMethod.GET)
    public String edit(@PathVariable String email, @PathVariable String bio){
        boolean res = this.searchJDBCTemplate.update(email, bio);
        if(res){
            return "Person successfully updated!";
        }else{
            return "An error occured! Person could not be updated!";
        }
    }

    @RequestMapping(value="/api/delete/'{email}'", method=RequestMethod.DELETE, produces = "application/json")
    public String deleteGui(@PathVariable String email) {
        return "{\"success\":"+this.searchJDBCTemplate.delete(email)+"}";
    }

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
