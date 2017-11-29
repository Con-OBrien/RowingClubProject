public class Athlete extends Member {
    private Object awards;

    public Athlete(String fname,String sname,String gender,String email,String phone,int age,int height,String dateregistered, boolean paid, Object experience, String status,String coachFirstName, String coachLastName, String coachPhone, String awards) {
        super(fname, sname, gender, email, phone, age, height, dateregistered, paid, experience, status, coachFirstName, coachLastName, coachPhone);
        this.awards=awards;
    }

    public Object getAwards() { return awards; }

    public void setAwards(Object awards) { this.awards = awards; }


    public String toString() { return super.toString()+"\nAward: "+ getAwards(); }
}
