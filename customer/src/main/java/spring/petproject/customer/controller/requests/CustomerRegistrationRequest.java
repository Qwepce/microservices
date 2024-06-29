package spring.petproject.customer.controller.requests;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {
}
