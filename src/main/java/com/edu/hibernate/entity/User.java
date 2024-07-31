package com.edu.hibernate.entity;

import com.edu.hibernate.entity.operations.Debit;
import com.edu.hibernate.entity.operations.Topup;
import com.edu.hibernate.entity.operations.Transfer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Person")
@Entity
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_user")
    @SequenceGenerator(name = "sequence_user", sequenceName = "user_main_sequence", allocationSize = 1)
    private long id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "account_number")
    private String accountNumber;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Topup> topupList;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Debit> debitList;

}
