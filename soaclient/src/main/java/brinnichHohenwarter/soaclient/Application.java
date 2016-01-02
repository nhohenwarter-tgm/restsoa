package brinnichHohenwarter.soaclient;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import search.wsdl.GetPersonResponse;

/**
 * Starter fuer den SOA Client
 *
 * @author Niklas Hohenwarter
 * @version 2016-01-02
 * @see "http://spring.io/guides/gs/consuming-web-service/"
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner lookup(SearchClient searchClient) {
        return args -> {
            String email = "";

            if (args.length > 0) {
                email = args[0];
            }
            GetPersonResponse response = searchClient.getPersonByEmail(email);
            searchClient.printResponse(response);
        };
    }

}