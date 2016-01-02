package brinnichHohenwarter.soa.localhost.soa.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
@Endpoint
public class SearchEndpoint {
    private static final String NAMESPACE_URI = "http://localhost/soa/search";

    private SearchRepository searchRepository;

    @Autowired
    public SearchEndpoint(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonsRequest")
    @ResponsePayload
    public GetPersonResponse getCountry(@RequestPayload GetPersonsRequest request) {
        GetPersonResponse response = new GetPersonResponse();
        response.setSearch(searchRepository.findPerson(request.getEmail()));
        return response;
    }
}