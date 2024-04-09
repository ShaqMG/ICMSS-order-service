package org.icmss.icmssorderservice.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaxResponse {
    private Long id;
    private BigDecimal amount;
    private String taxType;
}
