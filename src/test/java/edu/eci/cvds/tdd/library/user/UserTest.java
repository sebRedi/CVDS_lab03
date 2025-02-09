package edu.eci.cvds.tdd.library.user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UserTest {

    @Test
    public void shouldReturnTheName() {
        String name = "julian";
        User user = new User();
        user.setName(name);
        assertEquals(name, user.getName());
    }

    public void shouldReturnTheId() {
        String id = "123456";
        User user = new User();
        user.setId(id);
        assertEquals(id, user.getId());
    }
}