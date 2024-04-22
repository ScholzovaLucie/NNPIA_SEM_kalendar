package org.example.nnpia_sem_kalendar;

import org.example.nnpia_sem_kalendar.Entities.ApplicationUser;
import org.example.nnpia_sem_kalendar.Repository.ApplicationUserRepository;
import org.example.nnpia_sem_kalendar.contollers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationUserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void verifyUser_ReturnsUser_WhenCredentialsAreCorrect() throws Exception {
        String username = "testuser";
        String password = "testpass";

        ApplicationUser user = new ApplicationUser();
        user.setUsername(username);
        user.setPassword(password);

        when(userRepository.findByUsernameAndPassword(username, password)).thenReturn(user);

        mockMvc.perform(get("/verifyUser")
                        .param("username", username)
                        .param("password", password))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(username));

        verify(userRepository).findByUsernameAndPassword(username, password);
    }

    @Test
    public void allUser_ReturnsAllUsers() throws Exception {
        List<ApplicationUser> users = Arrays.asList(new ApplicationUser(), new ApplicationUser());
        when(userRepository.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/allUser"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(userRepository).getAllUsers();
    }

    @Test
    public void createUser_CreatesNewUserAndReturnsIt() throws Exception {
        String username = "newuser";
        String password = "newpass";
        String firstName = "New";
        String lastName = "User";

        ApplicationUser newUser = new ApplicationUser();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);

        when(userRepository.findByUsernameAndPassword(username, password)).thenReturn(newUser);

        mockMvc.perform(put("/createUser")
                        .param("username", username)
                        .param("password", password)
                        .param("firstName", firstName)
                        .param("lastName", lastName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(username));

        verify(userRepository).save(any(ApplicationUser.class));
        verify(userRepository).findByUsernameAndPassword(username, password);
    }
}
