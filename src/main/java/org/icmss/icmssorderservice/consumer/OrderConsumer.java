package org.icmss.icmssorderservice.consumer;

import com.middleproject.common.model.kafka.ItemReserveResponseDTO;
import com.middleproject.common.model.kafka.OrderFailedDTO;
import com.middleproject.common.util.JsonUtil;
import com.middleproject.orderservice.facade.OrderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderFacade orderFacade;

    @KafkaListener(
            topics = "${kafka.topics.order.order-failed}",
            groupId = "${kafka.groupId}"
    )
    public void handleOrderFailedEvent(String message) {
        OrderFailedDTO orderFailedParam = JsonUtil.toObject(message, OrderFailedDTO.class);
        orderFacade.handleOrderFailedEvent(orderFailedParam);
    }

    @KafkaListener(
            topics = "${kafka.topics.catalog.item-reserve-response}",
            groupId = "${kafka.groupId}"
    )
    public void handleItemReserveResponse(String message) {
        ItemReserveResponseDTO itemReserveResponseParam = JsonUtil.toObject(message, ItemReserveResponseDTO.class);
        orderFacade.handleItemReserveResponse(itemReserveResponseParam);
    }

}
