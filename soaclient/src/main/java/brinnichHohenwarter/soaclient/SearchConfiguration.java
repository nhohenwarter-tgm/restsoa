package brinnichHohenwarter.soaclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
/**
 * Konfiguration fuer den SOA Client
 *
 * @author Niklas Hohenwarter
 * @version 2016-01-02
 * @see "http://spring.io/guides/gs/consuming-web-service/"
 */
public class SearchConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("search.wsdl");
        return marshaller;
    }

    @Bean
    public SearchClient searchClient(Jaxb2Marshaller marshaller) {
        SearchClient client = new SearchClient();
        client.setDefaultUri("http://localhost/soa");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}