package com.example.testetecnico.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.testetecnico.entities.AnimaisFazenda;
import com.example.testetecnico.entities.Fazenda;
import com.example.testetecnico.repository.AnimaisFazendaRepository;
import com.example.testetecnico.service.AnimaisFazendaService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AnimaisFazendaResourceTest extends AbstractTest{
	
	@Autowired
	private AnimaisFazendaRepository animaisFazendaRepository;
	
	@Autowired
	private AnimaisFazendaResource animaisFazendaResource;
	
	@Autowired
	private AnimaisFazendaService animaisFazendaService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private EntityManager em;
	
	private AnimaisFazenda animaisFazenda;
	
	@BeforeEach
	public void setup() {
		animaisFazenda = new AnimaisFazenda();
		animaisFazenda.setId("1");
		animaisFazenda.setTagIdentificacao("123456789123456");
		animaisFazendaRepository.save(animaisFazenda);
	}
	
	@Test
	@Transactional
	public void getAllAnimais() throws Exception{			
		this.mockMvc.perform(get("/animais"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		//		.andExpect(jsonPath("$.[*].id").value(hasItem(animaisFazenda.getId())))
		.andExpect(jsonPath("$.[*].id").isNotEmpty())
		.andExpect(jsonPath("$.[*].tagIdentificacao").value(animaisFazenda.getTagIdentificacao()));
	}
	
	@Test
	@Transactional
	public void deleteAnimais() throws Exception{	
				//inicializa a base
		animaisFazendaRepository.saveAndFlush(animaisFazenda);
		
		int dataBaseSizeBeforeDelete = animaisFazendaRepository.findAll().size();		
		//realiza o delete
		this.mockMvc.perform(delete("/animais/{id}",animaisFazenda.getId()))
		.andExpect(status().isNoContent());
		
		//valida se a base esta vazia
		List<AnimaisFazenda> animaisList = animaisFazendaRepository.findAll();
		assertThat(animaisList).hasSize(dataBaseSizeBeforeDelete - 1);
		
	}
	
	@Test
	@Transactional
	public void criarAnimaisSemSucesso() throws Exception{
		animaisFazendaRepository.saveAndFlush(animaisFazenda);
		int databaseSizeBeforeCreate = animaisFazendaRepository.findAll().size();
		
		String teste = super.mapToJson(animaisFazenda);

		this.mockMvc.perform(post("/animais")
		.contentType(MediaType.APPLICATION_JSON)
		.content(teste))
		.andExpect(status().isBadRequest());

		List<AnimaisFazenda> animaisList = animaisFazendaRepository.findAll();
		assertThat(animaisList).hasSize(databaseSizeBeforeCreate);
	}
	

	@Test
	@Transactional
	public void criarFazendaSucesso() throws Exception{
		
		List<AnimaisFazenda> listAnimais = List.of(animaisFazenda);
		animaisFazenda.setTagIdentificacao("123456789123456");
				
		String teste = super.mapToJson(listAnimais);

		this.mockMvc.perform(post("/animais")
		.contentType(MediaType.APPLICATION_JSON)
		.content(teste))
		.andExpect(status().isCreated());

	}
	
	@Test
	@Transactional
	public void updateFazenda() throws Exception{
		animaisFazendaRepository.saveAndFlush(animaisFazenda);
		int databaseSizeBeforeCreate = animaisFazendaRepository.findAll().size();
			
	
		em.detach(animaisFazenda);
		AnimaisFazenda updateAnimais = animaisFazendaRepository.findById(animaisFazenda.getId()).get();
		updateAnimais.setTagIdentificacao("123456789123456");
		
		String teste = super.mapToJson(updateAnimais);

		this.mockMvc.perform(put("/animais/{id}", animaisFazenda.getId())
		.contentType(MediaType.APPLICATION_JSON)
		.content(teste))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(animaisFazenda.getId()))
		.andExpect(jsonPath("$.tagIdentificacao").value(animaisFazenda.getTagIdentificacao()));
		
		List<AnimaisFazenda> animaisList = animaisFazendaRepository.findAll();
		assertThat(animaisList).hasSize(databaseSizeBeforeCreate);

	}
}
