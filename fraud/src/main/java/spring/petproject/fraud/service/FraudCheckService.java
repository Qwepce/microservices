package spring.petproject.fraud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.petproject.fraud.entity.FraudCheckHistory;
import spring.petproject.fraud.repository.FraudCheckHistoryRepository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(Integer customerId) {
        this.fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
