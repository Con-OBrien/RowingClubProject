public class Athlete extends Member {
    private Object awards;

    public Athlete(String fname,String sname,String gender,String email,String phone,int age,int height,String dateregistered, String paidstatus, Object experience, String status,String coachFirstName, String coachLastName, String coachPhone, String coachStatus, String awards) {
        super(fname, sname, gender, email, phone, age, height, dateregistered, paidstatus, experience, status, coachFirstName, coachLastName, coachPhone, coachStatus);
        this.awards=awards;
    }

    public Object getAwards() { return awards; }

    public void setAwards(Object awards) { this.awards = awards; }


    public String toString() { return super.toString()+"\nAward: "+ getAwards(); }
}
