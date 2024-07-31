package com.edu.hibernate.dao;


import com.edu.hibernate.entity.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Transaction transaction) {
        entityManager.persist(transaction);

    }



}
