package com.example.ipldashboard.controller;

import com.example.ipldashboard.models.Match;
import com.example.ipldashboard.repository.CSVRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/dashboard")
@Slf4j
public class DashBoardController {

    @Autowired
    public CSVRepository csvRepository;

    @PostMapping("/matches")
    public List<Match> matchListBetweenTwoTeams(@RequestBody Map<String,String> teams){
        List<String> team = new ArrayList<>();
        team.add(teams.get("team1"));
        team.add(teams.get("team2"));
//        log.info(team.get(0)+" "+team.get(1));
        return csvRepository.getmatchinfo(team);
    }

    @PostMapping("/seasonwinner")
    public String getIPLWinners(@RequestBody Map<String,String> season){
        log.info(csvRepository.getseasonwinner(season.get("season")));
        return csvRepository.getseasonwinner(season.get("season"));
    }

    @GetMapping("/teamdetails")
    public void getTeamDetails(@RequestParam String team){
        List<Match> matches = csvRepository.getlast4teammatch(team);
        int wins = csvRepository.getwins(team);
        int allmatches = csvRepository.getallmatches(team);
        int losses = allmatches - wins;
        log.info(matches.toString());
        log.info(String.valueOf(wins)+"/"+losses);
        log.info(String.valueOf(allmatches));
        return;
    }

    @GetMapping("/tosswinners")
    public List<Object[]> gettosswinners(){
        return csvRepository.gettossdecisions();
    }

//
//    @GetMapping("/testing")
//    public List<Match> getbyseason(@RequestParam String team){
//        return csvRepository.findByTeam1(team);
//    }
}
