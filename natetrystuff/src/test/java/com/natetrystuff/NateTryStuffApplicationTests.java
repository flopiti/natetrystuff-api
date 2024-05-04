package com.natetrystuff;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        assert response.getStatusCode().equals(HttpStatus.OK);
        assert "API test successful!".equals(response.getBody());
    }
}
