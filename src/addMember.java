import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addMember {
    JTextField poundsField;
    JTextField kilosField;

    public addMember()
    {
        JFrame main = new JFrame("Add Member ");
        main.setSize(200,200);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        main.setLocation(screenSize.width/2-main.getSize().width/2, screenSize.height/2-main.getSize().height/2);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] experiencelvls = { "Novice", "Recreational", "Competitive"};

        JTextField fname = new JTextField(10);
        JTextField sname = new JTextField(10);
        JTextField gender = new JTextField(10);
        JTextField email = new JTextField(10);
        JTextField phone = new JTextField(10);
        JTextField age = new JTextField(10);
        JTextField height = new JTextField(10);
        JComboBox experience = new JComboBox(experiencelvls);

       // main.setLayout(new FlowLayout());
        main.setLayout(new GridLayout(0,1));
        JPanel pane = new JPanel(new GridLayout(9,1));
        main.add(pane);
        pane.add(new JLabel("Forename: "));
        pane.add(fname);
        pane.add(new JLabel("Surname: "));
        pane.add(sname);
        pane.add(new JLabel("Gender: "));
        pane.add(gender);
        pane.add(new JLabel("Email: "));
        pane.add(email);
        pane.add(new JLabel("Phone: "));
        pane.add(phone);
        pane.add(new JLabel("Age: "));
        pane.add(age);
        pane.add(new JLabel("Height: "));
        pane.add(height);
        pane.add(new JLabel("Experience: "));
        pane.add(experience);

        JButton submit = new JButton("Save");
        pane.add(submit);
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
