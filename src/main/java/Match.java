import java.util.Date;

public class Match {
    private static int incrementId = 1;

    private String id;
    private Team homeTeam;
    private Team awayTeam;
    private Date startTime;
    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }



    public Match(Team homeTeam, Team awayTeam) {
        this.id = Integer.toString(incrementId++);
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.startTime = new Date();
    }

    public String getId() {
        return id;
    }

    public void updateScore(int homeScore, int awayScore) {
        homeTeam.setScore(homeScore);
        awayTeam.setScore(awayScore);
    }

    public int getTotalScore() {
        return homeTeam.getScore() + awayTeam.getScore();
    }

    public Date getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return homeTeam.getName() + " " + homeTeam.getScore() + " - " + awayTeam.getName() + " " + awayTeam.getScore();
    }
}
