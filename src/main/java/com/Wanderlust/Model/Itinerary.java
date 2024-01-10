package com.Wanderlust.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer itineraryID;
    private Date startDate;
    private Date endDate;
    private String location;
    private String description;
    private Double budget;
    @ManyToOne(cascade=CascadeType.ALL)
    private Destination destination;
    @ManyToOne
    @JsonIgnore
    private User user;
}
