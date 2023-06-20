import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ScoreBoardTest {
    private ScoreBoard scoreBoard;

    @Before
    public void setUp() {
        scoreBoard = new ScoreBoard();
    }

    @Test
    public void shouldStartNewMatch() {
        Team Mexico = new Team("Mexico", 0);
        Team Canada = new Team("Canada", 0);

        scoreBoard.startNewMatch(Mexico.getName(), Canada.getName());

        List<Match> matches = scoreBoard.getMatchesOrderedByTotalScore();
        assertEquals(1, matches.size());

        Match match = matches.get(0);
        assertEquals(Mexico.getName(), match.getHomeTeam().getName());
        assertEquals(Canada.getName(), match.getAwayTeam().getName());
    }

    @Test
    public void shouldUpdateScore() {
        Team Mexico = new Team("Mexico", 0);
        Team Canada = new Team("Canada", 0);

        scoreBoard.startNewMatch(Mexico.getName(), Canada.getName());

        List<Match> matches = scoreBoard.getMatchesOrderedByTotalScore();
        assertEquals(1, matches.size());

        Match match = matches.get(0);
        String matchId = match.getId();

        int homeScore = 0;
        int awayScore = 5;
        scoreBoard.updateScore(matchId, homeScore, awayScore);

        assertEquals(homeScore, match.getHomeTeam().getScore());
        assertEquals(awayScore, match.getAwayTeam().getScore());
    }
    @Test
    public void shouldGetMatchesInProgressOrderedByTotalScore() {
        Team Mexico = new Team("Mexico", 0);
        Team Canada = new Team("Canada", 0);

        scoreBoard.startNewMatch(Mexico.getName(), Canada.getName());

        Team Spain = new Team("Spain", 0);
        Team Brazil = new Team("Brazil", 0);
        scoreBoard.startNewMatch(Spain.getName(), Brazil.getName());

        List<Match> matchesInProgress = scoreBoard.getMatchesOrderedByTotalScore();
        assertEquals(2, matchesInProgress.size());
        assertEquals(0, matchesInProgress.get(0).getTotalScore());
        assertEquals(0, matchesInProgress.get(1).getTotalScore());

        int homeScore1 = 0;
        int awayScore1 = 2;
        scoreBoard.updateScore(matchesInProgress.get(0).getId(), homeScore1, awayScore1);

        int homeScore2 = 10;
        int awayScore2 = 2;
        scoreBoard.updateScore(matchesInProgress.get(1).getId(), homeScore2, awayScore2);

        List<Match> updatedMatchesInProgress = scoreBoard.getMatchesOrderedByTotalScore();
        assertEquals(2, updatedMatchesInProgress.size());
        assertEquals(homeScore1 + awayScore1, updatedMatchesInProgress.get(1).getTotalScore());
        assertEquals(homeScore2 + awayScore2, updatedMatchesInProgress.get(0).getTotalScore());
    }

    @Test
    public void shouldFinishMatch() {
        Team Mexico = new Team("Mexico", 0);
        Team Canada = new Team("Canada", 0);

        scoreBoard.startNewMatch(Mexico.getName(), Canada.getName());

        List<Match> matches = scoreBoard.getMatchesOrderedByTotalScore();
        assertEquals(1, matches.size());

        Match match = matches.get(0);
        String matchId = match.getId();

        scoreBoard.finishMatch(matchId);

        matches = scoreBoard.getMatchesOrderedByTotalScore();
        assertEquals(0, matches.size());
    }
}
