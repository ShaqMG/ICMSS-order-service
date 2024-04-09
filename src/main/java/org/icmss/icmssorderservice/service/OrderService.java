package org.icmss.icmssorderservice.service;

import lombok.RequiredArgsConstructor;
import org.icmss.icmssorderservice.domain.entity.Order;
import org.icmss.icmssorderservice.domain.request.OrderRequest;
import org.icmss.icmssorderservice.domain.response.OrderResponse;
import org.icmss.icmssorderservice.mapper.OrderMapper;
import org.icmss.icmssorderservice.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final DealerService dealerService;
    private final CarService carService;
    private final ShippingService shippingService;
    private final TransactionService transactionService;
    private final EscrowService escrowService;
    private final TaxService taxService;

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = OrderMapper.toOrder(orderRequest);

        order = orderRepository.save(order);

        ShippingDetails shippingDetails = shippingService.createShippingDetails(order);
        Transaction transaction = transactionService.initiateTransaction(order);
        EscrowAccount escrowAccount = escrowService.createEscrowAccount(order, transaction.getAmount());
        List<Tax> taxes = taxService.calculateAndApplyTaxes(order);

        order.setTotalPrice(car.getPrice().add(shippingDetails.getShippingCost()).add(taxes.stream().map(Tax::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add)));
        order.setShippingDetails(shippingDetails);
        order.setTransaction(transaction);
        order.setEscrowAccount(escrowAccount);
        order.setTaxes(taxes);

        return orderRepository.save(order);
    }

    public Order getOrder(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        return orderOptional.orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
    }

    @Transactional
    public Order updateOrder(Long orderId, OrderRequest orderRequest) {
        Order existingOrder = getOrder(orderId);

        User buyer = userService.getUserById(orderRequest.getBuyerId());
        Dealer seller = dealerService.getDealerById(orderRequest.getSellerId());
        Car car = carService.getCarById(orderRequest.getCarId());

        existingOrder.setBuyer(buyer);
        existingOrder.setSeller(seller);
        existingOrder.setCar(car);

        ShippingDetails updatedShippingDetails = shippingService.updateShippingDetails(existingOrder, orderRequest.getShippingDetailsRequest());
        existingOrder.setShippingDetails(updatedShippingDetails);

        List<Tax> updatedTaxes = taxService.calculateAndApplyTaxes(existingOrder);
        existingOrder.setTaxes(updatedTaxes);

        existingOrder.setTotalPrice(car.getPrice().add(updatedShippingDetails.getShippingCost()).add(updatedTaxes.stream().map(Tax::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add)));

        return orderRepository.save(existingOrder);
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = getOrder(orderId);
        order.setStatus(OrderStatus.CANCELLED);
        transactionService.cancelTransaction(order.getTransaction().getId());
        escrowService.releaseEscrowFunds(order.getEscrowAccount().getId());
        orderRepository.save(order);
    }

    public Page<Order> getOrders(String filterBy, String sortBy, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        if (filterBy == null || filterBy.isEmpty()) {
            return orderRepository.findAll(pageable);
        } else if (filterBy.equalsIgnoreCase("status")) {
            return orderRepository.findByStatus(OrderStatus.valueOf(filterBy.toUpperCase()), pageable);
        } else {
            throw new IllegalArgumentException("Invalid filter parameter: " + filterBy);
        }
    }
}
