import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class AddMember extends JFrame implements ActionListener {

   private JTextField fnameBox, snameBox, genderBox, emailBox, phoneBox, ageBox, heightBox;
   private JComboBox experienceBox;
   private JCheckBox paidBox;
   private Standard standard;
   private Athlete athlete;
   private String[] awardsArray = {"100m", "500m", "Endeavour", "Team"};
   private String awards = "";


    public AddMember()
    {
        setTitle("Rowing Club");
        setSize(600,600);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width/2-getSize().width/2, screenSize.height/2-getSize().height/2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String[] experiencelvls = { "Novice", "Recreational", "Competitive"};

        fnameBox = new JTextField(15);
        snameBox = new JTextField(15);
        genderBox = new JTextField(15);
        emailBox = new JTextField(15);
        phoneBox = new JTextField(15);
        ageBox = new JTextField(15);
        heightBox = new JTextField(15);
        experienceBox = new JComboBox(experiencelvls);
        paidBox = new JCheckBox();


       // main.setLayout(new FlowLayout());
        setLayout(new GridLayout(0,1));
        JPanel pane = new JPanel(new GridLayout(10,1));
        add(pane);
        pane.add(new JLabel("Forename: "));
        pane.add(fnameBox);
        pane.add(new JLabel("Surname: "));
        pane.add(snameBox);
        pane.add(new JLabel("Gender: "));
        pane.add(genderBox);
        pane.add(new JLabel("Email: "));
        pane.add(emailBox);
        pane.add(new JLabel("Phone: "));
        pane.add(phoneBox);
        pane.add(new JLabel("Age: "));
        pane.add(ageBox);
        pane.add(new JLabel("Height: "));
        pane.add(heightBox);
        pane.add(new JLabel("Experience: "));
        pane.add(experienceBox);
        pane.add(new JLabel("Paid Fee: "));
        pane.add(paidBox);
        JButton submit = new JButton("Add");
        submit.addActionListener(this);
        pane.add(submit);


        pack();
        setVisible(true);



    }

    public void actionPerformed(ActionEvent e)
        {

            if((fnameBox.getText().equals("") || snameBox.getText().equals("")) || snameBox.getText().equals("") || snameBox.getText().equals("") || snameBox.getText().equals("")) {
                JOptionPane.showMessageDialog(null,"Field is empty, make sure all fields are entered." );
            }
           else {
                Object experience = experienceBox.getSelectedItem();

                if(experience.equals("Competitive")) {
                   addAthlete();
                }
                else {
                    addStandard();
                }
            }

        }
        private Standard addStandard() {


            try {
                String fname = fnameBox.getText();
                String sname = snameBox.getText();
                String gender = genderBox.getText();
                String email = emailBox.getText();
                String phone = phoneBox.getText();
                String ageAsString = ageBox.getText();
                int age = Integer.parseInt(ageAsString);
                String heightAsString = phoneBox.getText();
                int height = Integer.parseInt(heightAsString);
                Object experience = experienceBox.getSelectedItem();
                boolean paid = paidBox.isSelected();


                LocalDate date = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
                String dateregistered = date.format(formatter);


                String coachfirstname = "", coachlastname = "", coachphone = "";

                String status = "Active";

                if (age < 18 && experience.equals("Novice")) {
                  coachfirstname = RowingGUI.coaches.get(0).getCoachFirstName();
                  coachlastname = RowingGUI.coaches.get(0).getCoachLastName();
                  coachphone = RowingGUI.coaches.get(0).getCoachPhone();
                } else if (age < 18 && experience.equals("Recreational")) {
                    coachfirstname = RowingGUI.coaches.get(1).getCoachFirstName();
                    coachlastname = RowingGUI.coaches.get(1).getCoachLastName();
                    coachphone = RowingGUI.coaches.get(1).getCoachPhone();
                } else if (age < 18 && experience.equals("Competitive")) {
                    coachfirstname = RowingGUI.coaches.get(2).getCoachFirstName();
                    coachlastname = RowingGUI.coaches.get(2).getCoachLastName();
                    coachphone = RowingGUI.coaches.get(2).getCoachPhone();
                } else if (age >= 18 && experience.equals("Novice")) {
                    coachfirstname = RowingGUI.coaches.get(3).getCoachFirstName();
                    coachlastname = RowingGUI.coaches.get(3).getCoachLastName();
                    coachphone = RowingGUI.coaches.get(3).getCoachPhone();
                } else if (age >= 18 && experience.equals("Recreational")) {
                    coachfirstname = RowingGUI.coaches.get(4).getCoachFirstName();
                    coachlastname = RowingGUI.coaches.get(4).getCoachLastName();
                    coachphone = RowingGUI.coaches.get(4).getCoachPhone();
                } else if (age >= 18 && experience.equals("Competitive")) {
                    coachfirstname = RowingGUI.coaches.get(5).getCoachFirstName();
                    coachlastname = RowingGUI.coaches.get(5).getCoachLastName();
                    coachphone = RowingGUI.coaches.get(5).getCoachPhone();
                }
                RowingGUI.count++;
                setVisible(false);

                standard = new Standard(fname, sname, gender, email, phone, age, height, dateregistered, paid, experience, status, coachfirstname, coachlastname, coachphone);
                RowingGUI.members.add(standard);

                JOptionPane.showMessageDialog(null, "Member has been inserted: Make sure to save!\n\n" + standard.toString());

            } catch (NullPointerException n) {
                // n.printStackTrace();
            } catch (IndexOutOfBoundsException i) {
                JOptionPane.showMessageDialog(null,"Insufficient coaches on the system!");
            }
            return standard;
        }
    private Athlete addAthlete() {


        try {
            String fname = fnameBox.getText();
            String sname = snameBox.getText();
            String gender = genderBox.getText();
            String email = emailBox.getText();
            String phone = phoneBox.getText();
            String ageAsString = ageBox.getText();
            int age = Integer.parseInt(ageAsString);
            String heightAsString = phoneBox.getText();
            int height = Integer.parseInt(heightAsString);
            Object experience = experienceBox.getSelectedItem();
            boolean paid = paidBox.isSelected();


            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
            String dateregistered = date.format(formatter);


            String coachfirstname = "", coachlastname = "", coachphone = "";
            String status = "Active";

            if (age < 18 && experience.equals("Novice")) {
                coachfirstname = RowingGUI.coaches.get(0).getCoachFirstName();
                coachlastname = RowingGUI.coaches.get(0).getCoachLastName();
                coachphone = RowingGUI.coaches.get(0).getCoachPhone();
            } else if (age < 18 && experience.equals("Recreational")) {
                coachfirstname = RowingGUI.coaches.get(1).getCoachFirstName();
                coachlastname = RowingGUI.coaches.get(1).getCoachLastName();
                coachphone = RowingGUI.coaches.get(1).getCoachPhone();
            } else if (age < 18 && experience.equals("Competitive")) {
                coachfirstname = RowingGUI.coaches.get(2).getCoachFirstName();
                coachlastname = RowingGUI.coaches.get(2).getCoachLastName();
                coachphone = RowingGUI.coaches.get(2).getCoachPhone();
            } else if (age >= 18 && experience.equals("Novice")) {
                coachfirstname = RowingGUI.coaches.get(3).getCoachFirstName();
                coachlastname = RowingGUI.coaches.get(3).getCoachLastName();
                coachphone = RowingGUI.coaches.get(3).getCoachPhone();
            } else if (age >= 18 && experience.equals("Recreational")) {
                coachfirstname = RowingGUI.coaches.get(4).getCoachFirstName();
                coachlastname = RowingGUI.coaches.get(4).getCoachLastName();
                coachphone = RowingGUI.coaches.get(4).getCoachPhone();
            } else if (age >= 18 && experience.equals("Competitive")) {
                coachfirstname = RowingGUI.coaches.get(5).getCoachFirstName();
                coachlastname = RowingGUI.coaches.get(5).getCoachLastName();
                coachphone = RowingGUI.coaches.get(5).getCoachPhone();
            }

            String input;

            input = (String) JOptionPane.showInputDialog(null, "Which awards would you like to go for?",
                    "Rowing Club", JOptionPane.QUESTION_MESSAGE, null, awardsArray, awardsArray[0]);

            awards = input;

            athlete = new Athlete(fname, sname, gender, email, phone, age, height, dateregistered, paid, experience, status, coachfirstname, coachlastname, coachphone, awards);
            RowingGUI.members.add(athlete);

            JOptionPane.showMessageDialog(null, "Member has been inserted: Make sure to save!\n\n" + athlete.toString());

            RowingGUI.count++;
            setVisible(false);

        } catch (NullPointerException n) {
            // n.printStackTrace();
        } catch (IndexOutOfBoundsException i) {
            JOptionPane.showMessageDialog(null,"Insufficient coaches on the system!");
        }

        return athlete;
    }
}


