package com.nbcomp.petpeers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbcomp.petpeers.dto.PetDto;
import com.nbcomp.petpeers.service.PetService;
import com.nbcomp.petpeers.service.UserService;

@WebMvcTest(MainController.class)
class MainControllerTest {
	@MockBean
	private PetService petService;

	@MockBean
	private UserService userService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void savePetTest() throws Exception {
		//creating a object
		PetDto petDto = PetDto.builder().petAge(12).petName("cd").petPlace("lakimpur").build();
		//creating a saved object by giving it id
		PetDto saved = new PetDto();
		BeanUtils.copyProperties(petDto, saved);
		saved.setPetId(1l);
		//giving behaviour to dependent object
		when(petService.savePet(petDto)).thenReturn(saved);
		//creating json out of object
		ObjectMapper mapper = new ObjectMapper();
		String petJson = mapper.writeValueAsString(petDto);
		//creating request and performing action
		MockHttpServletRequestBuilder req = MockMvcRequestBuilders.post("/api/pets").contentType("application/json")
				.content(petJson);
		MockHttpServletResponse r = mockMvc.perform(req).andReturn().getResponse();
		//getting object from response json
		PetDto pd = new ObjectMapper().readValue(r.getContentAsString(), PetDto.class);
	
		assertEquals(1l,pd.getPetId());
	}
	
	//exceptional handling method below
	
	@Test
	void savePetAgeValidaionTest() throws Exception {
		PetDto petDto = PetDto.builder().petAge(-1).petName("cd").petPlace("lakimpur").build();
		
		ObjectMapper mapper = new ObjectMapper();
		String petJson = mapper.writeValueAsString(petDto);
		MockHttpServletRequestBuilder req = MockMvcRequestBuilders.post("/api/pets").contentType("application/json")
				.content(petJson);
		int status = mockMvc.perform(req).andReturn().getResponse().getStatus();
		assertEquals(400,status);
	}
}
