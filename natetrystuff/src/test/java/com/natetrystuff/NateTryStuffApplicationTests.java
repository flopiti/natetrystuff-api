package com.natetrystuff;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NateTryStuffApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void contextLoads() {
    }

    @Test
    void testAPI() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/test", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Response status is not OK");
        assertEquals("API test successful!", response.getBody(), "Response body does not match");
    }
}
