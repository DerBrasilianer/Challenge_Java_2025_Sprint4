package com.fiap.gestaofrota;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // usa application-test.properties
class GestaoFrotaApplicationTests {

	@Test
	void contextLoads() {
		// Teste simples só para garantir que o contexto levanta
	}

}
