package com.example.ipldashboard.repository;

import com.example.ipldashboard.models.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {
    @Query(value = "Select * from team_info where team_name=:team",
            nativeQuery = true)
    public Team getTeamDetails(@Param("team") String team);
}
