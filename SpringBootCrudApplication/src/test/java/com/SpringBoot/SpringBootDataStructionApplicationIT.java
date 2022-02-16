package com.SpringBoot;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.model.LinkedListNode;
import com.repository.LinkedListRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SpringBootDataStructionApplicationIT {
	private Random random = new Random();

	@LocalServerPort
	private int port;
	@Autowired
	LinkedListRepository linkedlistRepository;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldAddatTheBegeningOfTheLinkedList() {
		random.setSeed(2);
		for (int i = 0; i < 5; i++) {
			LinkedListNode node1 = new LinkedListNode();
			node1.setData(4 + random.nextInt());

			ResponseEntity<String> linkedlistAddResponseAtBeginning = restTemplate.postForEntity(
					"http://localhost:" + port + "/datastructures/LinkedlistAddBeginning", node1, String.class);
			log.info(linkedlistAddResponseAtBeginning.getBody());
		}
	}

	@Test
	public void shouldAddAtTheEnd() {
		random.setSeed(4);
		for (int i = 0; i < 5; i++) {
			LinkedListNode node1 = new LinkedListNode();
			node1.setData(4 + random.nextInt());

			ResponseEntity<String> linkedlistAddResponseAtEnd = restTemplate.postForEntity(
					"http://localhost:" + port + "/datastructures/linkedListAddEnd", node1, String.class);
			log.info(linkedlistAddResponseAtEnd.getBody());
		}
	}

	@Test
	public void shouldDisplayLinkedList() {
		shouldAddatTheBegeningOfTheLinkedList();
		ResponseEntity<String> linkedListDisplayResponse = restTemplate
				.getForEntity("/datastructures/displayLinkedList", String.class);
		log.info(linkedListDisplayResponse.getBody());

		shouldAddAtTheEnd();
		ResponseEntity<String> linkedListDisplayResponse1 = restTemplate
				.getForEntity("/datastructures/displayLinkedList", String.class);
		log.info(linkedListDisplayResponse1.getBody());
	}

	@Test

	public void shouldSearchLinkedList() {
		LinkedListNode node2 = new LinkedListNode();
		node2.setData(4);

		restTemplate.postForEntity("http://localhost:" + port + "/datastructures/LinkedlistAddBeginning", node2,
				String.class);

		LinkedListNode node3 = new LinkedListNode();
		node3.setData(6);

		ResponseEntity<String> linkedlistAddResponseAtBeginning1 = restTemplate.postForEntity(
				"http://localhost:" + port + "/datastructures/LinkedlistAddBeginning", node3, String.class);
		assertTrue(linkedlistAddResponseAtBeginning1.getBody() != null);
		ResponseEntity<String> linkedListSearchResponse = restTemplate.postForEntity("/datastructures/searchLinkedList",
				node2, String.class);
		log.info(linkedListSearchResponse.getBody());
		ResponseEntity<String> linkedListSearchResponse1 = restTemplate
				.postForEntity("/datastructures/searchLinkedList", node3, String.class);
		log.info(linkedListSearchResponse1.getBody());
		assertTrue(linkedlistAddResponseAtBeginning1.getBody() != null);

	}

	@Test
	public void shouldDeleteFromLinkedList() {
		LinkedListNode node2 = new LinkedListNode();
		node2.setData(4);

		ResponseEntity<String> linkedlistAddResponseAtBeginning = restTemplate.postForEntity(
				"http://localhost:" + port + "/datastructures/LinkedlistAddBeginning", node2, String.class);

		assertTrue(linkedlistAddResponseAtBeginning.getBody() != null);
		LinkedListNode node3 = new LinkedListNode();
		node3.setData(6);

		ResponseEntity<String> linkedlistAddResponseAtBeginning1 = restTemplate.postForEntity(
				"http://localhost:" + port + "/datastructures/LinkedlistAddBeginning", node3, String.class);
		restTemplate.delete("/datastructures/deleteLinkedListElement");
		assertTrue(linkedlistAddResponseAtBeginning1.getBody() != null);
	}

	@Test
	void contextLoads() {

	}

}
