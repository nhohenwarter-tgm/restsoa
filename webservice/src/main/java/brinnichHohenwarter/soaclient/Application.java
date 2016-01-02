package brinnichHohenwarter.soaclient;

import brinnichHohenwarter.soa.localhost.soa.search.GetPersonResponse;
import hello.wsdl.GetCityForecastByZIPResponse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    CommandLineRunner lookup(SearchClient weatherClient) {
        return args -> {
            String zipCode = "";

            if (args.length > 0) {
                zipCode = args[0];
            }
            GetPersonResponse response = weatherClient.getPersonByEmail(zipCode);
            weatherClient.printResponse(response);
        };
    }

}