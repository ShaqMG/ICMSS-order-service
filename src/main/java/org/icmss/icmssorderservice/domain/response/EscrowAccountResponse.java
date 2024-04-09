package org.icmss.icmssorderservice.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EscrowAccountResponse {
    private Long id;
    private BigDecimal amount;
    private LocalDateTime releasedAt;
}
