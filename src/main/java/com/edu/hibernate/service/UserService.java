package com.edu.hibernate.service;

import com.edu.hibernate.dao.UserDao;
import com.edu.hibernate.dto.UserDto;
import com.edu.hibernate.dto.UserDtoResponse;
import com.edu.hibernate.entity.User;
import com.edu.hibernate.exception.user.UserAlreadyExistException;
import com.edu.hibernate.exception.user.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

@AllArgsConstructor
@Service
public class UserService {

    private UserDao repository;

    public UserDtoResponse createUser(UserDto userDto){

        if (repository.find(userDto.getUsername()).isPresent()){
            throw new UserAlreadyExistException(userDto.getUsername());
        }
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(userDto.getPassword());
        newUser.setBalance(BigDecimal.ZERO);
        newUser.setAccountNumber(generateUserAccountNumber());
        repository.createUser(newUser);

        return new UserDtoResponse(newUser.getUsername(), newUser.getBalance());

    }

    public User findUserByUsername(String username){
        Optional<User> user = repository.find(username);
        if (user.isEmpty()){
            throw new UserNotFoundException(username);
        }
        return user.get();
    }

    public User findUserByAccountNumber(String accountNumber){
        Optional<User> user = repository.findUserByAccountNumber(accountNumber);
        if (user.isEmpty()){
            throw new UserNotFoundException(accountNumber);
        }
        return user.get();
    }

    public void updateUser(User user){
        repository.updateUser(user);
    }

    public String generateUserAccountNumber(){

        Random random = new Random();
        StringBuffer accountNumber = new StringBuffer();
        for (int i = 0; i < 16; i++) {
            accountNumber.append(random.nextInt(8) + 1);
        }
        return accountNumber.toString();
    }

}
