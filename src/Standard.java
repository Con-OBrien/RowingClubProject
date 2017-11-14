public class Standard extends Member {

    public Standard(){
        this("","","","","",0,0,"","","",0);
    }

    public Standard(String fname,String sname,String gender,String email,String phone,int age,int height,String dateregistered, String status, String coachName,int coachNum)
    {
        super(fname, sname, gender, email, phone, age, height, dateregistered, status, coachName, coachNum);

    }
    public void setMembershipFee(double carfee) {
        fee += 150;
    }
    public double calcMembershipFee() { return fee; }
}
