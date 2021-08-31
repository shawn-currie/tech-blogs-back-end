package com.shawncurrie.techblogs.service.impl;

import com.shawncurrie.techblogs.io.entity.UserEntity;
import com.shawncurrie.techblogs.io.repository.UserRepository;
import com.shawncurrie.techblogs.shared.dto.UserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void getUser_covertsEntityToDTO_True() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setName("testName1");
        when(userRepository.findById(anyInt())).thenReturn(userEntity);

        UserDTO userDTO = userService.getUser(1);

        assertEquals(1, userDTO.getId());
        assertEquals("testName1", userDTO.getName());
    }
}