package com.example.testetecnico.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.testetecnico.entities.AnimaisFazenda;
import com.example.testetecnico.entities.Fazenda;
import com.example.testetecnico.repository.AnimaisFazendaRepository;

@Service
@Transactional
public class AnimaisFazendaService {
	
	@Autowired
	private AnimaisFazendaRepository animaisFazendaRepository;
	
	public List<AnimaisFazenda> save(List<AnimaisFazenda> animais){
	
		return  animaisFazendaRepository.saveAll(animais);
	}

	
	public List<AnimaisFazenda> buscar(){
		return animaisFazendaRepository.findAll();
	}
	
	public void delete(String id) {
		animaisFazendaRepository.deleteById(id);
	}
	
	public AnimaisFazenda update(String id, AnimaisFazenda animais) {
		animais = findById(id);
		animais.setTagIdentificacao(animais.getTagIdentificacao());
		return animaisFazendaRepository.save(animais);
	}

	
	public AnimaisFazenda findById(String id) {
		Optional<AnimaisFazenda> animais = animaisFazendaRepository.findById(id);
		return animais.orElseThrow( ()-> new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo: " + AnimaisFazenda.class.getName(), id));
	}
}
