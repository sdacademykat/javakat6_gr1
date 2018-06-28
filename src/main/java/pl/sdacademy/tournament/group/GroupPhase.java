package pl.sdacademy.tournament.group;

import pl.sdacademy.tournament.match.Match;
import pl.sdacademy.tournament.team.Team;
import pl.sdacademy.tournament.TournamentPhase;

import java.util.Arrays;
import java.util.List;

public class GroupPhase implements TournamentPhase {
    private List<Group> groups;
    private boolean finished;
    private int currentRound;

    public GroupPhase(List<Group> groups) {
        this.groups = groups;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void generateNextRoundMatches() {
        currentRound++;
        groups.forEach(this::generateNextRoundMatches);
    }

    private void generateNextRoundMatches(Group group) {
        Team team1;
        Team team2;
        Team team3;
        Team team4;
        List<Team> teams = group.getTeams();
        if (currentRound == 1) {
            team1 = teams.get(0);
            team2 = teams.get(1);
            team3 = teams.get(2);
            team4 = teams.get(3);
        } else if (currentRound == 2) {
            team1 = teams.get(0);
            team2 = teams.get(2);
            team3 = teams.get(1);
            team4 = teams.get(3);
        } else if (currentRound == 3) {
            team1 = teams.get(0);
            team2 = teams.get(3);
            team3 = teams.get(1);
            team4 = teams.get(2);
            finished = true;
        } else {
            throw new RuntimeException("Rozegrano wszystkie mecze");
        }
        List<Match> matches = Arrays.asList(
                new Match(team1, team2),
                new Match(team3, team4)
        );
        group.setMatchesForRound(currentRound, matches);
    }

}
