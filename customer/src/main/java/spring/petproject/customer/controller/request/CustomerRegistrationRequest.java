package spring.petproject.customer.controller.request;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {
}
