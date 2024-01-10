package com.Wanderlust.Repository;

import com.Wanderlust.Model.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {
}
