package com.example.testetecnico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.testetecnico.entities.AnimaisFazenda;


@Repository
public interface AnimaisFazendaRepository extends JpaRepository<AnimaisFazenda, String> {

}
