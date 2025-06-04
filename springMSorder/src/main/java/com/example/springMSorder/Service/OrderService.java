package com.example.springMSorder.Service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.springMSbooking.event.BookingEvent;
import com.example.springMSorder.Client.InventoryServiceClient;
import com.example.springMSorder.Entity.Order;
import com.example.springMSorder.Repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryServiceClient inventoryServiceClient;

    public OrderService(OrderRepository orderRepository, InventoryServiceClient inventoryServiceClient) {
        this.orderRepository = orderRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    @KafkaListener(topics = "booking", groupId = "order-service")
    public void orderEvent(BookingEvent bookingEvent) {
        log.info("Received booking event: {}", bookingEvent);
        //Create order object for DB
        Order order = createOrder(bookingEvent);

        orderRepository.saveAndFlush(order);
        // Update inventory
        inventoryServiceClient.updateInventory(bookingEvent);
        log.info("Order created and inventory updated for event: {}", bookingEvent.getEventId());
    }

    private Order createOrder(BookingEvent bookingEvent) {
        return Order.builder()
            .customerId(bookingEvent.getUserId())
            .eventId(bookingEvent.getEventId())
            .quantity(bookingEvent.getTicketCount())
            .total(bookingEvent.getTotalPrice())
            .build();
    }
}
