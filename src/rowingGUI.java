

import java.awt.*;
import java.awt.SplashScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.naming.Context;
import javax.swing.*;

public class rowingGUI extends JFrame implements ActionListener{

    private final JMenuItem newAction, updateAction, removeAction, queryAction, exitAction, simulateAction;
    public int count;
    //public static Member [] members;
    ArrayList<Member> members = new ArrayList<Member>();

    private rowingGUI() {



        setIconImage(new ImageIcon("C:\\Users\\Conor\\Desktop\\sailboat.png").getImage());
        setBackground(Color.darkGray);
        setTitle("Rowing Club Menu");
        setSize(500, 250);
        setLocation(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Creates a menubar for a JFrame
        JMenuBar menuBar = new JMenuBar();

        // Add the menubar to the frame
        setJMenuBar(menuBar);

        // Define and add two drop down menu to the menubar
        JMenu memberMenu = new JMenu("Members");
        JMenu calcMenu = new JMenu("Calculation");
        menuBar.add(memberMenu);
        menuBar.add(calcMenu);

        // Create and add simple menu item to one of the drop down menu
        newAction = new JMenuItem("New Member");
        updateAction = new JMenuItem("Update Member");
        removeAction = new JMenuItem("Remove Member");
        queryAction = new JMenuItem("Query Member");
        exitAction = new JMenuItem("Quit");
        simulateAction = new JMenuItem("Simulation");


        memberMenu.add(newAction);
        memberMenu.add(updateAction);
        memberMenu.add(removeAction);
        memberMenu.addSeparator();
        memberMenu.add(queryAction);
        memberMenu.addSeparator();
        memberMenu.add(exitAction);
        calcMenu.add(simulateAction);

        newAction.addActionListener(this);
        updateAction.addActionListener(this);
        removeAction.addActionListener(this);
        queryAction.addActionListener(this);
        exitAction.addActionListener(this);
        simulateAction.addActionListener(this);

        JPanel head = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        head.setLayout(flowLayout);
        head.setSize(100,100);
        add(new JLabel(new ImageIcon("C:\\Users\\Conor\\Desktop\\ocean2.gif")));

    }

    public static void main(String[] args) {

        rowingGUI me = new rowingGUI();

        me.setVisible(true);
    }

    private void addStandard() {

        String coachname;
        int coachnum;

       /* String fname = "John";
        String sname = "Walsh";
        String gender = "Male";
        String email = "johnwalsh@walsh.com";
        String phone = "0863025601";
        int age = 19;
        int height = 180; */
        /*

         String coachname = "John";
        int coachnum= 10;  */

        //Input Dialogs for adding a new member


        String fname = JOptionPane.showInputDialog(null, "Please enter your first name: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
        String sname = JOptionPane.showInputDialog(null, "Please enter your last name: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
        String gender = JOptionPane.showInputDialog(null, "Please enter your gender: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
        String email = JOptionPane.showInputDialog(null, "Please enter your email: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
        isValidEmail(email);
        String phone = JOptionPane.showInputDialog(null, "Please enter your phone: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
        String ageAsString = JOptionPane.showInputDialog(null, "Please enter your age: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
        int age = Integer.parseInt(ageAsString);
        String heightAsString = JOptionPane.showInputDialog(null, "Please enter your height: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
        int height = Integer.parseInt(heightAsString);

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
        String dateregistered = date.format(formatter);


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

        Standard mem = new Standard(fname, sname, gender, email, phone, age, height, dateregistered, status, coachname, coachnum);
        //members[count] = mem;
        members.add(mem);
        String memAsString = members.get(count).toString();


        System.out.print(members.get(count).toString());

       /* try {
            FileReader reader = new FileReader("output.txt");
            FileWriter writer = new FileWriter("output.txt");

            while(reader.)
            writer.write(members.get(count).toString() + "\n\n");




            writer.close();
        }  catch(IOException ex){
            System.out.println("Could not find file " + "output.txt");
        } */
        try {
            FileWriter fw = new FileWriter("test.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            try {
                FileReader fr = new FileReader("test.txt");
                BufferedReader br = new BufferedReader(fr);

                while ((memAsString = br.readLine()) != null) {
                    StringTokenizer strtok = new StringTokenizer(memAsString, " ");
                    while (strtok.hasMoreTokens()) {
                        bw.write("\n" + strtok.nextToken() + "\n");
                        Member.setNumObjects(count);
                        count++;
                    }
                    br.close();
                }
            } catch (FileNotFoundException e) {
                System.out.println("File was not found!");
            } catch (IOException e) {
                System.out.println("No file found!");
            }

            bw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error1!");
        } catch (IOException e) {
            System.out.println("Error2!");
        }


        setVisible(true);

    }
    private void addAthlete() {

        String coachname;
        int coachnum;
        String[] awardsArray = {"100m", "500m", "Endeavour", "Team"};
        boolean valid = false;
        //Input Dialogs for adding a new member
        String fname = JOptionPane.showInputDialog(null,"Please enter your first name: ","Rowing Club",JOptionPane.INFORMATION_MESSAGE);
        String sname = JOptionPane.showInputDialog(null,"Please enter your last name: ","Rowing Club",JOptionPane.INFORMATION_MESSAGE);
        String gender = JOptionPane.showInputDialog(null,"Please enter your gender: ","Rowing Club",JOptionPane.INFORMATION_MESSAGE);
        String email = JOptionPane.showInputDialog(null,"Please enter your email: ","Rowing Club",JOptionPane.INFORMATION_MESSAGE);
        isValidEmail(email);
        while (!valid) {
            while (!isValidEmail(email)) {
                if (isValidEmail(email)) {
                    valid = true;
                }
                email = JOptionPane.showInputDialog(null, "ERROR - Please enter your email: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        String phone = JOptionPane.showInputDialog(null,"Please enter your phone: ","Rowing Club",JOptionPane.INFORMATION_MESSAGE);
        int age = Integer.parseInt(JOptionPane.showInputDialog(null,"Please enter your age: ","Rowing Club",JOptionPane.INFORMATION_MESSAGE));
        int height = Integer.parseInt(JOptionPane.showInputDialog(null,"Please enter your height: ","Rowing Club",JOptionPane.INFORMATION_MESSAGE));
        int awardsOption = JOptionPane.showOptionDialog(null,"What awards would you like to go for: ","Rowing Club",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,awardsArray,awardsArray[3]);
        String awards = awardsArray[awardsOption];

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
        String dateregistered = date.format(formatter);


        String status = "Active";

        if(age < 18) {
            coachname = "John Diggins";
            coachnum = 4;
        }
        else if(age > 18 && age <=35)
        {
            coachname = "Brian Fitzwilliams";
            coachnum = 5;
        }
        else
        {
            coachname = "Mary Edgar";
            coachnum = 6;
        }

        Athlete mem = new Athlete(fname, sname, gender, email, phone, age, height, dateregistered, status, coachname, coachnum, awards);
        members.add(mem);

        JOptionPane.showMessageDialog(null,members.get(count).toString());
        count++;

        setVisible(true);

    }
    private void updateMember()
    {
        String question = JOptionPane.showInputDialog(null,"Enter the surname of the member you'd like to update: ");
        final ImageIcon icon = new ImageIcon("C:\\Users\\Conor\\Desktop\\boat.png");

       for(int i=0;i<members.size();i++)
        {
            if(question.equals(members.get(i).getSname()))
            {
                String question2 = JOptionPane.showInputDialog(null,"What would you like to edit? (Enter the number equivalent)\n\n" + "" +
                        "1. First Name\n2.Last Name\n3.Gender\n4.Email\n5.Phone\n6.Age\n7.Height\n8.Date Registered\n9.Status");
                String updated;
                int updatedNum;

                switch(question2) {
                    case "1":
                        updated = JOptionPane.showInputDialog(null,"Enter new first name: ");
                        members.get(i).setFname(updated);
                        break;
                    case "2":
                        updated = JOptionPane.showInputDialog(null,"Enter new last name: ");
                        members.get(i).setSname(updated);
                        break;
                    case "3":
                        updated = JOptionPane.showInputDialog(null,"Enter new gender: ");
                        members.get(i).setGender(updated);
                        break;
                    case "4":
                        updated = JOptionPane.showInputDialog(null,"Enter new email: ");
                        members.get(i).setEmail(updated);
                        break;
                    case "5":
                        updated = JOptionPane.showInputDialog(null,"Enter new phone number: ");
                        members.get(i).setPhone(updated);
                        break;
                    case "6":
                        updatedNum = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter new age: "));
                        members.get(i).setAge(updatedNum);
                        break;
                    case "7":
                        updatedNum = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter new height: "));
                        members.get(i).setHeight(updatedNum);
                        break;
                    case "8":
                        updated = JOptionPane.showInputDialog(null,"Enter new date registered: ");
                        members.get(i).setDateregistered(updated);
                        break;
                    case "9":
                        updated = JOptionPane.showInputDialog(null,"Enter new status: ");
                        members.get(i).setStatus(updated);
                        break;
                }
                JOptionPane.showMessageDialog(null,"Field has been updated in the membership file!","Rowing Club Membership Management",JOptionPane.INFORMATION_MESSAGE,icon);

            }
            else
            {
                JOptionPane.showMessageDialog(null,"Not found!");
                System.exit(0);
            }
        }
    }
    private void removeMember()
    {
        String question = JOptionPane.showInputDialog(null,"Enter the surname of the member you'd like to remove: ");


            if(question.equals(""))
            {
                JOptionPane.showInputDialog(null, "ERROR - Enter the surname of the member you'd like to remove: ");
            }


            for (count = 0; count < members.size(); count++) {



                if (question.equals(members.get(count).getSname())) {

                    members.get(count).setStatus("Inactive");
                    JOptionPane.showMessageDialog(null, "Member is no longer a member of the club and has been set to inactive: \n\n" + members.get(count).toString());
                    System.exit(0);
                }

                question = JOptionPane.showInputDialog(null, "ERROR - Enter the surname of the member you'd like to remove: ");

            }
        }

    private void queryMember() {

        setVisible(true);
        String question = JOptionPane.showInputDialog(null,"Enter the surname of the member you'd like to enquire about: ","Rowing Club",JOptionPane.INFORMATION_MESSAGE);
        int questionOpt = Integer.parseInt(question);

        if(questionOpt == JOptionPane.OK_OPTION) {
            if(question.equals(" "))
            {
                JOptionPane.showInputDialog(null, "ERROR - Enter the surname of the member you'd like to enquire about: ");
            }

            else
                for (int i = 0; i < members.size(); i++) {

                if (question.equals(members.get(i).getSname())) {
                    //  rowingMembers[i] = null;
                    JOptionPane.showMessageDialog(null, members.get(i).toString());
                    System.exit(0);
                }
                else
                    question = JOptionPane.showInputDialog(null, "ERROR - Enter the surname of the member you'd like to remove: ");

            }

        }
        else
            System.exit(0);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New Member")){
            // System.out.print("yes");

                        String[] memberships = {"Standard", "Athlete"};
                        int question2 = JOptionPane.showOptionDialog(null, "What type of membership would you like?","Rowing Club",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null,memberships,memberships[1]);

                      if (question2 == 0) {
                          this.setVisible(false);
                          addStandard();
                        } else if (question2 == 1) {
                            addAthlete();
                        }

        }
        else if (e.getActionCommand() .equals ("Update Member")){
            updateMember(); // branch to a separate method
        }
        else if (e.getActionCommand() .equals ("Remove Member") && members.size()>=1){
            removeMember();
        }
        else if (e.getActionCommand() .equals ("Query Member")){
            queryMember();
        }
        else if (e.getActionCommand() .equals ("Simulation")) {
            int amount = Integer.parseInt(JOptionPane.showInputDialog(null,"Please enter the amount of members you'd like to compare: (Max of 5)"));
            String simMembers = "";


            for(int i=0;i<amount;i++) {
                String question = JOptionPane.showInputDialog(null,"Please enter the surname of the member you'd like to compare: ");

                if(question.equals(members.get(i).getSname())) {
                    simMembers += members.get(i).toString() + "\n";
                }
                else {
                    JOptionPane.showMessageDialog(null,"That member doesn't exist on our system");
                }
            }

            JOptionPane.showMessageDialog(null,simMembers);

        }
        else if (e.getActionCommand() .equals ("Quit"))
        {
            JOptionPane.showMessageDialog(null, "System closing now, thanks for your time", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        else
            JOptionPane.showMessageDialog(null,"I have no idea what you clicked");
    }
    private static boolean isValidEmail(String email)
    {
        String firstName,lastName,firstNameAndLastName;
        int dotPosition, j;
        boolean valid = false;

        if(email.endsWith("@ittralee.ie") || email.endsWith("@hotmail.com") || email.endsWith("@gmail.com"))
        {
            firstNameAndLastName = email.substring(0,email.length() - 12);

            dotPosition = firstNameAndLastName.indexOf('.');

            if(dotPosition!=-1)
            {
                firstName = firstNameAndLastName.substring(0,dotPosition);
                lastName = firstNameAndLastName.substring(dotPosition+1,email.length()-12);

                if(!firstName.equals("") && Character.isUpperCase(firstName.charAt(0)))
                {
                    for(j=1;j<firstName.length();j++)
                        if(!Character.isLowerCase(firstName.charAt(j)))
                            break;

                    if(j==firstName.length() && !lastName.equals("") &&
                            Character.isUpperCase(lastName.charAt(0)))
                    {
                        for(j=1;j<lastName.length();j++)
                            if(!Character.isLowerCase(lastName.charAt(j)))
                                break;

                        if(j==lastName.length())
                            valid = true;
                    }
                }
            }
        }
        return valid;
    }
}

