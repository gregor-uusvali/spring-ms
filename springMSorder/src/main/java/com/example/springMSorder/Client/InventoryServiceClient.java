package com.example.springMSorder.Client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.springMSbooking.event.BookingEvent;

@Service
public class InventoryServiceClient {

  @org.springframework.beans.factory.annotation.Value("${inventory.service.url}")
  private String inventoryServiceUrl;

  public ResponseEntity<Void> updateInventory(BookingEvent bookingEvent) {
    final RestTemplate restTemplate = new RestTemplate();
    restTemplate.put(inventoryServiceUrl + bookingEvent.getEventId(), bookingEvent);
    return ResponseEntity.ok().build();
  }

}
