package org.example.nnpia_sem_kalendar;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.example.nnpia_sem_kalendar.Entities.Person;
import org.example.nnpia_sem_kalendar.contollers.PorsonController;
import org.example.nnpia_sem_kalendar.Repository.ApplicationUserRepository;
import org.example.nnpia_sem_kalendar.Repository.PersonRepository;
import org.example.nnpia_sem_kalendar.service.NamedayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(PorsonController.class)
public class PersonControllerTests {

    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private ApplicationUserRepository userRepository;

    @MockBean
    private NamedayService holidayService;

    @InjectMocks
    private PorsonController personController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    void allUser_ValidUser_ReturnsPersons() throws Exception {
        String username = "testUser";
        ApplicationUser user = new ApplicationUser();
        user.setId(1L);
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        List<Person> persons = Arrays.asList(person);

        when(userRepository.findByUsername(username)).thenReturn(user);
        when(personRepository.getAll(user.getId())).thenReturn(persons);

        mockMvc.perform(get("/getAllPersons")
                        .param("username", username))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("John"));

        verify(userRepository, times(1)).findByUsername(username);
        verify(personRepository, times(1)).getAll(user.getId());
    }
}
