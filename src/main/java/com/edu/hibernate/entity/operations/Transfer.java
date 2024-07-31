package com.edu.hibernate.entity.operations;

import com.edu.hibernate.entity.Transaction;
import com.edu.hibernate.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "Transfer")
@Entity
public class Transfer extends Transaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_transaction")
    @SequenceGenerator(name = "sequence_transaction", sequenceName = "transaction_main_sequence", allocationSize = 1)
    private long id;
    @Column(name = "user_account_from")
    private String from;
    @Column(name = "target")
    private String target;
    @Column(name = "amount")
    private BigDecimal amount;

}
