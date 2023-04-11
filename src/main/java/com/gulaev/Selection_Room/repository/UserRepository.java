package com.gulaev.Selection_Room.repository;

import com.gulaev.Selection_Room.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
