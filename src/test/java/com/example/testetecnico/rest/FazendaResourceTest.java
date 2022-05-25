package com.example.testetecnico.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.testetecnico.entities.Fazenda;
import com.example.testetecnico.repository.FazendaRepository;
import com.example.testetecnico.service.FazendaService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebMvcTest(FazendaResource.class)
@ActiveProfiles("test")
public class FazendaResourceTest {

	//@MockBean objetos falsos
	@Autowired
	private FazendaRepository fazendaRepository;
	
	@Autowired
	private FazendaResource fazendaResource;
	
	@Autowired
	private FazendaService fazendaService;
	
	@Autowired
	private MockMvc mockMvc;
		
	private Fazenda fazenda;
	

	@BeforeEach
	public void setup() {
		fazenda = new Fazenda();
		fazenda.setNomeFazenda("Fazenda Mariana");
		fazendaRepository.save(fazenda);
	}
	
	@Test
	@Transactional
	public void getAllFazenda() throws Exception{	
		this.mockMvc.perform(get("/fazenda"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[*].id").value(fazenda.getId()))
		.andExpect(jsonPath("$[*].nomeFazenda").value(fazenda.getNomeFazenda()));
	}

	@Test
	@Transactional
	public void deleteFazenda() throws Exception{	
				//inicializa a base
		fazendaRepository.saveAndFlush(fazenda);
		
		int dataBaseSizeBeforeDelete = fazendaRepository.findAll().size();		
		//realiza o delete
		this.mockMvc.perform(delete("/fazenda/{id}",fazenda.getId()))
		.andExpect(status().isNoContent());
		
		//valida se a baseesta vazia
		List<Fazenda> fazendaList = fazendaRepository.findAll();
		assertThat(fazendaList).hasSize(dataBaseSizeBeforeDelete - 1);
		
	}
	
	@Test
	@Transactional
	public void updateFazenda() throws Exception{	
		this.mockMvc.perform(post("/fazenda"))
		.andExpect(status().isCreated());
		

		
	}

	
}
