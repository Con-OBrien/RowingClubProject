import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCoach extends JFrame implements ActionListener {

    private JTextField coachfirstnameBox, coachlastnameBox, coachphoneBox;

    public AddCoach() {

        setTitle("Rowing Club");
        setSize(600,600);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width/2-getSize().width/2, screenSize.height/2-getSize().height/2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        coachfirstnameBox = new JTextField(10);
        coachlastnameBox = new JTextField(10);
        coachphoneBox = new JTextField(10);

        setLayout(new GridLayout(0,1));
        JPanel pane = new JPanel(new GridLayout(4,1));
        add(pane);

        pane.add(new JLabel("Coach First Name: "));
        pane.add(coachfirstnameBox);
        pane.add(new JLabel("Coach Last Name: "));
        pane.add(coachlastnameBox);
        pane.add(new JLabel("Coach Phone No: "));
        pane.add(coachphoneBox);

        JButton submit = new JButton("Add");
        submit.addActionListener(this);
        pane.add(submit);


        pack();
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {

        if(coachfirstnameBox.getText().equals("") || coachlastnameBox.getText().equals("") || coachphoneBox.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Field is empty, make sure all fields are entered." );
        }
        else {
            String coachfirstname = coachfirstnameBox.getText();
            String coachlastname = coachlastnameBox.getText();
            String coachphone = coachphoneBox.getText();

            Coach coach = new Coach(coachfirstname, coachlastname, coachphone);
            RowingGUI.coaches.add(coach);

            setVisible(false);

            JOptionPane.showMessageDialog(null, "Coach has been added: \n\n" + coach.toString());
        }

    }
}
