package pl.sdacademy.tournament.group;

import pl.sdacademy.tournament.match.Match;
import pl.sdacademy.tournament.team.Team;

import java.util.List;

public class GroupTeamStatistics implements Comparable<GroupTeamStatistics> {
    public final static String GROUP_TEAM_STATS_HEADER = "nazwa                | pkt | g.r | g.z | g.s\n" +
        "----------------------------------------------\n";
    private Team team;
    private int points;
    private int goalsScored;
    private int goalsLost;

    public GroupTeamStatistics(Team team, List<Match> matches) {
        this.team = team;
        matches.forEach(this::appendStatsFrom);
    }

    public void appendStatsFrom(Match match) {
        int team1Goals = match.getTeam1Goals();
        int team2Goals = match.getTeam2Goals();

        int teamGoals, opponentGoals;
        if (match.getTeam1() == team) {
            teamGoals = team1Goals;
            opponentGoals = team2Goals;
        } else {
            teamGoals = team2Goals;
            opponentGoals = team1Goals;
        }

        goalsScored += teamGoals;
        goalsLost += opponentGoals;

        if (teamGoals > opponentGoals) {
            points += 3;
        } else if (teamGoals == opponentGoals) {
            points += 1;
        }
    }

    private int getGoalsDifference() {
        return goalsScored - goalsLost;
    }

    private String addLeftPadding(String text, int length) {
        return String.format("%" + length + "." + length + "s", text);
    }

    private String addRightPadding(String text, int length) {
        return String.format("%-" + length + "." + length + "s", text);
    }

    private String addLeftPadding(int value, int length) {
        return addLeftPadding(Integer.toString(value), length);
    }

    @Override
    public int compareTo(GroupTeamStatistics groupTeamStatistics) {
        if (this.points != groupTeamStatistics.points) {
            return this.points - groupTeamStatistics.points;
        }
        if (this.getGoalsDifference() != groupTeamStatistics.getGoalsDifference()) {
            return this.getGoalsDifference() - groupTeamStatistics.getGoalsDifference();
        }
        return this.goalsScored - groupTeamStatistics.goalsScored;
    }

    @Override
    public String toString() {
        return addRightPadding(team.getName(), 20) + " | " +
                addLeftPadding(points, 3) + " | " +
                addLeftPadding(goalsScored - goalsLost, 3) + " | " +
                addLeftPadding(goalsScored, 3) + " | " +
                addLeftPadding(goalsLost, 3) + "\n";
    }
}
