package com.example.springMSbooking.Event;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingEvent {
  private Long userId;
  private Long eventId;
  private Long ticketCount;
  private BigDecimal totalPrice;

}
