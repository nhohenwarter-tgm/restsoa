package brinnichHohenwarter;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SearchController {

    @RequestMapping("/add")
    public boolean add(@RequestParam(value="email") String email, @RequestParam(value="bio") String bio){
        return true;
    }

    @RequestMapping("/edit")
    public boolean edit(@RequestParam(value="email") String email, @RequestParam(value="bio") String bio){
        return true;
    }

    @RequestMapping("/delete")
    public boolean delete(@RequestParam(value="email") String email) {
        return true;
    }
}
