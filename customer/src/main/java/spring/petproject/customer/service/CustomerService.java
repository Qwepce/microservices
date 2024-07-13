package spring.petproject.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spring.petproject.customer.controller.response.FraudCheckResponse;
import spring.petproject.customer.repository.CustomerRepository;
import spring.petproject.customer.controller.request.CustomerRegistrationRequest;
import spring.petproject.customer.entity.Customer;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        this.customerRepository.saveAndFlush(customer);

        String baseURI = "http://localhost:8081/api/v1/fraud-check/{customerId}";
        FraudCheckResponse fraudCheckResponse = this.restTemplate.getForObject(
                baseURI,
                FraudCheckResponse.class,
                customer.getId()
        );

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster!");
        }
        //todo: send notification
    }
}
