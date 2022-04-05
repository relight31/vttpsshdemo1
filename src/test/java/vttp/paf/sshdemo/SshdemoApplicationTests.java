package vttp.paf.sshdemo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp.paf.sshdemo.controllers.GiphyController;
import vttp.paf.sshdemo.services.GiphyService;

@SpringBootTest
class SshdemoApplicationTests {

	@Autowired
	GiphyService service;

	@Autowired
	GiphyController controller;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldLoadBeans() {
		assertNotNull(service);
		assertNotNull(controller);
	}
}
