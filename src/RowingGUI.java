

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class RowingGUI extends JFrame implements ActionListener{

    public int count;
    ArrayList<Member> members = new ArrayList<Member>();
    private Member member;
    Member[] memLoad;
    JLabel header;
    JPanel picPanel;
    JMenu fileMenu, editMenu, funcMenu;
    JMenuBar menuBar;


    private RowingGUI() {

        newSystem();

        //Set basics
        setIconImage(new ImageIcon(".\\images\\sailboat.png").getImage());
        setBackground(Color.darkGray);
        setTitle("Rowing Club Menu");
        setSize(500, 300);

        //Set position
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width/2-getSize().width/2, screenSize.height/2-getSize().height/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        fileMenu();
        editMenu();
        funcMenu();



        //PROBLEM HERE
        try
        {
            JLabel image = new JLabel(new ImageIcon(".\\images\\ocean2.gif"));
           add(image);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Invalid Image File in Main Screen");
        }
    }

    public static void main(String[] args) {

        RowingGUI me = new RowingGUI();
        me.setVisible(true);
    }
    public void save() throws IOException {

        try {

            File file = new File("members1.dat");

            FileOutputStream fos = new FileOutputStream(file);

            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("members.dat"));
            oos.writeObject(members);
            oos.close();

            JOptionPane.showMessageDialog(null,"File saved successfully!");
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "File not found", "Rowing Club", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }
    public void read()
    {
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("members1.dat"));

            members = ((ArrayList) ois.readObject());
            for(int i=0; i<members.size();i++) {
          //      members = ((ArrayList) ois.readObject());
                memLoad[i] = members.get(i);
                System.out.print("loop");
                System.out.print(members.get(i).toString());
            }

            ois.close();

            JOptionPane.showMessageDialog(null, "File loaded to the system.", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "File not found", "Rowing Club", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    private void fileMenu() {

        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem loadAction, saveAction, exitAction;

        loadAction = new JMenuItem("Load");
        saveAction = new JMenuItem("Save");
        exitAction = new JMenuItem("Quit");


        fileMenu.add(loadAction);
        fileMenu.addSeparator();
        fileMenu.add(saveAction);
        fileMenu.addSeparator();
        fileMenu.add(exitAction);

        loadAction.addActionListener(this);
        saveAction.addActionListener(this);
        exitAction.addActionListener(this);
    }
    private void editMenu() {

        editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        JMenuItem addAction, updateAction, removeAction, queryAction, listAction, desc;

        desc = new JMenuItem("Members");
        addAction = new JMenuItem("Add");
        updateAction = new JMenuItem("Update");
        removeAction = new JMenuItem("Remove");
        queryAction = new JMenuItem("Query");
        listAction = new JMenuItem("List All");

        editMenu.add(desc);
        editMenu.addSeparator();
        editMenu.add(addAction);
        editMenu.add(updateAction);
        editMenu.add(removeAction);
        editMenu.add(queryAction);
        editMenu.add(listAction);

        addAction.addActionListener(this);
        updateAction.addActionListener(this);
        removeAction.addActionListener(this);
        queryAction.addActionListener(this);
        listAction.addActionListener(this);

    }
    private void funcMenu() {

        funcMenu = new JMenu("Functions");
        menuBar.add(funcMenu);

        JMenuItem simulationAction;

        simulationAction = new JMenuItem("Simulation");
        simulationAction.addActionListener(this);
        funcMenu.add(simulationAction);

    }
    private void addStandard() {

        String coachname;
        int coachnum;

        String fname = JOptionPane.showInputDialog(null, "Please enter your first name: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
        String sname = JOptionPane.showInputDialog(null, "Please enter your last name: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
        String gender = JOptionPane.showInputDialog(null, "Please enter your gender: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
        String email = JOptionPane.showInputDialog(null, "Please enter your email: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
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
        }
        else if (age > 18 && age <= 35) {
            coachname = "Peter Andrews";
            coachnum = 2;
        }
        else {
            coachname = "David Edgar";
            coachnum = 3;
        }

        Standard mem = new Standard(fname, sname, gender, email, phone, age, height, dateregistered, status, coachname, coachnum);
        //members[count] = mem;
        members.add(mem);

        System.out.print(members.get(count).toString());
        count++;

        setVisible(true);

    }
    private void addAthlete() {

        String coachname;
        int coachnum;
        String[] awardsArray = {"100m", "500m", "Endeavour", "Team"};
        String fname = "", sname = "", gender = "", email = "", phone = "", awards = "";
        int age = 0, height = 0;
        boolean valid = false;
        //Input Dialogs for adding a new member

        //CANCEL Option for looping not working 999999999999999
        while(!valid) {
        try {
         fname = JOptionPane.showInputDialog(null,"Please enter your first name: ","Rowing Club",JOptionPane.INFORMATION_MESSAGE);
         sname = JOptionPane.showInputDialog(null,"Please enter your last name: ","Rowing Club",JOptionPane.INFORMATION_MESSAGE);
         gender = JOptionPane.showInputDialog(null,"Please enter your gender: ","Rowing Club",JOptionPane.INFORMATION_MESSAGE);
         email = JOptionPane.showInputDialog(null,"Please enter your email: ","Rowing Club",JOptionPane.INFORMATION_MESSAGE);
         phone = JOptionPane.showInputDialog(null,"Please enter your phone: ","Rowing Club",JOptionPane.INFORMATION_MESSAGE);
         age = Integer.parseInt(JOptionPane.showInputDialog(null,"Please enter your age: ","Rowing Club",JOptionPane.INFORMATION_MESSAGE));
         height = Integer.parseInt(JOptionPane.showInputDialog(null,"Please enter your height: ","Rowing Club",JOptionPane.INFORMATION_MESSAGE));
         int awardsOption = JOptionPane.showOptionDialog(null,"What awards would you like to go for: ","Rowing Club",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,awardsArray,awardsArray[3]);
         awards = awardsArray[awardsOption];
                valid = true;
        }
        catch (NullPointerException u) {

            int question = JOptionPane.showConfirmDialog(null, "Cancel field detected - would you like to restart?", "Rowing Club", JOptionPane.WARNING_MESSAGE);

                if (question == 0) {

                fname = JOptionPane.showInputDialog(null, "Please enter your first name: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                JOptionPane.showMessageDialog(null, "Exiting now", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
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
    private void newSystem() {
        memLoad = new Member[5];
        count = 0;
        Member.setNumObjects(members.size());
    }
    private void display() {

        JTextArea jta = new JTextArea();
        if (members.size()>0) {
            jta.setText("Tralee Rowing Club Members List: \n\n");
            for (int i = 0; i<members.size(); i++) {
                jta.append("Member ID: " + (i+1) + "\n " + memLoad[i].toString() + "\n\n");
            }
            JOptionPane.showMessageDialog(null,jta);
        }
        else
            JOptionPane.showMessageDialog(null,"No members in the system");
    }
    public void actionPerformed(ActionEvent e) {


        if(e.getActionCommand() .equals("Load")) {
                read();
                display();
        }
        else if(e.getActionCommand() .equals("Save")) {
            try {
                save();
            }
            catch (IOException f) {
                JOptionPane.showMessageDialog(null, "Error - data did not save!", "Rowing CLUB", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getActionCommand() .equals ("Quit"))
        {
            JOptionPane.showMessageDialog(null, "System closing now, thanks for your time", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        else if (e.getActionCommand() .equals ("Add")){

            String[] memberships = {"Standard", "Athlete"};
            int question2 = JOptionPane.showOptionDialog(null, "What type of membership would you like?","Rowing Club",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null,memberships,memberships[1]);

            if (question2 == 0) {
                addStandard();
            } else if (question2 == 1) {
                addAthlete();
            }

        }
        else if (e.getActionCommand() .equals ("Update")){
            updateMember();
        }
        else if (e.getActionCommand() .equals ("Remove")){
            removeMember();
        }
        else if (e.getActionCommand() .equals ("Query")){
            queryMember();
        }
        else if (e.getActionCommand() .equals ("Simulation")) {
            simulation();
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

    private JFrame simulation() {

    JFrame frame = new JFrame();

        frame.setIconImage(new ImageIcon(".\\images\\sailboat.png").getImage());
        frame.setTitle("Rowing Club Menu");
        frame.setSize(750, 250);
        frame.setBounds(0,0,750,250);


            frame.add(new JLabel(new ImageIcon(".\\images\\water.gif")));
            frame.setResizable(false);


        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(screenSize.width/2-frame.getSize().width/2, screenSize.height/2-this.getSize().height/2);

        frame.setVisible(true);

    return frame;
    }
}

