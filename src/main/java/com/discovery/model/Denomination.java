package com.discovery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DENOMINATION")
@Entity
@Component
public class Denomination {

    @Id
    @Column(name = "DENOMINATION_ID")
    private  Integer denominationId;
    @Column(name = "VALUE")
    private double value;
    @Column(name = "DENOMINATION_TYPE_CODE")
    private String DenominationTypeCode;
}
