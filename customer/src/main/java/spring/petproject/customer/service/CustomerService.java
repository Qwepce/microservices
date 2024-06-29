package spring.petproject.customer.service;

import org.springframework.stereotype.Service;
import spring.petproject.customer.CustomerRepository;
import spring.petproject.customer.controller.requests.CustomerRegistrationRequest;
import spring.petproject.customer.entity.Customer;

@Service
public record CustomerService(CustomerRepository customerRepository) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        //todo: check if email valid
        //todo: check if email not taken
        //todo: store customer in db
        this.customerRepository.save(customer);
    }
}
