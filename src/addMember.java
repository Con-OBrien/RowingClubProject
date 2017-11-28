import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class addMember extends JFrame implements ActionListener {
    JTextField fnameBox;
    JTextField snameBox;
    JTextField genderBox;
    JTextField emailBox;
    JTextField phoneBox;
    JTextField ageBox;
    JTextField heightBox;
    JComboBox experienceBox;
    JCheckBox paidBox;
    JButton submit;
    Standard standard;



    public addMember()
    {
        setTitle("Rowing Club");
        setSize(600,600);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width/2-getSize().width/2, screenSize.height/2-getSize().height/2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String[] experiencelvls = { "Novice", "Recreational", "Competitive"};

        fnameBox = new JTextField(10);
        snameBox = new JTextField(10);
        genderBox = new JTextField(10);
        emailBox = new JTextField(10);
        phoneBox = new JTextField(10);
        ageBox = new JTextField(10);
        heightBox = new JTextField(10);
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
        submit = new JButton("Add");
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
               add();
            }

        }
        private Standard add() {


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


                String coachname = "";
                int coachnum = 0;
                String status = "Active";

    if (age < 18 && experience.equals("Novice")) {
        coachname = "Mark O'Donnell";
        coachnum = 1;
    } else if (age < 18 && experience.equals("Recreational")) {
        coachname = "Hugh Morris";
        coachnum = 2;
    } else if (age < 18 && experience.equals("Competitive")) {
        coachname = "Leon deJon";
        coachnum = 3;
    } else if (age >= 18 && experience.equals("Novice")) {
        coachname = "Brian Simmons";
        coachnum = 4;
    } else if (age >= 18 && experience.equals("Recreational")) {
        coachname = "Mike FitzGerald";
        coachnum = 5;
    } else if (age >= 18 && experience.equals("Competitive")) {
        coachname = "Cathal O'Donnelly";
        coachnum = 5;
    }



                standard = new Standard(fname, sname, gender, email, phone, age, height, dateregistered, paid, experience, status, coachname, coachnum);
                RowingGUI.members.add(standard);

                RowingGUI.count++;
                JOptionPane.showMessageDialog(null,"Member has been inserted: Make sure to save!\n\n" + standard.toString());

                setVisible(false);
            }
            catch (NullPointerException n) {
               // n.printStackTrace();
            }
         return standard;
        }
}


