package com.example.ipldashboard.util;

import com.example.ipldashboard.models.Team;
import com.example.ipldashboard.repository.MatchRepository;
import com.example.ipldashboard.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TeamUtils {
    MatchRepository matchRepository;
    TeamRepository teamRepository;
    @Autowired
    public TeamUtils(MatchRepository matchRepository,
                     TeamRepository teamRepository){
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }

    public Team teamDetail(String teamName){
        Team team = new Team();
        team.setTeam_name(teamName);
        int matches = matchRepository.getallmatches(teamName);
        int wins = matchRepository.getallwins(teamName);
        int draws = matchRepository.getalldraws(teamName);
        int losses = matches - wins - draws;
        team.setTotal_match(matches);
        team.setTotal_win(wins);
        team.setTotal_draw(draws);
        team.setTotal_loss(losses);
        return team;
    }

    public List<String> teamNames(){
        return matchRepository.getallteams();
    }

    public void save(){
        List<String> teams = teamNames();
        for (String team:teams){
            Team temp = teamDetail(team);
//            log.info(temp.toString());
            teamRepository.save(temp);
        }
    }
}
