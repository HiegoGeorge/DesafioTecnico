package com.example.testetecnico.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.testetecnico.entities.AnimaisFazenda;
import com.example.testetecnico.entities.Fazenda;
import com.example.testetecnico.repository.AnimaisFazendaRepository;
import com.example.testetecnico.rest.FazendaResource;

@Service
@Transactional
public class AnimaisFazendaService {
	private static final Logger log = LoggerFactory.getLogger(FazendaResource.class);
	
	@Autowired
	private AnimaisFazendaRepository animaisFazendaRepository;
	
	public void save(List<AnimaisFazenda> animais){
	
		for(AnimaisFazenda a : animais) {
			//verifica se a tag e diferente de null e se tem o tamanho de 15digitos e verifica se tem apenas numeros e nao letras
			if(a.getTagIdentificacao()!=null && a.getTagIdentificacao().length()==15 && a.getTagIdentificacao().matches("^\\d+$")) {
				try {
					animaisFazendaRepository.saveAll(animais);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("Erro ao salvar");
				}
				
			 }			
		}
		
	}

	
	public List<AnimaisFazenda> buscar(){
		return animaisFazendaRepository.findAll();
	}
	
	public void delete(String id) {
		animaisFazendaRepository.deleteById(id);
	}
	
	public AnimaisFazenda update(String id, AnimaisFazenda animais) {
		AnimaisFazenda animais2 = findById(id);
		animais2.setTagIdentificacao(animais.getTagIdentificacao());
		return animaisFazendaRepository.save(animais2);
	}

	
	public AnimaisFazenda findById(String id) {
		Optional<AnimaisFazenda> animais = animaisFazendaRepository.findById(id);
		return animais.orElseThrow( ()-> new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo: " + AnimaisFazenda.class.getName(), id));
	}
}
