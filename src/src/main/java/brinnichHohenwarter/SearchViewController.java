package brinnichHohenwarter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchViewController {
    @RequestMapping(value = "/", method= RequestMethod.GET, produces = "text/html")
    public String read(){
        return "index";
    }

    @RequestMapping(value = "/add", method= RequestMethod.GET, produces = "text/html")
    public String add(){
        return "add";
    }
}
