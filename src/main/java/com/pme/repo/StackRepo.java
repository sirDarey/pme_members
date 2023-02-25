package com.pme.repo;

import com.pme.entity.TechStack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StackRepo extends JpaRepository<TechStack, Integer> {
    TechStack findByName(String name);
}
