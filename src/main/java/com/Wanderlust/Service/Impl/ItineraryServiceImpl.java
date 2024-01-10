package com.Wanderlust.Service.Impl;
import com.Wanderlust.Exception.ItineraryException;
import com.Wanderlust.Model.Itinerary;
import com.Wanderlust.Model.User;
import com.Wanderlust.Repository.ItineraryRepository;
import com.Wanderlust.Repository.UserRepository;
import com.Wanderlust.Service.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItineraryServiceImpl implements ItineraryService {

    private final ItineraryRepository itineraryRepository;

    private final UserRepository userRepository;

    @Autowired
    public ItineraryServiceImpl(ItineraryRepository itineraryRepository, UserRepository userRepository) {
        this.itineraryRepository = itineraryRepository;
        this.userRepository = userRepository;
    }


    /**
     * Creates a new itinerary.
     *
     * @param itinerary The itinerary object to be created.
     * @return The created itinerary.
     * @throws ItineraryException If the itinerary already exists.
     */
    @Override
    @Transactional
    public Itinerary createItinerary(Itinerary itinerary, String email) {
        if(itinerary==null){
            throw new ItineraryException("Itinerary cannot be null");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            User user = userRepository.findByEmail(email).get();
            itinerary.setUser(user);

        } else {
            throw new ItineraryException("User not found");
        }
        return itineraryRepository.save(itinerary);
    }

    @Override
    public List<Itinerary> getAllItineraries() {
        if (itineraryRepository.findAll().isEmpty()) {
            throw new ItineraryException("No itineraries found");
        }
        return itineraryRepository.findAll();
    }

    @Override
    public List<Itinerary> getAllItinerariesByUser(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            User user = userRepository.findByEmail(email).get();
            List<Itinerary> itineraries = user.getItineraries();
        } else {
            throw new ItineraryException("User not found");
        }
        return itineraryRepository.findAll();
    }

    @Override
    public Itinerary getItineraryById(Integer itineraryId) {
        return itineraryRepository.findById(itineraryId)
                .orElseThrow(() -> new ItineraryException("Itinerary not found"));

    }

    @Override
    public Itinerary updateItinerary(Integer itineraryId, Itinerary itinerary) {
        if (itineraryRepository.existsById(itineraryId)) {
            itinerary.setItineraryID(itineraryId);
            return itineraryRepository.save(itinerary);
        } else {
            throw new ItineraryException("Itinerary not found");
        }
    }

    @Override
    public String deleteItinerary(Integer itineraryId) {
        if (itineraryRepository.existsById(itineraryId)) {
            itineraryRepository.deleteById(itineraryId);
            return "Itinerary deleted successfully";
        } else {
            throw new ItineraryException("Itinerary not found");
        }
    }
}
