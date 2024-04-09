package org.icmss.icmssorderservice.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String buyerId;
    private String sellerId;
    private Long carId;
    private ShippingDetailsRequest shippingDetailsRequest;
}
