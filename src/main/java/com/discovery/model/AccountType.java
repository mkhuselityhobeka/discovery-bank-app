package com.discovery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor@AllArgsConstructor
@Table(name = "ACCOUNT_TYPE")
@Component
@Entity
public class AccountType {

    @Id
    @Column(name = "ACCOUNT_TYPE_CODE")
    private String accountTypeCode;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "TRANSACTIONAL")
    private byte transactional;

//    @JsonBackReference
//    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn(name = "clientAccountNumber")
//    private ClientAccount account;
}
