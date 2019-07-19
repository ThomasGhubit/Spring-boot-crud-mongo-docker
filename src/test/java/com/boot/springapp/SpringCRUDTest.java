package com.boot.springapp;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.boot.controller.TaskController;
import com.boot.repository.TaskRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class SpringCRUDTest {
    @Autowired
    TaskController taskController;

    @Autowired
    TaskRepository taskRepository;

	@Test
	public void testListAll() throws IOException {
		// TestRestTemplate restTemplate = new TestRestTemplate();
		// ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/tasks", String.class);

	    // assertThat(response.getStatusCode() , equalTo(HttpStatus.OK));

	    // ObjectMapper objectMapper = new ObjectMapper();
	    // JsonNode responseJson = objectMapper.readTree(response.getBody());

	    // assertThat( responseJson.isMissingNode() , is(false) );
	    // assertThat( responseJson.toString() , equalTo("[]") );
	}
}