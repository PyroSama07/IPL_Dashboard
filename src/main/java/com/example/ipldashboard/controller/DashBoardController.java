package com.example.ipldashboard.controller;

import com.example.ipldashboard.models.Match;
import com.example.ipldashboard.models.Team;
import com.example.ipldashboard.repository.MatchRepository;
import com.example.ipldashboard.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/dashboard")
@Slf4j
@CrossOrigin
public class DashBoardController {

    @Autowired
    public MatchRepository matchRepository;
    @Autowired
    public TeamRepository teamRepository;

    @GetMapping("/teamDetails")
    public Team getTeamDetails(@RequestParam String teamName){
        List<Match> matches = matchRepository.getlast4teammatch(teamName);
        Team team = teamRepository.getTeamDetails(teamName);
        team.setLatest_matches(matches);
        return team;
    }

    @GetMapping("/matchDetails")
    public List<Match> getMatchDetails(@RequestParam String teamName, @RequestParam int year){
        return matchRepository.getAllMatches(teamName,year);
    }

}
