package com.example.sprintMSinventory.Response;

import java.math.BigDecimal;

import com.example.sprintMSinventory.Entity.Venue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventInventoryResponse {
  private Long eventId;
  private String event;
  private Long capacity;
  private Venue venue;	
  private BigDecimal ticketPrice;
}
