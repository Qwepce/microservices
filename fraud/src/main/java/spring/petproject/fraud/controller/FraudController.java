package spring.petproject.fraud.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spring.petproject.clients.fraud.FraudCheckResponse;
import spring.petproject.fraud.service.FraudCheckService;

@RestController
@RequestMapping("api/v1/fraud-check")
@RequiredArgsConstructor
@Slf4j
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping("{customerId:\\d+}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {

        boolean isFraudulentCustomer = this.fraudCheckService
                .isFraudulentCustomer(customerId);

        log.info("Fraud check request for customer : {}", customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
