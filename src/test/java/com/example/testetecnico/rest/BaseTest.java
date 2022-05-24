package com.example.testetecnico.rest;

import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.Validator;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseTest {
	
    @Autowired
    protected PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    protected EntityManager em;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected Validator validator;

    protected MockMvc restMockMvc;


    @BeforeEach
    public void setup() {
        openMocks(this);
        this.restMockMvc = standaloneSetup(getResources())
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setValidator(validator)
            .build();

    }

    public Object getResource() {
        return null;
    }

    public Object[] getResources() {
        Object resource = getResource();
        return resource != null ? new Object[]{resource} : null;
    }

}
