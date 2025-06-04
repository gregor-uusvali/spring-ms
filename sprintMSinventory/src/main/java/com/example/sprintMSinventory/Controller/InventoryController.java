package com.example.sprintMSinventory.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sprintMSinventory.Response.EventInventoryResponse;
import com.example.sprintMSinventory.Response.VenueInventoryResponse;
import com.example.sprintMSinventory.Service.InventoryService;

@RestController
@RequestMapping("api/v1")
public class InventoryController {

  private InventoryService inventoryService;

  public InventoryController(final InventoryService inventoryService) {
    this.inventoryService = inventoryService;
  }
  
  @GetMapping("inventory/events")
  private @ResponseBody List<EventInventoryResponse> getAllInventoryEvents() {
    return inventoryService.getAllInventoryEvents();
  }

  @GetMapping("inventory/venue/{venueId}")
  private @ResponseBody VenueInventoryResponse getVenueById(@PathVariable("venueId") final Long venueId) {
    return inventoryService.getVenueById(venueId);
  }

  @GetMapping("inventory/events/{eventId}")
  private @ResponseBody EventInventoryResponse getEventById(@PathVariable("eventId") final Long eventId) {
    return inventoryService.getEventById(eventId);
  }

  @PutMapping("inventory/events/{eventId}")
  private ResponseEntity<Void> updateEventCapacity(@PathVariable("eventId") final Long eventId, @PathVariable("capacity") final Long capacity) {
    inventoryService.updateEventCapacity(eventId, capacity);
    return ResponseEntity.ok().build();
  }

}
