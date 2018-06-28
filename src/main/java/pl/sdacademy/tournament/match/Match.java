package pl.sdacademy.tournament.match;

import pl.sdacademy.tournament.team.Person;
import pl.sdacademy.tournament.team.Team;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Match {
    private Team team1;
    private Team team2;
    private List<MatchEvent> events;

    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        randomizeEvents();
    }

    public Match(Team team1, Team team2, List<MatchEvent> events){
        this.team1 = team1;
        this.team2 = team2;
        this.events = events;
    }


    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public List<MatchEvent> getEvents() {
        return events;
    }

    public int getTeam1Goals() {
        return getTeamGoals(team1);
    }

    public int getTeam2Goals() {
        return getTeamGoals(team2);
    }

    private int getTeamGoals(Team team) {
        return (int) events.stream()
                .filter(e -> team.getPlayers().contains(e.getPlayer())
                        && e.getType() == MatchEventType.GOAL)
                .count();
    }


    public void randomizeEvents() {
        events = new ArrayList<>();
        Random random = new Random();
        MatchEventType[] eventTypes = MatchEventType.values();
        for (int i = 0; i < 93; i++) {
            if(random.nextInt(30) == 0) {
                int minute = i > 90 ? 90 : i;
                MatchEventType eventType = eventTypes[random.nextInt(eventTypes.length)];
                Person player;
                if (random.nextBoolean()) {
                    player = getRandomPlayer(team1);
                } else {
                    player = getRandomPlayer(team2);
                }
                events.add(new MatchEvent(eventType, player, minute));
            }
        }
    }

    private Person getRandomPlayer(Team team) {
        Random random = new Random();
        List<Person> players = team.getPlayers();
        return players.get(random.nextInt(players.size()));
    }

    @Override
    public String toString() {
        return team1.getName() + " " + getTeam1Goals() + ":" + getTeam2Goals() + " " + team2.getName();
    }
}
