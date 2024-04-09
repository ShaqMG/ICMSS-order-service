package org.icmss.icmssorderservice.mapper;

import org.icmss.icmssorderservice.domain.entity.Order;
import org.icmss.icmssorderservice.domain.entity.ShippingDetails;
import org.icmss.icmssorderservice.domain.request.ShippingDetailsRequest;
import org.icmss.icmssorderservice.domain.response.ShippingDetailsResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ShippingDetailsMapper {
    public static ShippingDetails toShippingDetails(Order order, String shippingMethod, BigDecimal shippingCost, LocalDateTime estimatedDeliveryDate) {
        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setOrder(order);
        shippingDetails.setShippingMethod(shippingMethod);
        shippingDetails.setShippingCost(shippingCost);
        shippingDetails.setEstimatedDeliveryDate(estimatedDeliveryDate);
        return shippingDetails;
    }

    public static ShippingDetails toShippingDetails(ShippingDetails existingShippingDetails, ShippingDetailsRequest shippingDetailsRequest) {
        if (shippingDetailsRequest.getShippingMethod() != null) {
            existingShippingDetails.setShippingMethod(shippingDetailsRequest.getShippingMethod());
        }
        if (shippingDetailsRequest.getEstimatedDeliveryDate() != null) {
            existingShippingDetails.setEstimatedDeliveryDate(shippingDetailsRequest.getEstimatedDeliveryDate());
        }
        return existingShippingDetails;
    }

    public static ShippingDetailsResponse toShippingDetailsResponse(ShippingDetails shippingDetails) {
        ShippingDetailsResponse shippingDetailsResponse = new ShippingDetailsResponse();
        shippingDetailsResponse.setId(shippingDetails.getId());
        shippingDetailsResponse.setShippingMethod(shippingDetails.getShippingMethod());
        shippingDetailsResponse.setShippingCost(shippingDetails.getShippingCost());
        shippingDetailsResponse.setEstimatedDeliveryDate(shippingDetails.getEstimatedDeliveryDate());
        return shippingDetailsResponse;
    }
}
