package com.example.testetecnico.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
		.andExpect(jsonPath("$.[*].id").value(animaisFazenda.getId()))
		.andExpect(jsonPath("$.[*].tagIdentificacao").value(animaisFazenda.getTagIdentificacao()));
	}
	

}
