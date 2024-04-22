package org.example.nnpia_sem_kalendar;

import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.example.nnpia_sem_kalendar.Repository.ApplicationUserRepository;
import org.example.nnpia_sem_kalendar.Repository.TaskRepository;
import org.example.nnpia_sem_kalendar.contollers.TaskController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTests {

	private MockMvc mockMvc;

	@MockBean
	private ApplicationUserRepository userRepository;

	@MockBean
	private TaskRepository taskRepository;

	@InjectMocks
	private TaskController taskController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
	}

	@Test
	void allTasksByDate_ValidUser_ReturnsTasks() throws Exception {
		String username = "testUser";
		String date = "2021-04-01";
		LocalDate parsedDate = LocalDate.of(2021, 4, 1);
		ApplicationUser user = new ApplicationUser();
		user.setId(1L);

		when(userRepository.findByUsername(username)).thenReturn(user);
		when(taskRepository.getAllByDate(user.getId(), parsedDate)).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/allTasksByDate")
						.param("username", username)
						.param("date", date))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

		verify(userRepository, times(1)).findByUsername(username);
		verify(taskRepository, times(1)).getAllByDate(user.getId(), parsedDate);
	}

	@Test
	void getAllTasks_UserNotFound_ReturnsNull() throws Exception {
		String username = "nonExistentUser";

		when(userRepository.findByUsername(username)).thenReturn(null);

		mockMvc.perform(get("/getAllTasks")
						.param("username", username))
				.andExpect(status().isOk())
				.andExpect(content().string(""));

		verify(userRepository, times(1)).findByUsername(username);
		verifyNoInteractions(taskRepository);
	}

}
