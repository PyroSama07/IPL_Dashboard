package com.example.ipldashboard;

import com.example.ipldashboard.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class IpldashboardApplicationTests {

	@Autowired
	private MatchRepository csvRepository;
	@Test
	void contextLoads() {
	}

	@Test
	public void whenFindingCustomerById_thenCorrect() {
	}

}
