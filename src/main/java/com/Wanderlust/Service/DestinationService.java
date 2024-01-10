package com.Wanderlust.Service;

import com.Wanderlust.Model.Destination;

import java.util.List;

public interface DestinationService {
    Destination createDestination(Destination destination, Integer itineraryId);
    List<Destination> getAllDestinations();
    Destination getDestinationById(Integer destinationId);
    Destination updateDestination(Integer destinationId, Destination destination);
    String deleteDestination(Integer destinationId);
    Destination payAmountForDestination(Integer destinationId);
    List<Destination> completeDestination();
    List<Destination> incompleteDestination();
}
