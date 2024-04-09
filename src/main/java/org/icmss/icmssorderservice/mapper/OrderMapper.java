package org.icmss.icmssorderservice.mapper;

import org.icmss.icmssorderservice.domain.entity.Order;
import org.icmss.icmssorderservice.domain.enums.OrderStatus;
import org.icmss.icmssorderservice.domain.request.OrderRequest;
import org.icmss.icmssorderservice.domain.response.OrderResponse;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class OrderMapper {

    public static Order toOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setBuyerId(orderRequest.getBuyerId());
        order.setSellerId(orderRequest.getSellerId());
        order.setCarId(orderRequest.getCarId());
        order.setStatus(OrderStatus.PLACED);
        order.setPlacedAt(LocalDateTime.now());
        return order;
    }

    public static OrderResponse toOrderResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setOrderNumber(order.getOrderNumber());
        orderResponse.setBuyer(order.getBuyerId());
        orderResponse.setSeller(order.getSellerId());
        orderResponse.setCar(order.getCarId());
        orderResponse.setTotalPrice(order.getTotalPrice());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setPlacedAt(order.getPlacedAt());
        orderResponse.setDeliveredAt(order.getDeliveredAt());
        orderResponse.setShippingDetails(ShippingDetailsMapper.toShippingDetailsResponse(order.getShippingDetails()));
        orderResponse.setTransaction(transactionMapper.toTransactionResponse(order.getTransaction()));
        orderResponse.setEscrowAccount(escrowAccountMapper.toEscrowAccountResponse(order.getEscrowAccount()));
        orderResponse.setTaxes(order.getTaxes().stream().map(taxMapper::toTaxResponse).collect(Collectors.toList()));
        return orderResponse;
    }
}
