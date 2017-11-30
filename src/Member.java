import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;

public abstract class Member implements Serializable {
    private String fname;
    private String sname;
    private String gender;
    private String email;
    private String phone;
    private int age;
    private int height;
    private String dateregistered;
    private String paidstring;
    private Object experience;
    private String status;
    private Coach coach;
    private static int numObjects;


   // public Member(){ this("Unknown","Unknown","Unknown","Unknown","Unknown",0,0,"Unknown","Unknown"); }

    public Member(String fname,String sname,String gender,String email,String phone,int age,int height,String dateregistered, String paidstring, Object experience, String status, String coachFirstName, String coachLastName, String coachPhone, String coachStatus)
    {
        setFname(fname);
        setSname(sname);
        setGender(gender);
        setEmail(email);
        setPhone(phone);
        setAge(age);
        setHeight(height);
        setDateregistered(dateregistered);
        setPaidString(paidstring);
        setExperience(experience);
        setStatus(status);
        setCoach(new Coach(coachFirstName, coachLastName, coachPhone, coachStatus));
        numObjects++;
        }

    public String getFname() { return fname; }
    public String getSname() { return sname; }
    public String getGender() { return gender; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public int getAge() { return age; }
    public int getHeight() { return height; }
    public String getDateregistered() { return dateregistered; }
    public String getStatus() { return status; }
    public String getPaidstring() { return paidstring; }
    public Object getExperience() { return experience; }


    public void setFname(String fname) { this.fname = fname; }
    public void setSname(String sname) { this.sname = sname; }
    public void setGender(String gender) { this.gender = gender; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAge(int age) { this.age = age; }
    public void setHeight(int height) { this.height = height; }
    public void setDateregistered(String dateregistered) { this.dateregistered = dateregistered; }
    public void setStatus(String status) { this.status = status; }
    public void setPaidString(String paidstring) { this.paidstring = paidstring; }
    public void setExperience(Object experience) { this.experience = experience; }
    public Coach getCoach() { return coach; }
    public void setCoach(Coach coach) { this.coach = coach; }


    public static void setNumObjects(int numObjects) {
        Member.numObjects = numObjects;
    }
    public static int getNumObjects() { return numObjects; }

    public String toString() {

        return "First name: " + fname + "\nLast name: " + sname + "\nGender: " + gender + "\nEmail: " + email + "\nPhone: " + phone + "\nAge: " + age + "\nHeight: " + height +
                "\nDate registered: " + dateregistered + "\nHave they paid? " + paidstring + "\nExperience: " + experience + "\nStatus: " + status + coach.toString();
    }
}
