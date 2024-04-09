package org.icmss.icmssorderservice.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.icmss.icmssorderservice.domain.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse {
    private Long id;
    private String orderNumber;
    private String buyer;
    private String seller;
    private Long car;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private LocalDateTime placedAt;
    private LocalDateTime deliveredAt;
    private ShippingDetailsResponse shippingDetails;
    private TransactionResponse transaction;
    private EscrowAccountResponse escrowAccount;
    private List<TaxResponse> taxes;

}
