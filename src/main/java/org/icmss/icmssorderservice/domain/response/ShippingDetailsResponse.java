package org.icmss.icmssorderservice.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShippingDetailsResponse {
    private Long id;
    private String shippingMethod;
    private BigDecimal shippingCost;
    private LocalDateTime estimatedDeliveryDate;
}
