package com.example.springMSbooking.Service;

import java.math.BigDecimal;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.springMSbooking.DTO.BookingRequestDTO;
import com.example.springMSbooking.DTO.BookingResponseDTO;
import com.example.springMSbooking.DTO.InventoryResponseDTO;
import com.example.springMSbooking.Entity.Customer;
import com.example.springMSbooking.Event.BookingEvent;
import com.example.springMSbooking.Repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

import com.example.springMSbooking.Client.InventoryServiceClient;

@Service
@Slf4j
public class BookingService {

  private CustomerRepository customerRepository;
  private InventoryServiceClient inventoryServiceClient;
  private KafkaTemplate<String, BookingEvent> kafkaTemplate;

  public BookingService(final CustomerRepository customerRepository,
      final InventoryServiceClient inventoryServiceClient,
      final KafkaTemplate<String, BookingEvent> kafkaTemplate) {
    this.customerRepository = customerRepository;
    this.inventoryServiceClient = inventoryServiceClient;
    this.kafkaTemplate = kafkaTemplate;
  }

  public BookingResponseDTO createBooking(final BookingRequestDTO bookingRequest) {
    // check if user exists
    Customer customer = customerRepository.findById(bookingRequest.getUserId()).orElse(null);
    if (customer == null) {
      throw new RuntimeException("User not found");
    }
    // check if there is enough inventory and get event and venue info
    final InventoryResponseDTO inventory = inventoryServiceClient.getInventory(bookingRequest.getEventId());
    log.info("Inventory: {}", inventory);
    if (inventory.getCapacity() < bookingRequest.getTicketCount()) {
      throw new RuntimeException("Not enough inventory");
    }
    // create booking
    BookingEvent bookingEvent = createBookingEvent(bookingRequest, customer, inventory);
    // send booking to kafka queue
    log.info("About to send booking to kafka. Event details: {}", bookingEvent);
    kafkaTemplate.send("booking", bookingEvent).whenComplete((result, ex) -> {
      if (ex == null) {
        log.info("Successfully sent message to kafka. Topic: {}, Partition: {}, Offset: {}",
            result.getRecordMetadata().topic(),
            result.getRecordMetadata().partition(),
            result.getRecordMetadata().offset());
      } else {
        log.error("Failed to send message to kafka", ex);
      }
    });
    log.info("Booking sent to kafka: {}", bookingEvent);

    return BookingResponseDTO.builder()
        .userId(bookingEvent.getUserId())
        .eventId(bookingEvent.getEventId())
        .ticketCount(bookingEvent.getTicketCount())
        .totalPrice(bookingEvent.getTotalPrice())
        .build();
  }

  private BookingEvent createBookingEvent(final BookingRequestDTO bookingRequest, final Customer customer,
      final InventoryResponseDTO inventory) {
    return BookingEvent.builder()
        .userId(customer.getId())
        .eventId(inventory.getEventId())
        .ticketCount(bookingRequest.getTicketCount())
        .totalPrice(inventory.getTicketPrice().multiply(BigDecimal.valueOf(bookingRequest.getTicketCount())))
        .build();
  }
}
