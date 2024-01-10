package com.Wanderlust.Service;

import com.Wanderlust.Model.Itinerary;

import java.util.List;

public interface ItineraryService {
    Itinerary createItinerary(Itinerary itinerary,String email);

    List<Itinerary> getAllItineraries();

    List<Itinerary> getAllItinerariesByUser(String email);

    Itinerary getItineraryById(Integer itineraryId);

    Itinerary updateItinerary(Integer itineraryId, Itinerary itinerary);

    String deleteItinerary(Integer itineraryId);
}
