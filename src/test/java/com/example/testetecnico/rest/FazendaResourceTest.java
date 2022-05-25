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
import java.util.UUID;

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

import com.example.testetecnico.entities.Fazenda;
import com.example.testetecnico.repository.FazendaRepository;
import com.example.testetecnico.service.FazendaService;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FazendaResourceTest extends AbstractTest{


	@Autowired
	private FazendaRepository fazendaRepository;
	
	@Autowired
	private FazendaResource fazendaResource;
	
	@Autowired
	private FazendaService fazendaService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private EntityManager em;
		
	private Fazenda fazenda;
	

	@BeforeEach
	public void setup() {
		fazenda = new Fazenda();
		fazenda.setId("1");
		fazenda.setNomeFazenda("Fazenda Mariana");
		fazendaRepository.save(fazenda);
	}
	
	@Test
	@Transactional
	public void getAllFazenda() throws Exception{	
		this.mockMvc.perform(get("/fazenda"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		//.andExpect(jsonPath("$[*].id").value(fazenda.getId()))
		.andExpect(jsonPath("$.[*].id").isNotEmpty())
		.andExpect(jsonPath("$.[*].nomeFazenda").value(fazenda.getNomeFazenda()));
	}


	
	@Test
	@Transactional
	public void criarFazenda() throws Exception{	
		fazendaRepository.saveAndFlush(fazenda);
		int databaseSizeBeforeCreate = fazendaRepository.findAll().size();		
		String teste = super.mapToJson(fazenda);
				

		em.detach(fazenda);
		this.mockMvc.perform(post("/fazenda")
		.contentType(MediaType.APPLICATION_JSON)
		.content(teste))
		.andExpect(status().isCreated())
//		.andExpect(jsonPath("$.id").value(fazenda.getId()))
		.andExpect(jsonPath("$.id").isNotEmpty())
		.andExpect(jsonPath("$.nomeFazenda").value(fazenda.getNomeFazenda()));
		
		List<Fazenda> list = fazendaRepository.findAll();
		assertThat(list).hasSize(databaseSizeBeforeCreate + 1);
		Fazenda fazendaTeste = list.get(list.size() -1);
		//valida se esta na base
		assertThat(fazendaTeste.getNomeFazenda()).isEqualTo(fazenda.getNomeFazenda());			
	}		


	@Test
	@Transactional
	public void updateFazenda() throws Exception{	
		fazendaRepository.saveAndFlush(fazenda);
		
		int databaseSizeBeforeUptade = fazendaRepository.findAll().size();		
		
		Fazenda updateFazenda = fazendaRepository.findById(fazenda.getId()).get();
		updateFazenda.setNomeFazenda("capital do boi");		
		
		String teste = super.mapToJson(fazenda);
		
		this.mockMvc.perform(put("/fazenda/{id}",fazenda.getId())
		.contentType(MediaType.APPLICATION_JSON)
		.content(teste))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(fazenda.getId()))
		.andExpect(jsonPath("$.nomeFazenda").value(fazenda.getNomeFazenda()));
		
		List<Fazenda> list = fazendaRepository.findAll();
		assertThat(list).hasSize(databaseSizeBeforeUptade);
		Fazenda fazendaTeste = list.get(list.size() -1);
		//valida se alteraçao foi realizada na base
		assertThat(fazendaTeste.getNomeFazenda()).isEqualTo(updateFazenda.getNomeFazenda());			
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
		
		//valida se a base esta vazia
		List<Fazenda> fazendaList = fazendaRepository.findAll();
		assertThat(fazendaList).hasSize(dataBaseSizeBeforeDelete - 1);
		
	}

	
}
