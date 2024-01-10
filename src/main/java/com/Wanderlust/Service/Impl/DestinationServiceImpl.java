package com.Wanderlust.Service.Impl;

import com.Wanderlust.Exception.DestinationException;
import com.Wanderlust.Exception.ItineraryException;
import com.Wanderlust.Exception.UserException;
import com.Wanderlust.Model.*;
import com.Wanderlust.Repository.DestinationRepository;
import com.Wanderlust.Repository.ItineraryRepository;
import com.Wanderlust.Repository.WalletRepository;
import com.Wanderlust.Service.DestinationService;
import com.Wanderlust.Service.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;
    private final ItineraryRepository itineraryRepository;

    private final WalletRepository walletRepository;

    @Autowired
    public DestinationServiceImpl(DestinationRepository destinationRepository, ItineraryRepository itineraryRepository, WalletRepository walletRepository) {
        this.destinationRepository = destinationRepository;
        this.itineraryRepository = itineraryRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    @Transactional
    public Destination createDestination(Destination destination, Integer itineraryId) {
        Itinerary itinerary = itineraryRepository.findById(itineraryId)
                .orElseThrow(() -> new ItineraryException("Itinerary not found"));


        if (itinerary == null) {
            throw new ItineraryException("Itinerary does not exist");
        }
        if (destination == null) {
            throw new DestinationException("Destination cannot be null");
        }


        Destination newDestination = new Destination();
        newDestination.setLocation(destination.getLocation());
        newDestination.setName(destination.getName());
        newDestination.setBudget(destination.getBudget());
        newDestination.setDescription(destination.getDescription());
        newDestination.setCompletionStatus(CompletionStatus.INCOMPLETE);
        itinerary.setDestination(newDestination);





        itineraryRepository.save(itinerary);

        return destinationRepository.save(newDestination);
    }

    @Override
    public List<Destination> getAllDestinations() {
        List<Destination> destinations = destinationRepository.findAll();
        if (destinations.isEmpty()) {
            throw new DestinationException("No destinations found");
        }
        return destinations;
    }

    @Override
    public Destination getDestinationById(Integer destinationId) {
        return destinationRepository.findById(destinationId)
                .orElseThrow(() -> new DestinationException("Destination not found"));
    }

    @Override
    @Transactional
    public Destination updateDestination(Integer destinationId, Destination destination) {
        Destination existingDestination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new DestinationException("Destination not found"));


        existingDestination.setName(destination.getName());
        existingDestination.setDescription(destination.getDescription());
        existingDestination.setLocation(destination.getLocation());


        return destinationRepository.save(existingDestination);
    }

    @Override
    public String deleteDestination(Integer destinationId) {
        Destination existingDestination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new DestinationException("Destination not found"));

        destinationRepository.delete(existingDestination);
        return "Destination deleted successfully";
    }

    @Override
    @Transactional
    public Destination payAmountForDestination(Integer destinationId) {
        Destination existingDestination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new DestinationException("Destination not found"));

        User user = existingDestination.getItineraries().get(0).getUser();
        Wallet wallet = user.getWallet();
        if (wallet == null) {
            throw new UserException("Wallet not found");
        }
        if (wallet.getBalance() < existingDestination.getBudget()) {
            throw new DestinationException("Insufficient funds");
        }

        Expense expense = new Expense();
        expense.setAmount(existingDestination.getBudget());
        expense.setCategory("Destination");
        expense.setExpenseType(ExpenseType.Debit);
        expense.setCurrentBalance(wallet.getBalance() - existingDestination.getBudget());
        expense.setExpenseDate(LocalDateTime.now());

        wallet.getExpenses().add(expense);
        wallet.setBalance(wallet.getBalance() - existingDestination.getBudget());

        existingDestination.setCompletionStatus(CompletionStatus.COMPLETED);
        walletRepository.save(wallet);
        return existingDestination;
    }

    @Override
    public List<Destination> completeDestination() {
        List<Destination> destinations = destinationRepository.findAll();
        List<Destination> completedDestinations = new ArrayList<>();
        for (Destination destination : destinations) {
            if (destination.getCompletionStatus() == CompletionStatus.COMPLETED) {
                completedDestinations.add(destination);
            }
        }
        return completedDestinations;
    }

    @Override
    public List<Destination> incompleteDestination() {
        List<Destination> destinations = destinationRepository.findAll();
        List<Destination> incompleteDestinations = new ArrayList<>();
        for (Destination destination : destinations) {
            if (destination.getCompletionStatus() == CompletionStatus.INCOMPLETE) {
                incompleteDestinations.add(destination);
            }
        }
        return incompleteDestinations;
    }
}
