package com.example.ipldashboard.repository;

import com.example.ipldashboard.models.Match;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface CSVRepository extends CrudRepository<Match, Integer> {

    @Query(value = "select * from matchinfo where team1 in :teams and team2 in :teams",
            nativeQuery = true)
    List<Match> getmatchinfo(@Param("teams") List<String> teams);

    @Query(value = "select winning_team from matchinfo where match_number = 'Final' and season =:season",
            nativeQuery = true)
    String getseasonwinner(@Param("season") String season);

    @Query(value = "select * from matchinfo where team1=:team or team2=:team order by date desc limit 4",
            nativeQuery = true)
    List<Match> getlast4teammatch(@Param("team") String team);

    @Query(value = "select count(*) from matchinfo where winning_team=:team",
            nativeQuery = true)
    int getwins(@Param("team") String team);

    @Query(value = "select count(*) from matchinfo where team1=:team or team2=:team",
            nativeQuery = true)
    int getallmatches(@Param("team") String team);

    @Query(value = "SELECT toss_winner,COUNT(toss_winner)  from matchinfo group by toss_winner order by count(toss_winner) desc",
            nativeQuery = true)
    List<Object[]> gettossdecisions();

//    List<Match> findByTeam1(String team);
}
