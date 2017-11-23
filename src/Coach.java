import java.io.Serializable;

public class Coach implements Serializable {
    private int coachNum;
    private String coachName;

    public Coach(String coachName,int coachNum)
    {
        setCoachName(coachName);
        setCoachNum(coachNum);
    }

    public int getCoachNum() { return coachNum; }
    public void setCoachNum(int coachNum) { this.coachNum = coachNum; }
    public String getCoachName() { return coachName; }
    public void setCoachName(String coachName) { this.coachName = coachName; }

    public String toString() { return "\nCoach Name: " + getCoachName() + "\nCoach Number: " + getCoachNum(); }
}
