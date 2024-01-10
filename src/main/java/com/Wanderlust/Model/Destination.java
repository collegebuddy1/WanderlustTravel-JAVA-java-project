package com.Wanderlust.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Destination {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer disID;

    @Column(unique = true)
    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Location cannot be null")
    private String location;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Budget cannot be null")
    private Double budget;
    @Enumerated(EnumType.STRING)
    private CompletionStatus completionStatus;
    @OneToMany(mappedBy = "destination")
    @JsonIgnore
    private List<Itinerary> itineraries;

}
