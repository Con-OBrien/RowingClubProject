import java.io.Serializable;

public class Coach implements Serializable {
    private String coachFirstName;
    private String coachLastName;
    private String coachPhone;
    private String coachStatus;

    public Coach(String coachFirstName, String coachLastName, String coachPhone, String coachStatus)
    {
        setCoachFirstName(coachFirstName);
        setCoachLastName(coachLastName);
        setCoachPhone(coachPhone);
        setCoachStatus(coachStatus);
    }


    public String getCoachFirstName() { return coachFirstName; }
    public String getCoachLastName() { return coachLastName; }
    public String getCoachPhone() { return coachPhone; }
    public String getCoachStatus() { return coachStatus; }


    public void setCoachFirstName(String coachFirstName) { this.coachFirstName = coachFirstName; }
    public void setCoachLastName(String coachLastName) { this.coachLastName = coachLastName; }
    public void setCoachPhone(String coachPhone) { this.coachPhone = coachPhone; }
    public void setCoachStatus(String coachStatus) { this.coachStatus = coachStatus; }


    public String toString() { return "\nCoach First Name: " + getCoachFirstName() +
            "\nCoach Last Name: " + getCoachLastName() + "\nCoach Phone Number: " + getCoachPhone() + "\nStatus: " + getCoachStatus(); }
}
