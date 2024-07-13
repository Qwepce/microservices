package spring.petproject.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.petproject.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
