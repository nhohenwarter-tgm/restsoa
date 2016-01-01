package brinnichHohenwarter;

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

@Controller
public class SearchViewController {

    private SearchJDBCTemplate searchJDBCTemplate;

    public SearchViewController(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        this.searchJDBCTemplate = (SearchJDBCTemplate)context.getBean("studentJDBCTemplate");
    }

    @RequestMapping(value = "/", method= RequestMethod.GET, produces = "text/html")
    public String index(){
        return "redirect:page=1";
    }

    @RequestMapping(value = "/page={page}", method= RequestMethod.GET, produces = "text/html")
    public String showPersons(@PathVariable int page, Model model){
        if(page <= 0){
            return "redirect:page=1";
        }
        List<Search> persons = this.searchJDBCTemplate.listPersons(page*100-100, page*100);
        model.addAttribute("persons", persons);
        return "index";
    }

    @RequestMapping(value = "/search='{search}'/page={page}", method= RequestMethod.GET, produces = "text/html")
    public String showPersonsFilter(@PathVariable String search, @PathVariable int page, Model model){
        if(page <= 0){
            return "redirect:search="+search+"/page=1";
        }
        List<Search> persons = this.searchJDBCTemplate.listPersonsFilterBy(search, page * 100 - 100, page * 100);
        model.addAttribute("persons", persons);
        return "index";
    }

    @RequestMapping(value = "/add", method= RequestMethod.GET, produces = "text/html")
    public String add(){
        return "add";
    }

    @RequestMapping(value = "/edit/'{email}'", method= RequestMethod.GET, produces = "text/html")
    public String edit(@PathVariable String email, Model model){
        System.out.println(email);
        Search person = this.searchJDBCTemplate.getPerson(email);
        model.addAttribute("person", person);
        return "edit";
    }
}
