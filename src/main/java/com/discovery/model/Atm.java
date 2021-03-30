package com.discovery.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ATM")
@Entity
@Component
public class Atm {

    @Id
    @Column(name = "ATM_ID")
    private Integer atmId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "LOCATION")
    private String location;


    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "atm")
    @ToString.Exclude
    private List<AtmAllocation> atmAllocationId;
}
