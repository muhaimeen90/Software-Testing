package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationManagerTest {

    private AuthenticationManager authenticationManager;

    @Mock
    private HashLibrary hashLibraryMock;

    @Mock
    private User userMock;

    String email = "123@gmai";
    String password = "123";
    String hashedPassword = "123hashed";

    @BeforeEach
    void setUp() {
        authenticationManager = new AuthenticationManager(hashLibraryMock);
        when(hashLibraryMock.hashPassword(password)).thenReturn(hashedPassword);
    }

    @Test
    void login() {
        // Mock user retrieval
        when(userMock.getPassword()).thenReturn(hashedPassword);
        authenticationManager.findByEmail(email); // This is a placeholder, actual mocking is below
        AuthenticationManager spyAuthManager = spy(authenticationManager);
        doReturn(userMock).when(spyAuthManager).findByEmail(email);
        // Mock password hashing
        lenient().when(hashLibraryMock.hashPassword(password)).thenReturn(hashedPassword);
        boolean result = spyAuthManager.login(email, password);

        assertTrue(result);
    }
}
