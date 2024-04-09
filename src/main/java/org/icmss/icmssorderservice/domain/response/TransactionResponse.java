package org.icmss.icmssorderservice.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.icmss.icmssorderservice.domain.enums.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionResponse {
    private Long id;
    private BigDecimal amount;
    private TransactionStatus status;
    private LocalDateTime createdAt;
}
