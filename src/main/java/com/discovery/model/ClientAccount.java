package com.discovery.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CLIENT_ACCOUNT")
@Entity
@Component
public class ClientAccount {

    @Id
    @Column(name ="CLIENT_ACCOUNT_NUMBER")
    private String clientAccountNumber;
    @Column(name ="CLIENT_ID")
    private Integer clientId;
    @Column(name = "ACCOUNT_TYPE_CODE")
    private String accountTypeCode;
    @OneToOne
    @JoinColumn(name = "CURRENCY_CODE",
            referencedColumnName = "CURRENCY_CODE",
            foreignKey = @javax.persistence
            .ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private CurrencyConversionRate currencyCode;
    @Column(name = "DISPLAY_BALANCE")
    private double displayBalance;
    @Transient
    private double saRands;
}
