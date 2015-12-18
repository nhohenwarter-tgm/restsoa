package brinnichHohenwarter;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @RequestMapping("/add")
    public boolean add(@RequestParam(value="email") String email, @RequestParam(value="bio") String bio){
        return true;
    }

    @RequestMapping("/edit")
    public Search edit(@RequestParam(value="email") String email, @RequestParam(value="bio") String bio){
        return new Search("asd","asd");
    }

    @RequestMapping("/delete")
    public boolean delete(@RequestParam(value="email") String email){
        return true;
    }
}
