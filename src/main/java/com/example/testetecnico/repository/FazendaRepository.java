package com.example.testetecnico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.testetecnico.entities.Fazenda;



@Repository
public interface FazendaRepository extends JpaRepository<Fazenda, String>{
}
