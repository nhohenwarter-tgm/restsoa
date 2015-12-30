package brinnichHohenwarter;

import brinnichHohenwarter.db.Search;
import brinnichHohenwarter.db.SearchJDBCTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
/**
 * Startet die App
 */
public class Application {

    /**
     * Startet die App
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);


        // Database Test
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");

        SearchJDBCTemplate searchJDBCTemplate =
                (SearchJDBCTemplate)context.getBean("studentJDBCTemplate");

        searchJDBCTemplate.create("info@niklashohenwarter.com", "Tech guy");
        System.out.println("----Listing Record with Email = info@niklashohenwarter.com -----" );
        Search search = searchJDBCTemplate.getPerson("info@niklashohenwarter.com");
        System.out.print("Email : " + search.getEmail() );
        System.out.print(", Bio : " + search.getBio() );
    }
}
