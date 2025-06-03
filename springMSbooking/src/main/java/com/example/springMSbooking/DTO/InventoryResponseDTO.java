package com.example.springMSbooking.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponseDTO {
  private Long eventId;
  private String event;
  private Long capacity;
  private VenueDTO venue;	
  private BigDecimal ticketPrice;
}
