package com.example.coursework.models.repositories;

import com.example.coursework.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByUserid(Long id);
    @Query(value = "SELECT * FROM orders WHERE user_id = ?1",
            nativeQuery = true)
    List<Order> findOBUI(Long id);
    @Query(value = "DELETE FROM orders WHERE id = ?1",
            nativeQuery = true)
    Void delBI(Long id);
    @Query(value = "DELETE FROM orders WHERE user_id = ?1",
            nativeQuery = true)
    Void delBUI(Long id);
    @Query(value = "UPDATE orders SET description=?2 WHERE id = ?1",
            nativeQuery = true)
    Void updBI(Long id, String decp);
}
