package com.example.Ejercicios456.controllers;

import com.example.Ejercicios456.entitys.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {

        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);

    }

    @Test
    void findAll() {

        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/Laptop/findall", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        //assertEquals(200, response.getStatusCode());

        //List<Laptop> laptops = Arrays.asList(response.getBody());


    }

    @Test
    void findOneById() {

        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/Laptop/findbyid/1", Laptop.class);

        //assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void create() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                        "precio": 999.0,
                        "marca": "PcComponentes"
                    }
                """;

        HttpEntity<String> request = new HttpEntity<>(json,httpHeaders);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/Laptop/save",HttpMethod.POST, request, Laptop.class);

        Laptop result = response.getBody();
        assertEquals(1L, result.getId());
        assertEquals(999.0,result.getPrecio());

    }
}