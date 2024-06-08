package com.mateuspaz.transaction.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void deveRetornarStatus200EStringException() {
		ResponseEntity<String> response = restTemplate.getForEntity("/api/product/exception", String.class);
		assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
		assertEquals(response.getBody(), "Exception");
	}

	@Test
	public void deveRetornarStatus500PorTransactionSilentlyRollback() {
		ResponseEntity<String> response = restTemplate.getForEntity("/api/product/runtime-exception", String.class);
		assertThat(response.getStatusCode().is5xxServerError()).isTrue();
		assertThat(response.getBody()).contains("Internal Server Error");
	}

	@Test
	public void deveRetornarStatus200EStringRuntimeExceptionComRequiresNew() {
		ResponseEntity<String> response = restTemplate.getForEntity("/api/product/runtime-exception/requires-new", String.class);
		assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
		assertEquals(response.getBody(), "RuntimeException :: propagation = Propagation.REQUIRES_NEW");
	}

	@Test
	public void deveRetornarStatus200EStringRuntimeExceptionComNoRollbackFor() {
		ResponseEntity<String> response = restTemplate.getForEntity("/api/product/runtime-exception/no-rollback-for", String.class);
		assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
		assertEquals(response.getBody(), "RuntimeException :: noRollbackFor = RuntimeException.class");
	}

}
