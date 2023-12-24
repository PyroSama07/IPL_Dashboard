package com.example.ipldashboard.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString

@Entity
@Table(name="team_info")
public class Team {
    @Id
    @GeneratedValue
    int id;

    String team_name;
    int total_match;
    int total_win;
    int total_loss;
    int total_draw;

    @Transient
    List<Match> latest_matches;

}
