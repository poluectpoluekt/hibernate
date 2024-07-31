package com.edu.hibernate.dao;

import com.edu.hibernate.entity.User;
import com.edu.hibernate.exception.user.UserNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class UserDao {

    private final EntityManager entityManager;


    @Transactional
    public void createUser(User user){

        entityManager.persist(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> find(String username){

        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.username=:username",User.class);

        query.setParameter("username", username);

        if(query.getResultList().isEmpty()){
            return Optional.empty();
        }

        return Optional.ofNullable(query.getSingleResult());
    }

    @Transactional(readOnly = true)
    public Optional<User> findUserByAccountNumber(String accountNumber){

        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.accountNumber=:accountNumber",User.class);
        query.setParameter("accountNumber", accountNumber);

        if(query.getResultList().isEmpty()){
            return Optional.empty();
        }
        return Optional.ofNullable(query.getSingleResult());

    }

    @Transactional
    public void updateUser(User user){

        entityManager.merge(user);
    }


}
