package achraf.accountdataservice.feign;

import achraf.accountdataservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "http://localhost:8082", name = "customer-rest-client")
public interface CustomerRestClient {
    @GetMapping("/customers")
    public List<Customer> getCustomers();
}
