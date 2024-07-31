package com.derek.user.controller;

import com.derek.common.constants.ResultCodes;
import com.derek.user.entity.User;
import com.derek.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUserName("testUser");
        user.setEmail("test@example.com");
        user.setMobile("13111111111");
    }

    @Test
    public void testCreateUser() throws Exception {
        // Mock the `save` method
        when(userService.save(any(User.class))).thenReturn(true);
        // Mock the `sendEmailForUser` method
        doNothing().when(userService).sendEmailForUser(any(User.class));

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\":\"testUser\",\"email\":\"test@example.com\",\"mobile\":\"13111111111\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCodes.SUCCESS));
    }

    @Test
    public void testListUsers() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(user);

        when(userService.listUsers()).thenReturn(userList);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCodes.SUCCESS))
                .andExpect(jsonPath("$.object[0].userName").value("testUser"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        // Mock the `updateById` method
        when(userService.updateById(any(User.class))).thenReturn(true);

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\":\"updatedUser\",\"email\":\"updated@example.com\",\"mobile\":\"13111111111\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCodes.SUCCESS));
    }

    @Test
    public void testDeactivateUsers() throws Exception {
        // Mock the `updateBatchById` method
        when(userService.updateBatchById(anyList())).thenReturn(true);
        // Mock the `getById` method
        when(userService.getById(1L)).thenReturn(user);

        mockMvc.perform(delete("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[1]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ResultCodes.SUCCESS));
    }
}
