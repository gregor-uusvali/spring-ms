package com.example.sprintMSinventory.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VenueInventoryResponse {
  private Long venueId;
  private String venueName;
  private Long totalCapacity;
}

