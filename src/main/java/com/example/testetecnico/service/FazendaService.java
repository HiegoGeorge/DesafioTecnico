package com.example.testetecnico.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.testetecnico.entities.Fazenda;
import com.example.testetecnico.repository.FazendaRepository;

@Service
//@Transactional
public class FazendaService {
	
	@Autowired
	private FazendaRepository fazendaRepository;
	
	public Fazenda save(Fazenda fazenda) {
		return this.fazendaRepository.save(fazenda);
	}
	
	
	public List<Fazenda> buscarAll(){
		return fazendaRepository.findAll();
	}
	
	
	public Fazenda update(String id, Fazenda fazenda) {
		fazenda = findById(id);
		fazenda.setNomeFazenda(fazenda.getNomeFazenda());
		return fazendaRepository.save(fazenda);
	}
	
	public void delete(String id) {
		fazendaRepository.deleteById(id);
	}

	
	public Fazenda findById(String id) {
		Optional<Fazenda> fazenda = fazendaRepository.findById(id);
		return fazenda.orElseThrow( ()-> new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo: " + Fazenda.class.getName(), id));
	}
	

}
