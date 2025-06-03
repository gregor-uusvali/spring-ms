package com.example.sprintMSinventory.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sprintMSinventory.Entity.Event;


//Every repository is associated with a table in the database
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
