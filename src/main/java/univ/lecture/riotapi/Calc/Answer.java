package univ.lecture.riotapi.Calc;

/**
 * Created by fhzot on 2017-04-06.
 */
public class Answer {
    private int teamId;
    private long now;
    private double result;

    public Answer(int teamId, long now, double result) {
        this.teamId = teamId;
        this.now = now;
        this.result = result;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
