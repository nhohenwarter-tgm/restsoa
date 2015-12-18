package brinnichHohenwarter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class SearchController {

    @RequestMapping(value = "/testy", method= RequestMethod.GET, produces = "text/html")
    public String read(){
        return "index";
    }

    /**
    @RequestMapping("/add")
    public boolean add(@RequestParam(value="email") String email, @RequestParam(value="bio") String bio){
        return true;
    }

    @RequestMapping("/edit")
    public boolean edit(@RequestParam(value="email") String email, @RequestParam(value="bio") String bio){
        return true;
    }

    @RequestMapping("/delete")
    public boolean delete(@RequestParam(value="email") String email){
        return true;
    }
    */
}
