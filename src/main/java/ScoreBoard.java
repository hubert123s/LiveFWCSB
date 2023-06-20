import java.util.*;

public class ScoreBoard {
    private Map<String, Match> matches;

    public ScoreBoard() {
        this.matches = new LinkedHashMap<>();
    }

    public void startNewMatch(String homeTeamName, String awayTeamName) {
        Team homeTeam = new Team(homeTeamName, 0);
        Team awayTeam = new Team(awayTeamName, 0);
        Match match = new Match(homeTeam, awayTeam);
        matches.put(match.getId(), match);
    }

    public void updateScore(String matchId, int homeScore, int awayScore) {
        Match match = matches.get(matchId);
        if (match != null) {
            match.updateScore(homeScore, awayScore);
        }
    }

    public List<Match> getMatchesOrderedByTotalScore() {
        List<Match> matchesInProgress = new ArrayList<>(matches.values());
        matchesInProgress.sort(Comparator.comparingInt(Match::getTotalScore).reversed().thenComparing(Match::getStartTime));
        return matchesInProgress;
    }
}
