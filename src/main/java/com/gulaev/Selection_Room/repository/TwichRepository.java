package com.gulaev.Selection_Room.repository;

import com.gulaev.Selection_Room.model.TwichTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwichRepository extends JpaRepository<TwichTranslation, Integer> {
}
