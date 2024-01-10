package com.Wanderlust.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer userID;

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;
    @NotNull(message = "Password cannot be null")
    @Size(min = 4, max = 30, message = "Password must be between 8 and 30 characters")
    private String password;

    @NotNull(message = "Email cannot be null")
    @Size(min = 5, max = 30, message = "Email must be between 2 and 30 characters")
    @Column(unique = true)
    private String email;
    @NotNull(message = "address cannot be null")
    @Size(min = 5, max = 50, message = "address must be between 2 and 30 characters")
    private String address;
    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<Itinerary> itineraries;
    @OneToOne(mappedBy="user" ,cascade= CascadeType.ALL)
    private Wallet wallet;


}
