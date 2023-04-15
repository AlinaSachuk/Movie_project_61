package com.tms.service;

import com.tms.domain.User;
import com.tms.domain.dto.UserDto;
import com.tms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    private final String USER_ROLE = "USER";
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired

    public ArrayList<User> getAllUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(new User());
    }

    public User createUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setBirthdate(userDto.getBirthdate());
        user.setTelephoneNumber(userDto.getTelephoneNumber());
        user.setCreated(new Date(System.currentTimeMillis()));
        user.setChanged(new Date(System.currentTimeMillis()));
        user.setDeleted(false);
        user.setRole(USER_ROLE);
        return userRepository.save(user);
    }

    public User updateUser(UserDto userDto) {
        User user = userRepository.findUserById(userDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("User by id not found: " + userDto.getId()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setBirthdate(userDto.getBirthdate());
        user.setTelephoneNumber(userDto.getTelephoneNumber());
        user.setChanged(new Date(System.currentTimeMillis()));
        return userRepository.saveAndFlush(user);
    }

    //public boolean addMovieToUser(int userId, int movieId) {
    //    return userRepository.addMovieToUser(userId, movieId);
    //}

    //public ArrayList<Movie> getMoviesForSingleUser(int id) {
    //    return userRepository.getMoviesForSingleUser(id);
    //}

    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }

    public Optional<User> findUserByLastName(String lastName) {
        return userRepository.findUserByLastName(lastName);
    }
}
