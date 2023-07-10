/*package com.rekrutacja.zadanie;

import com.rekrutacja.zadanie.controllers.UserRESTController;
import com.rekrutacja.zadanie.model.entitys.ContactInfo;
import com.rekrutacja.zadanie.model.entitys.User;
import com.rekrutacja.zadanie.services.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserRESTControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserRESTController userRestController;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
	}

	@Test
	public void testAddContactInfo() throws Exception {
		User user = new User(); // dane testowe
		when(userService.addContactInfo(anyLong(), any(ContactInfo.class))).thenReturn(user);
		mockMvc.perform(post("/api/users/1/contactInfos")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"email\":\"test@example.com\"}"))
				.andExpect(status().isCreated());
	}

	@Test
	public void testGetAllUsers() throws Exception {
		User user1 = new User(); // dane testowe
		User user2 = new User(); // dane testowe
		when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));
		mockMvc.perform(get("/api/users")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").isArray());
	}

	@Test
	public void testGetUserByPesel() throws Exception {
		User user = new User(); // dane testowe
		when(userService.getUserByPesel(any(String.class))).thenReturn(user);
		mockMvc.perform(get("/api/users/12345678901") // przykładowy PESEL
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testExportUsersToFile() throws Exception {
		mockMvc.perform(post("/api/users/export")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}*/
