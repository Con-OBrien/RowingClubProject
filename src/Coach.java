import java.io.Serializable;

public class Coach implements Serializable {
    private String coachFirstName;
    private String coachLastName;
    private String coachPhone;

    public Coach(String coachFirstName, String coachLastName, String coachPhone)
    {
        setCoachFirstName(coachFirstName);
        setCoachLastName(coachLastName);
        setCoachPhone(coachPhone);
    }


    public String getCoachFirstName() { return coachFirstName; }
    public String getCoachLastName() { return coachLastName; }
    public String getCoachPhone() { return coachPhone; }


    public void setCoachFirstName(String coachFirstName) { this.coachFirstName = coachFirstName; }
    public void setCoachLastName(String coachLastName) { this.coachLastName = coachLastName; }
    public void setCoachPhone(String coachPhone) { this.coachPhone = coachPhone; }

    public String toString() { return "\nCoach First Name: " + getCoachFirstName() + "\nCoach Last Name: " + getCoachLastName() + "\nCoach Phone Number: " +
                                            getCoachPhone(); }
}
