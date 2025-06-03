package com.example.springMSbooking.Client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.springMSbooking.DTO.InventoryResponseDTO;

@Service
public class InventoryServiceClient {
  @org.springframework.beans.factory.annotation.Value("${inventory.service.url}")
  private String inventoryServiceUrl;


  public InventoryResponseDTO getInventory(final Long eventId) {
    final RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject(inventoryServiceUrl + eventId, InventoryResponseDTO.class);
  }
}
 