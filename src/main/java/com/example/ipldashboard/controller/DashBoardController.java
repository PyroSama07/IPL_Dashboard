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
public class DashBoardController {

    @Autowired
    public MatchRepository matchRepository;
    @Autowired
    public TeamRepository teamRepository;

    @PostMapping("/matches")
    public List<Match> matchListBetweenTwoTeams(@RequestBody Map<String,String> teams){
        List<String> team = new ArrayList<>();
        team.add(teams.get("team1"));
        team.add(teams.get("team2"));
//        log.info(team.get(0)+" "+team.get(1));
        return matchRepository.getmatchinfo(team);
    }

    @PostMapping("/seasonwinner")
    public String getIPLWinners(@RequestBody Map<String,String> season){
        log.info(matchRepository.getseasonwinner(season.get("season")));
        return matchRepository.getseasonwinner(season.get("season"));
    }

    @GetMapping("/teamdetails")
    public Team getTeamDetails(@RequestParam String teamName){
        List<Match> matches = matchRepository.getlast4teammatch(teamName);
        Team team = teamRepository.getTeamDetails(teamName);
        team.setLatest_matches(matches);
        return team;
    }

    @GetMapping("/tosswinners")
    public List<Object[]> gettosswinners(){
        return matchRepository.gettossdecisions();
    }

//
//    @GetMapping("/testing")
//    public List<Match> getbyseason(@RequestParam String team){
//        return csvRepository.findByTeam1(team);
//    }
}
