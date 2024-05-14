package achraf.accountdataservice.web;

import achraf.accountdataservice.feign.CustomerRestClient;
import achraf.accountdataservice.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/accounts-service")
@AllArgsConstructor
public class AccountRestController {
    private RestTemplate restTemplate;
    private CustomerRestClient customerRestClient;
    @GetMapping("/customers")
    public List<Customer> listCustomers() {
        return List.of(Objects.requireNonNull(restTemplate.getForObject("http://localhost:8082/customers", Customer[].class)));
    }

    @GetMapping("/customers/v2")
    public Flux<Customer> listCustomersV2() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8082")
                .build();
        return webClient.get().uri("/customers").retrieve().bodyToFlux(Customer.class);

    }
    @GetMapping("/customers/v3")
    public List<Customer> listCustomersV3() {
        return customerRestClient.getCustomers();
    }

    // GQL Approach

    @GetMapping("/gql/customers")
    public Mono<List<Customer>> listCustomersGQL() {
        HttpGraphQlClient client = HttpGraphQlClient.builder()
                .url("http://localhost:8082/graphql")
                .build();

        var httpRequestQuery = """
            query {
                allCustomers {
                    id
                    name
                    email
                }
            }
        """;
        return client.document(httpRequestQuery)
                .retrieve("allCustomers")
                .toEntityList(Customer.class);
    }

}
