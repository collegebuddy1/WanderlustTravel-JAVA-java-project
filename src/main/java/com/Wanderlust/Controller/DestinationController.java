package com.Wanderlust.Controller;

import com.Wanderlust.Exception.DestinationException;
import com.Wanderlust.Model.Destination;
import com.Wanderlust.Service.DestinationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinations")
public class DestinationController {

    private final DestinationService destinationService;

    @Autowired
    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @PostMapping("/create/{itineraryId}")
    public ResponseEntity<Destination> createDestination( @RequestBody Destination destination, @PathVariable Integer itineraryId) {
        try {
            Destination createdDestination = destinationService.createDestination(destination, itineraryId);
            return new ResponseEntity<>(createdDestination, HttpStatus.CREATED);
        } catch (DestinationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Destination>> getAllDestinations() {
        try {
            List<Destination> destinations = destinationService.getAllDestinations();
            return new ResponseEntity<>(destinations, HttpStatus.OK);
        } catch (DestinationException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{destinationId}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable Integer destinationId) {
        try {
            Destination destination = destinationService.getDestinationById(destinationId);
            return new ResponseEntity<>(destination, HttpStatus.OK);
        } catch (DestinationException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/complete")
    public ResponseEntity<List<Destination>> completeDestination() {
        try {
            List<Destination> destinations = destinationService.completeDestination();
            return new ResponseEntity<>(destinations, HttpStatus.OK);
        } catch (DestinationException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/incomplete")
    public ResponseEntity<List<Destination>> incompleteDestination() {
        try {
            List<Destination> destinations = destinationService.incompleteDestination();
            return new ResponseEntity<>(destinations, HttpStatus.OK);
        } catch (DestinationException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{destinationId}")
    public ResponseEntity<Destination> updateDestination(@PathVariable Integer destinationId, @RequestBody Destination destination) {
        try {
            Destination updatedDestination = destinationService.updateDestination(destinationId, destination);
            return new ResponseEntity<>(updatedDestination, HttpStatus.OK);
        } catch (DestinationException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{destinationId}")
    public ResponseEntity<String> deleteDestination(@PathVariable Integer destinationId) {
        try {
            String response = destinationService.deleteDestination(destinationId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DestinationException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{destinationId}/pay")
    public ResponseEntity<Destination> payAmountForDestination(@PathVariable Integer destinationId) {
        try {
            Destination paidDestination = destinationService.payAmountForDestination(destinationId);
            return new ResponseEntity<>(paidDestination, HttpStatus.OK);
        } catch (DestinationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
