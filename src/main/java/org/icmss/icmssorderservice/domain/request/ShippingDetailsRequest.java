package org.icmss.icmssorderservice.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingDetailsRequest {
    private String shippingMethod;
    private LocalDateTime estimatedDeliveryDate;
}
