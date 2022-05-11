package com.example.coursework.models.repositories;

import com.example.coursework.models.Order;
import com.example.coursework.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByLogin(String login);
    User findUserById(Long id);

    @Query(value = "SELECT * FROM users WHERE id = ?1",
            nativeQuery = true)
    List<Order> findOBUI(Long id);
    @Query(value = "DELETE FROM users WHERE id = ?1",
            nativeQuery = true)
    Void delUI(Long id);
    @Query(value = "UPDATE users SET name=?2 WHERE id = ?1",
            nativeQuery = true)
    Void updUIsurname(Long id, String surname);
    @Query(value = "UPDATE users SET name=?2 WHERE id = ?1",
            nativeQuery = true)
    Void updUIname(Long id, String name);
}
