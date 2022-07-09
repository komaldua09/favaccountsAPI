package com.learning.bank.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "fav_account")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FavBankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favBankAccountId;

    @Column
    private String iban;

    @NotNull
    private String accountName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="code")
    private Bank bank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;

    @Column
    private boolean deleteInd;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

}