package com.example.ipldashboard.controller;

import com.example.ipldashboard.models.Match;
import com.example.ipldashboard.repository.CSVRepository;
import com.example.ipldashboard.util.ReadCSV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.*;

@RestController
@RequestMapping("/read")
@Slf4j
public class ReadCSVController {

    @Autowired
    public ReadCSV read;
    @Autowired
    public CSVRepository csvRepository;
    @Value("${matchcsvpath}")
    public String matchespath;

    @GetMapping("/csvtomatch")
    public List<Match> readFromCSV() throws FileNotFoundException {
        return read.readCSV(matchespath);
    }

    @GetMapping("/savedb")
    public String saveToDB() throws FileNotFoundException {
        List<Match> matches = read.readCSV(matchespath);
        for (Match match:matches){
            csvRepository.save(match);
        }
        log.info("Saved into db");
        return "Saved";
    }

//    @PostMapping("/matches")
//    public List<Match> matchListBetweenTwoTeams(@RequestParam(required = true) String team1,
//                            @RequestParam(required = true) String team2){
//        List<String> teams = new ArrayList<>();
//        teams.add(team1);
//        teams.add(team2);
//        return csvRepository.getmatchinfo(teams);
//    }

}
