package pl.sdacademy.tournament;

import pl.sdacademy.tournament.match.Match;

public interface TournamentPhase {
    void generateNextRoundMatches();

    void addData(Match match);
}
