package com.example.springMSbooking.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springMSbooking.DTO.BookingRequestDTO;
import com.example.springMSbooking.DTO.BookingResponseDTO;
import com.example.springMSbooking.Service.BookingService;

@RestController
@RequestMapping("api/v1")
public class BookingController {

  private BookingService bookingService;

  public BookingController(final BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @PostMapping(consumes = "application/json", produces = "application/json", path = "/booking")
  private BookingResponseDTO createBooking(@RequestBody final BookingRequestDTO bookingRequest) {
    return bookingService.createBooking(bookingRequest);
  }

}
