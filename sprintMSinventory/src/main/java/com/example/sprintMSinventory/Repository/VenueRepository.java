package com.example.sprintMSinventory.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sprintMSinventory.Entity.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {

}
