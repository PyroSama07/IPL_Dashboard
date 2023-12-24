package com.example.ipldashboard;

import com.example.ipldashboard.repository.TeamRepository;
import com.example.ipldashboard.util.ReadCSV;
import com.example.ipldashboard.util.TeamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class IpldashboardApplication {

	@Autowired
	ReadCSV readCSV;
	TeamUtils teamUtils;
	public IpldashboardApplication(ReadCSV readCSV,
								   TeamUtils teamUtils) throws FileNotFoundException {
		this.readCSV = readCSV;
		this.teamUtils = teamUtils;
		readCSV.save();
		teamUtils.save();
	}

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(IpldashboardApplication.class, args);
	}

}
