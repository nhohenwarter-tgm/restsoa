package brinnichHohenwarter.soaclient;

import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import search.wsdl.GetPersonResponse;
import search.wsdl.GetPersonsRequest;
import search.wsdl.Search;

/**
 * Greift auf den SOA Webservice zu und sucht nach Personen
 *
 * @author Niklas Hohenwarter
 * @version 2015-12-30
 * @see "http://spring.io/guides/gs/consuming-web-service/"
 */
public class SearchClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(SearchClient.class);

    /**
     * Sucht nach einer Person anhand einer E-Mail Adresse
     * @param email E-Mail Adresse
     * @return gefundene Person/Personen
     */
    public GetPersonResponse getPersonByEmail(String email) {

        GetPersonsRequest request = new GetPersonsRequest();
        request.setEmail(email);

        log.info("Requesting bio for " + email);

        GetPersonResponse response = (GetPersonResponse) getWebServiceTemplate()
                .marshalSendAndReceive(
                        "http://localhost:8080/soa",
                        request,
                        new SoapActionCallback("http://localhost:8080/soa"));

        return response;
    }

    /**
     * Gibt die gefundenen Personen auf der Konsole aus
     * @param response gefundene Personen
     */
    public void printResponse(GetPersonResponse response) {

        List<Search> personReturn = response.getSearch();

        if (personReturn != null) {
            for(int i = 0; i < personReturn.size(); i++){
                Search res = personReturn.get(i);
                log.info("Email: " + res.getEmail() + "\nBio: " + res.getBio());
            }
        } else {
            log.info("Nothing found");
        }
    }

}