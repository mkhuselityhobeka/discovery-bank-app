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
@Table(name = "DENOMINATION_TYPE")
@Entity
@Component
public class DenominationTypeCode {


    @Id
    @Column(name = "DENOMINATION_TYPE_CODE")
    private String denominationTypeCode;
    @Column(name = "DESCRIPTION")
    private String description;
}
