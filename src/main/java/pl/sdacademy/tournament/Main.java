package pl.sdacademy.tournament;

import pl.sdacademy.tournament.group.Group;
import pl.sdacademy.tournament.group.GroupPhase;
import pl.sdacademy.tournament.match.Match;
import pl.sdacademy.tournament.match.MatchEvent;
import pl.sdacademy.tournament.match.MatchEventType;
import pl.sdacademy.tournament.team.Person;
import pl.sdacademy.tournament.team.Team;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Team poland = new Team("Polska", new Person("Adam", "Nawałka", LocalDate.of(1950, 1, 1)),
                Arrays.asList(
                        new Person("Wojciech", "Szczęsny", LocalDate.of(1990, 1, 1)),
                        new Person("Robert", "Lewandowski", LocalDate.of(1990, 1, 1)),
                        new Person("Grzegorz", "Krychowiak", LocalDate.of(1990, 1, 1)),
                        new Person("Michał", "Pazdan", LocalDate.of(1990, 1, 1))
                ));
        Team germany = new Team("Niemcy", new Person("Joachim", "Loew", LocalDate.of(1950, 1, 1)),
                Arrays.asList(
                        new Person("Thomas", "Muller", LocalDate.of(1990, 1, 1)),
                        new Person("Mesut", "Oezil", LocalDate.of(1990, 1, 1)),
                        new Person("Manuel", "Neuer", LocalDate.of(1990, 1, 1)),
                        new Person("Jerome", "Boateng", LocalDate.of(1990, 1, 1))
                ));
        Team brazil = new Team("Brazylia", new Person("Adenor Leonardo", "Bacchi", LocalDate.of(1950, 1, 1)),
                Arrays.asList(
                        new Person("", "Neymar", LocalDate.of(1990, 1, 1)),
                        new Person("", "Fernandinho", LocalDate.of(1990, 1, 1)),
                        new Person("", "Willian", LocalDate.of(1990, 1, 1)),
                        new Person("", "Fred", LocalDate.of(1990, 1, 1))
                ));
        Team argentina = new Team("Argentyna", new Person("Jorge", "Sampaoli", LocalDate.of(1950, 1, 1)),
                Arrays.asList(
                        new Person("Lionel", "Messi", LocalDate.of(1990, 1, 1)),
                        new Person("Javier", "Mascherano", LocalDate.of(1990, 1, 1)),
                        new Person("Sergio", "Aguero", LocalDate.of(1990, 1, 1)),
                        new Person("Angel", "di Maria", LocalDate.of(1990, 1, 1))
                ));

        Group group = new Group("Grupa X", Arrays.asList(
                poland, germany, brazil, argentina
        ));
        GroupPhase groupPhase = new GroupPhase(Arrays.asList(group));

        System.out.println(group);
//        while(!groupPhase.isFinished()) {
//            groupPhase.generateNextRoundMatches();
//            System.out.println(group);
//            group.getMatches(groupPhase.getCurrentRound())
//                    .forEach(System.out::println);
//            System.out.println('\n');
//        }
        Match match = new Match(poland, brazil, Arrays.asList(
                new MatchEvent(MatchEventType.GOAL, poland.getPlayers().get(0), 50)
        ));
        Match match1 = new Match(argentina, germany, Arrays.asList(
                new MatchEvent(MatchEventType.GOAL, germany.getPlayers().get(3), 19),
                new MatchEvent(MatchEventType.GOAL, argentina.getPlayers().get(0), 45)
        ));
        Match match2 = new Match(argentina, poland, Arrays.asList(
                new MatchEvent(MatchEventType.GOAL, poland.getPlayers().get(3), 19)
        ));
        Match match3 = new Match(brazil, germany, Arrays.asList(
                new MatchEvent(MatchEventType.GOAL, germany.getPlayers().get(3), 19),
                new MatchEvent(MatchEventType.GOAL, germany.getPlayers().get(0), 45)
        ));

        Match match4 = new Match(poland, germany, Arrays.asList(
                new MatchEvent(MatchEventType.GOAL, germany.getPlayers().get(3), 19),
                new MatchEvent(MatchEventType.GOAL, germany.getPlayers().get(0), 45)
        ));

        Match match5 = new Match(argentina, brazil, Arrays.asList(
                new MatchEvent(MatchEventType.GOAL, brazil.getPlayers().get(3), 19),
                new MatchEvent(MatchEventType.GOAL, argentina.getPlayers().get(0), 45)
        ));
        groupPhase.addData(match);
        groupPhase.addData(match1);
        groupPhase.addData(match2);
        groupPhase.addData(match3);
        groupPhase.addData(match4);
        groupPhase.addData(match5);
        System.out.println(group);
    }
}
