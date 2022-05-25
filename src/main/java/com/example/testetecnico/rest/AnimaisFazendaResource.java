package com.example.testetecnico.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.testetecnico.entities.AnimaisFazenda;
import com.example.testetecnico.entities.Fazenda;
import com.example.testetecnico.service.AnimaisFazendaService;

@RestController
public class AnimaisFazendaResource {
	private static final Logger log = LoggerFactory.getLogger(FazendaResource.class);
	
	
	private final AnimaisFazendaService animaisFazendaService;	
	
	public AnimaisFazendaResource(AnimaisFazendaService animaisFazendaService) {
		this.animaisFazendaService = animaisFazendaService;
	}


	@PostMapping("/animais")
	public ResponseEntity<List<AnimaisFazenda>> salvarFazenda(@RequestBody List<AnimaisFazenda> listAnimais ) throws URISyntaxException
	{
		animaisFazendaService.save(listAnimais);
		return ResponseEntity.created(new URI("/fazenda/")).build();
	}

	
	@GetMapping("/animais")
	public ResponseEntity<List<AnimaisFazenda>> getAllAnimais() throws URISyntaxException{
		return ResponseEntity.ok(animaisFazendaService.buscar());
	}
	
	@PutMapping("/animais/{id}")
	public ResponseEntity<AnimaisFazenda> updateFazenda(@PathVariable String id, @RequestBody AnimaisFazenda animais)
	{
		if(animais.getId() == null) {
			log.error("id nao existe!");
		}
		
		AnimaisFazenda result = animaisFazendaService.update(id, animais);
		
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping("/animais/{id}")
	public ResponseEntity<Void> deleteAnimais(@PathVariable String id){
		animaisFazendaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
