package com.discovery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ATM_ALLOCATION")
@Entity
@Component
public class AtmAllocation {

    @Id
    @Column(name = "ATM_ALLOCATION_ID")
    private Integer atmAllocationID;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATM_ID",
                 referencedColumnName = "ATM_ID",
                 foreignKey = @javax.persistence
                .ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Atm atm;
    @OneToOne
    @JoinColumn(name = "DENOMINATION_ID",
                referencedColumnName = "DENOMINATION_ID",
                foreignKey = @javax.persistence
                        .ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Denomination denominationId;
    @Column(name = "COUNT")
    private Integer count ;
}
