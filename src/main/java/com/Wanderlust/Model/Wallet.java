package com.Wanderlust.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
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
public class Wallet {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer walletId;
    @DecimalMin("0.0")
    private Double balance;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="walletId")
    private List<Expense> expenses;

    @OneToOne
    @JsonIgnore
    private User user;

}
