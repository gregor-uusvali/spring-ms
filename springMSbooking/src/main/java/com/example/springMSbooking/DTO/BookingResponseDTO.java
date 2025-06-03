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
public class BookingResponseDTO {
  private Long bookingId;
  private Long userId;
  private Long eventId;
  private Long ticketCount;
  private BigDecimal totalPrice;
}
