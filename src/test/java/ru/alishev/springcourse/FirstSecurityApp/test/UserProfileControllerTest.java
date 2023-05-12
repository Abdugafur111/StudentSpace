package ru.alishev.springcourse.FirstSecurityApp.test;


import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ru.alishev.springcourse.FirstSecurityApp.controllers.UserProfileController;
import ru.alishev.springcourse.FirstSecurityApp.models.UserProfile;
import ru.alishev.springcourse.FirstSecurityApp.repositories.UserProfileDAO;

public class UserProfileControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserProfileDAO userProfileDAO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        UserProfileController userProfileController = new UserProfileController(userProfileDAO, null);
        mockMvc = MockMvcBuilders.standaloneSetup(userProfileController).build();
    }

    @Test
    public void testIndex() throws Exception {
        // given
        UserProfile user1 = new UserProfile();
        user1.setStudentId(Integer.parseInt("123456"));
        UserProfile user2 = new UserProfile();
        user2.setStudentId(Integer.parseInt("789012"));
        List<UserProfile> users = Arrays.asList(user1, user2);
        Mockito.when(userProfileDAO.getAllUsers()).thenReturn(users);

        // when, then
        mockMvc.perform(get("/userprofile"))
                .andExpect(status().isOk())
                .andExpect(view().name("userprofile/index"))
                .andExpect(model().attribute("users", users));
    }

    @Test
    public void testEdit() throws Exception {
        // given
        UserProfile user = new UserProfile();
        user.setStudentId(1);
        user.setStudentId(Integer.parseInt("123456"));
        Mockito.when(userProfileDAO.getUserById(1)).thenReturn(user);

        // when, then
        mockMvc.perform(get("/userprofile/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("userprofile/edit"))
                .andExpect(model().attribute("userprofile", user));
    }

    @Test
    public void testUpdate() throws Exception {
        // given
        UserProfile user = new UserProfile();
        user.setStudentId(32456234);

        // when
        mockMvc.perform(post("/userprofile/32456234")
                        .param("studentId", "32456234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/posts"))
                .andExpect(model().hasNoErrors());

        // then
        verify(userProfileDAO).updateUser(eq(32456234), eq(user));
    }

}
