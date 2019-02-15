package com.memebershiprest.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.memebershiprest.demo.controller.MembersController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemebrshipApplicationTests {

	private MockMvc mockMvc;
	@InjectMocks
	private MembersController membersController;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(membersController).build();
	}

	@Test
	public void getAllMembers() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/members/")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

	}

	@Test
	public void saveNewMember() throws Exception {

		String json = "{\\n\\t\\\"firstName\\\": \\\"Jack\\\",\\n\\t\\\"secondName\\\": \\\"Mark\\\",\\n\\t\\\"planId\\\": 1,\\n\\t\\\"dob\\\" : \\\"2019-02-02\\\"\\n}";
		mockMvc.perform(MockMvcRequestBuilders.post("/members/").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());

	}

}

