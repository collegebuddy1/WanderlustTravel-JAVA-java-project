package com.Wanderlust.Controller;

import com.Wanderlust.Model.Itinerary;
import com.Wanderlust.Service.ItineraryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itineraries")
public class ItineraryController {

    /*
        {
      "startDate": "2023-12-01",
      "endDate": "2023-12-10",
      "location": "Paris",
      "description": "Exploring the City of Lights",
      "budget": 2500.00
    }


     */

    private final ItineraryService itineraryService;

    @Autowired
    public ItineraryController(ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    @PostMapping("/create/{email}")
    public ResponseEntity<Itinerary> createItinerary( @RequestBody Itinerary itinerary, @PathVariable String email) {
        Itinerary createdItinerary = itineraryService.createItinerary(itinerary, email);
        return new ResponseEntity<>(createdItinerary, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Itinerary> getItineraryById(@PathVariable Integer id) {
        Itinerary itinerary = itineraryService.getItineraryById(id);
        return new ResponseEntity<>(itinerary, HttpStatus.OK);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<List<Itinerary>> getAllItinerariesByUser(@PathVariable String email) {
        List<Itinerary> itineraries = itineraryService.getAllItinerariesByUser(email);
        return new ResponseEntity<>(itineraries, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Itinerary>> getAllItineraries() {
        List<Itinerary> itineraries = itineraryService.getAllItineraries();
        return new ResponseEntity<>(itineraries, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Itinerary> updateItinerary(@PathVariable Integer id, @RequestBody Itinerary itinerary) {
        Itinerary updatedItinerary = itineraryService.updateItinerary(id, itinerary);
        return new ResponseEntity<>(updatedItinerary, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteItinerary(@PathVariable Integer id) {
        String message = itineraryService.deleteItinerary(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
