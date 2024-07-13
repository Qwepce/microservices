package spring.petproject.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spring.petproject.clients.fraud.FraudCheckResponse;
import spring.petproject.clients.fraud.FraudClient;
import spring.petproject.customer.repository.CustomerRepository;
import spring.petproject.customer.controller.request.CustomerRegistrationRequest;
import spring.petproject.customer.entity.Customer;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        this.customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = this.fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster!");
        }
        //todo: send notification
    }
}
