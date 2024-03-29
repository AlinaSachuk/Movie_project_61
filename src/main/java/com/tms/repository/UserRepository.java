package com.tms.repository;

import com.tms.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByLastName(String lastName);

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserById(int id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE user_table SET is_deleted = true WHERE id=:id", countQuery = "SELECT * from user_table WHERE id=:id")
    void deleteUser(Integer id);

}
