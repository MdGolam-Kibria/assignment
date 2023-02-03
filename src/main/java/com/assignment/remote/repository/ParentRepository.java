package com.assignment.remote.repository;

import com.assignment.remote.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParentRepository extends JpaRepository<Parent, Long> {

    Parent findByIdAndIsActiveTrue(Long id);

    List<Parent> findAllByIsActiveTrue();
}
