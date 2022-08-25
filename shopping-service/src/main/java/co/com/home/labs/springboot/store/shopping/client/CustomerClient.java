package co.com.home.labs.springboot.store.shopping.client;

import co.com.home.labs.springboot.store.shopping.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "${feign.client.url.customer}")
public interface CustomerClient {
    static final Logger logger = LoggerFactory.getLogger(CustomerClient.class);

    @GetMapping(value = "/customers/{id}")

    // This is the name to the breaker configuration
    @CircuitBreaker(name = "CustomerClientCircuitBreaker", fallbackMethod = "getCustomerFallbackMethod")
    //@RequestMapping(method= RequestMethod.GET, value="/customers/{id}", produces = "application/json")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id);

    default ResponseEntity<Customer> getCustomerFallbackMethod(long id, Exception exc) {
        logger.error("Got an error, executing fallback method and returning default from application!");
        Customer customer = Customer.builder()
                .firstName("none")
                .lastName("none")
                .email("none")
                .photoUrl("none").build();
        return ResponseEntity.ok(customer);
    }
}