import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class addMember {
    JTextField poundsField;
    JTextField kilosField;

    public addMember()
    {
        JFrame main = new JFrame("Add Member ");
        main.setSize(600,600);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        main.setLocation(screenSize.width/2-main.getSize().width/2, screenSize.height/2-main.getSize().height/2);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] experiencelvls = { "Novice", "Recreational", "Competitive"};

        JTextField fnameBox = new JTextField(10);
        JTextField snameBox = new JTextField(10);
        JTextField genderBox = new JTextField(10);
        JTextField emailBox = new JTextField(10);
        JTextField phoneBox = new JTextField(10);
        JTextField ageBox = new JTextField(10);
        JTextField heightBox = new JTextField(10);
        JComboBox experienceBox = new JComboBox(experiencelvls);
        JCheckBox paidBox = new JCheckBox();

       // main.setLayout(new FlowLayout());
        main.setLayout(new GridLayout(0,1));
        JPanel pane = new JPanel(new GridLayout(10,1));
        main.add(pane);
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
        JButton submit = new JButton("Save");
        pane.add(submit);

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
        String experience = experienceBox.getName();




        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
        String dateregistered = date.format(formatter);



        String coachname;
        int coachnum;
        String status = "Active";

        if (age < 18) {
            coachname = "Mark O'Donnell";
            coachnum = 1;
        } else if (age > 18 && age <= 35) {
            coachname = "Peter Andrews";
            coachnum = 2;
        } else {
            coachname = "David Edgar";
            coachnum = 3;
        }
    }
    catch (NumberFormatException n) {
        System.out.print("Number format exception");
    }
      //  Standard standard = new Standard(fname, sname, gender, email, phone, age, height, dateregistered, paid, status, coachname, coachnum);

        main.pack();
        main.setVisible(true);



    }



    public static void main(String args[])
    {
        addMember guiApp = new addMember();
    }


    private class ButtonEventHandler implements ActionListener{

        public void actionPerformed(ActionEvent e)
        {
            String poundsAsString,kilosAsString;
            double kilos,pounds;

            if((poundsField.getText().equals("") && kilosField.getText().equals(""))
                    || (!poundsField.getText().equals("") && !kilosField.getText().equals("")))
            {
                JOptionPane.showMessageDialog(null,"You must enter a value into one " +
                                "field and one field only","Error",
                        JOptionPane.ERROR_MESSAGE);

                poundsField.setText("");
                kilosField.setText("");
            }
            else
            {
                if(poundsField.getText().equals(""))
                {
                    kilosAsString = kilosField.getText();

                    kilos = Double.parseDouble(kilosAsString);

                    pounds = kilos * 2.2;

                    poundsField.setText("" + String.format("%.4f",pounds));
                }
                else
                {
                    poundsAsString = poundsField.getText();

                    pounds = Double.parseDouble(poundsAsString);

                    kilos = pounds / 2.2;

                    kilosField.setText("" + String.format("%.4f",kilos));
                }

            }

        }
    }
}
