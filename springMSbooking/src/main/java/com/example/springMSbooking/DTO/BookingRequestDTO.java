package com.example.springMSbooking.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {
  private Long userId;
  private Long eventId;
  private Long ticketCount; 
}
