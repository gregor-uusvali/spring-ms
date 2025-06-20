package com.example.sprintMSinventory.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

 import com.example.sprintMSinventory.Entity.Event;
import com.example.sprintMSinventory.Entity.Venue;
import com.example.sprintMSinventory.Repository.EventRepository;
import com.example.sprintMSinventory.Repository.VenueRepository;
import com.example.sprintMSinventory.Response.EventInventoryResponse;
import com.example.sprintMSinventory.Response.VenueInventoryResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryService {

  private final EventRepository eventRepository;
  private final VenueRepository venueRepository;

  public InventoryService(final EventRepository eventRepository, final VenueRepository venueRepository) {
    this.eventRepository = eventRepository;
    this.venueRepository = venueRepository;
  }

  public List<EventInventoryResponse> getAllInventoryEvents() {
    final List<Event> events = eventRepository.findAll();
    return events.stream().map(event -> EventInventoryResponse.builder()
        .event(event.getName())
        .capacity (event.getLeftCapacity())
        .venue(event.getVenue())
        .build()).collect(Collectors.toList());
  }

  public VenueInventoryResponse getVenueById(final Long venueId) {
    final Venue venue = venueRepository.findById(venueId).orElseThrow(() -> new RuntimeException("Venue not found"));
    return VenueInventoryResponse.builder()
        .venueId(venue.getId())
        .venueName(venue.getName())
        .totalCapacity(venue.getTotalCapacity())
        .build();
  }

  public EventInventoryResponse getEventById(final Long eventId) {
    final Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
    return EventInventoryResponse.builder()
        .event(event.getName())
        .capacity(event.getLeftCapacity())
        .venue(event.getVenue())
        .ticketPrice(event.getTicketPrice())
        .eventId(event.getId())
        .build();
  }

  public void updateEventCapacity(final Long eventId, final Long capacity) {
    final Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
    event.setLeftCapacity(event.getLeftCapacity() - capacity);
    eventRepository.saveAndFlush(event);
    log.info("Event capacity updated successfully");
  }
}
