package com.example.testetecnico.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.testetecnico.entities.Fazenda;
import com.example.testetecnico.service.FazendaService;

@RestController
public class FazendaResource {
	
	private static final Logger log = LoggerFactory.getLogger(FazendaResource.class);
	
	
	private final FazendaService fazendaService;	
	
	
	public FazendaResource(FazendaService fazendaService) {
		this.fazendaService = fazendaService;
	}


	@PostMapping("/fazenda")
	public ResponseEntity<Fazenda> getFazenda(@Valid @RequestBody Fazenda fazenda) throws URISyntaxException
	{
		
		Fazenda result = fazendaService.save(fazenda);
		
		return ResponseEntity.created(new URI("/fazenda/" + result.getId())).body(result);
	}
	
	
	@GetMapping("/fazenda")
	public ResponseEntity<List<Fazenda>> getAllFazenda(){
		return  ResponseEntity.ok(fazendaService.buscarAll());
	}
	

	@PutMapping("/fazenda/{id}")
	public ResponseEntity<Fazenda> updateFazenda(@PathVariable String id, @RequestBody Fazenda fazenda)
	{
		if(fazenda.getId() == null) {
			log.error("id nao existe!");
		}
		
		Fazenda result = fazendaService.update(id, fazenda);
		
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping("/fazenda/{id}")
	public ResponseEntity<Void> deleteFazenda(@PathVariable String id){
		fazendaService.delete(id);
		return ResponseEntity.noContent().build();
	}
		

	
}
