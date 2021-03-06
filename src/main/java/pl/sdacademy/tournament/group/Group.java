package pl.sdacademy.tournament.group;

import pl.sdacademy.tournament.match.Match;
import pl.sdacademy.tournament.team.Team;

import java.util.*;
import java.util.stream.Collectors;

public class Group {
    private String name;
    private List<Team> teams;
    private Map<Integer, List<Match>> matchesByRound;

    public Group(String name, List<Team> teams) {
        this.name = name;
        if (teams.size() != 4) {
            throw new RuntimeException("Nieprawidłowa liczba drużyn");
        }
        this.teams = teams;
        this.matchesByRound = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setMatchesForRound(int round, List<Match> matches) {
        matchesByRound.put(round, matches);
    }

    private int getCurrentRound() {
        int currentRound = - 1;
        for (int i = 0; i <= 2; i++) {
            if (matchesByRound.containsKey(i)) {
                currentRound = i;
                if (matchesByRound.get(i).size() > 1){
                    currentRound += 1;
                }
            }
        }
        return currentRound;
    }


    public void addMatch(Match match) {
        int currentRound = getCurrentRound();
        List<Match> roundMatches;
        if (matchesByRound.containsKey(currentRound)) {
            roundMatches = matchesByRound.get(currentRound);
        } else {
            if (currentRound < 0) {
                currentRound = 0;
            }
            roundMatches = new ArrayList<>();

        }
        roundMatches.add(match);
        matchesByRound.put(currentRound, roundMatches);
    }

    public List<Match> getMatches(int round) {
        return matchesByRound.get(round);
    }

    public List<Match> getAllMatches() {
        return matchesByRound.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        List<GroupTeamStatistics> groupTeamsStats = teams.stream()
                .map(team -> new GroupTeamStatistics(team, getMatchesForTeam(team)))
                .sorted()
                .collect(Collectors.toList());
        Collections.reverse(groupTeamsStats);
        StringBuilder stringBuilder = new StringBuilder(GroupTeamStatistics.GROUP_TEAM_STATS_HEADER);
        groupTeamsStats.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    public List<Match> getMatchesForTeam(Team team) {
        return getAllMatches().stream()
                .filter(m -> m.getTeam1() == team
                        || m.getTeam2() == team)
                .collect(Collectors.toList());
    }


}
