package com.example.ipldashboard;

import com.example.ipldashboard.util.ReadCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class IpldashboardApplication {

	@Autowired
	ReadCSV readSCV;
	public IpldashboardApplication(ReadCSV readCSV) throws FileNotFoundException {
		this.readSCV = readCSV;
		readSCV.save();
	}

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(IpldashboardApplication.class, args);
	}

}
