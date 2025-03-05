package com.example.nbasimulator.services;

import com.example.nbasimulator.models.Team;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class TeamService {

    public List<Team> getAllTeams() {
        // Hardcoded example - eventually replace with data from SetupUtils
        List<Team> teams = new ArrayList<>();
        teams.add(new Team("Lakers", "West", "Pacific", "JJ Redick"));
        teams.add(new Team("Bucks", "East", "Central", "Doc Rivers"));
        return teams;
    }
}
