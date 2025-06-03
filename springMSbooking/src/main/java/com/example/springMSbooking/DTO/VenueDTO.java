package com.example.springMSbooking.DTO;
 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VenueDTO {
  private Long id;
  private String name;
  private String address;
  private long totalCapacity;
}
