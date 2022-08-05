package com.cg.repository;

import com.cg.model.Capacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapacityRepository extends JpaRepository<Capacity, Long> {
}