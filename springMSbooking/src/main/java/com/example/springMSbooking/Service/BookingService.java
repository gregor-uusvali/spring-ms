package com.example.springMSbooking.Service;

import org.springframework.stereotype.Service;

import com.example.springMSbooking.DTO.BookingRequestDTO;
import com.example.springMSbooking.DTO.BookingResponseDTO;
import com.example.springMSbooking.DTO.InventoryResponseDTO;
import com.example.springMSbooking.Entity.Customer;
import com.example.springMSbooking.Repository.CustomerRepository;
import com.example.springMSbooking.Client.InventoryServiceClient;

@Service
public class BookingService {

  private CustomerRepository customerRepository;
  private InventoryServiceClient inventoryServiceClient;

  public BookingService(final CustomerRepository customerRepository, final InventoryServiceClient inventoryServiceClient) {
    this.customerRepository = customerRepository;
    this.inventoryServiceClient = inventoryServiceClient;
  }

  public BookingResponseDTO createBooking(final BookingRequestDTO bookingRequest) {
    // check if user exists
    Customer customer = customerRepository.findById(bookingRequest.getUserId()).orElse(null);
    if (customer == null) {
      throw new RuntimeException("User not found");
    }
    // check if there is enough inventory
    final InventoryResponseDTO inventory = inventoryServiceClient.getInventory(bookingRequest.getEventId());
    System.out.println("Inventory: " + inventory);
    if (inventory.getCapacity() < bookingRequest.getTicketCount()) {
      throw new RuntimeException("Not enough inventory");
    }
    // get event and venue info
    // create booking
    // send booking to kafka queue
    return BookingResponseDTO.builder().build();
  }
}
