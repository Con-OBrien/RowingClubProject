

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

    public static int count;
    static ArrayList<Member> members = new ArrayList<Member>();

    private Member member;
    Member[] memLoad;
    Member[] unpaidmembers;
    private JMenu editMenu, funcMenu;
    JMenuBar menuBar;
    int totalFees;


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


        try {
            JLabel image = new JLabel(new ImageIcon(".\\images\\ocean2.gif"));
           add(image);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid Image File in Main Screen");
        }
    }

    public static void main(String[] args) {

        RowingGUI me = new RowingGUI();
        me.setVisible(true);
    }
    public static void save() throws IOException {

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
        int i;

        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("members1.dat"));

            members = ((ArrayList) ois.readObject());

            for(i=0; i<members.size();i++) {
                memLoad[i] = members.get(i);
            }

            totalFees += 100*members.size();
            count = i;
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

        JMenu fileMenu = new JMenu("File");
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

        JMenuItem addAction, updateAction, removeAction, queryAction, listActiveAction, listAllAction, desc;

        desc = new JMenuItem("Members");
        addAction = new JMenuItem("Add");
        updateAction = new JMenuItem("Update");
        removeAction = new JMenuItem("Remove");
        queryAction = new JMenuItem("Query");
        listActiveAction = new JMenuItem("List Active Members");
        listAllAction = new JMenuItem("List All Members");

        editMenu.add(desc);
        editMenu.addSeparator();
        editMenu.add(addAction);
        editMenu.add(updateAction);
        editMenu.add(removeAction);
        editMenu.add(queryAction);
        editMenu.addSeparator();
        editMenu.add(listActiveAction);
        editMenu.add(listAllAction);


        addAction.addActionListener(this);
        updateAction.addActionListener(this);
        removeAction.addActionListener(this);
        queryAction.addActionListener(this);
        listActiveAction.addActionListener(this);
        listAllAction.addActionListener(this);

    }

    private void funcMenu() {

        funcMenu = new JMenu("Functions");
        menuBar.add(funcMenu);

        JMenuItem simulationAction;

        simulationAction = new JMenuItem("Fees");
        simulationAction.addActionListener(this);
        funcMenu.add(simulationAction);

    }

    private void addStandard() {

        String coachname;
        int coachnum, age = 0, height = 0;
        String fname = "", sname = "", gender = "", email = "", phone = "";

    try {
            addMember g = new addMember();
            fname = JOptionPane.showInputDialog(null, "Please enter your first name: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
            sname = JOptionPane.showInputDialog(null, "Please enter your last name: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
            gender = JOptionPane.showInputDialog(null, "Please enter your gender: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
            email = JOptionPane.showInputDialog(null, "Please enter your email: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
            phone = JOptionPane.showInputDialog(null, "Please enter your phone: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
            age = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter your age: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE));
            height = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter your height: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE));
    }
    catch (Exception n) {
        JOptionPane.showMessageDialog(null,"Cancel option selected exiting now!");
    }


        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
        String dateregistered = date.format(formatter);


        boolean paid = true;

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

      //  Standard mem = new Standard(fname, sname, gender, email, phone, age, height, dateregistered, paid, status, coachname, coachnum);

    //    members.add(mem);

        if(!fname.equals("") && !sname.equals("") && !gender.equals("") && !email.equals("") && !phone.equals("") && age!=0 && height!=0 ) {
            JOptionPane.showMessageDialog(null,members.get(count).toString());
        }
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

        try {
            fname = JOptionPane.showInputDialog(null, "Please enter your first name: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
            sname = JOptionPane.showInputDialog(null, "Please enter your last name: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
            gender = JOptionPane.showInputDialog(null, "Please enter your gender: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
            email = JOptionPane.showInputDialog(null, "Please enter your email: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
            phone = JOptionPane.showInputDialog(null, "Please enter your phone: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
            age = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter your age: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE));
            height = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter your height: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE));
            int awardsOption = JOptionPane.showOptionDialog(null, "What awards would you like to go for: ", "Rowing Club", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, awardsArray, awardsArray[3]);
            awards = awardsArray[awardsOption];


        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
        String dateregistered = date.format(formatter);

        boolean paid = true;
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

      //  Athlete mem = new Athlete(fname, sname, gender, email, phone, age, height, dateregistered, paid, experience, status, coachname, coachnum, awards);
     //   members.add(mem);


        if(!fname.equals("") || !sname.equals("") || !gender.equals("") || !email.equals("") || !phone.equals("") || age!=0 || height!=0 ) {
            JOptionPane.showMessageDialog(null,members.get(count).toString());
        }

        count++;

        setVisible(true);
        }
        catch (NullPointerException n) {
            JOptionPane.showMessageDialog(null,"Not sure what you selected! Exiting now!");
        }
        catch (Exception n) {
            JOptionPane.showMessageDialog(null,"Cancel option selected exiting now!");
        }
    }

    private void updateMember() {


        String question = JOptionPane.showInputDialog(null, "Enter the surname of the member you'd like to update: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
        int i;
        int confirmMsg = 1;

        try {
            for (i = 0; i < members.size(); i++) {
                if (question.equals(members.get(i).getSname())) {

                    confirmMsg = JOptionPane.showConfirmDialog(null,"Is this the member you'd like to select?\n\n" + members.get(i).toString(), "Rowing Club", JOptionPane.YES_NO_OPTION);
                    if (confirmMsg == 0) {
                        break;
                    }
                }
            }


            if (confirmMsg == 0) {

                String question2 = JOptionPane.showInputDialog(null, "What would you like to edit? (Enter the number equivalent)\n\n" + "" +
                        "1. First Name\n2.Last Name\n3.Gender\n4.Email\n5.Phone\n6.Age\n7.Height\n8.Date Registered\n9.Status");
                String updated;
                int updatedNum;

                switch (question2) {
                    case "1":
                        updated = JOptionPane.showInputDialog(null, "Enter new first name: ");
                        members.get(i).setFname(updated);
                        break;
                    case "2":
                        updated = JOptionPane.showInputDialog(null, "Enter new last name: ");
                        members.get(i).setSname(updated);
                        break;
                    case "3":
                        updated = JOptionPane.showInputDialog(null, "Enter new gender: ");
                        members.get(i).setGender(updated);
                        break;
                    case "4":
                        updated = JOptionPane.showInputDialog(null, "Enter new email: ");
                        members.get(i).setEmail(updated);
                        break;
                    case "5":
                        updated = JOptionPane.showInputDialog(null, "Enter new phone number: ");
                        members.get(i).setPhone(updated);
                        break;
                    case "6":
                        updatedNum = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new age: "));
                        members.get(i).setAge(updatedNum);
                        break;
                    case "7":
                        updatedNum = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new height: "));
                        members.get(i).setHeight(updatedNum);
                        break;
                    case "8":
                        updated = JOptionPane.showInputDialog(null, "Enter new date registered: ");
                        members.get(i).setDateregistered(updated);
                        break;
                    case "9":
                        updated = JOptionPane.showInputDialog(null, "Enter new status: ");
                        members.get(i).setStatus(updated);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid number entered, exiting update option now!");
                        setVisible(true);
                        break;

                }

                JOptionPane.showMessageDialog(null, "Make sure to save!\n\n" + members.get(i).toString(), "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (NullPointerException n) {
            JOptionPane.showMessageDialog(null,"Cancel option selected! Exiting now..");
            setVisible(true);
        }
        catch (IndexOutOfBoundsException r) {
            JOptionPane.showMessageDialog(null,"Member not found! Exiting now..");
            setVisible(true);
        }
    }

    private void removeMember()
    {
        String question = JOptionPane.showInputDialog(null,"Enter the surname of the member you'd like to remove: ");


        try {
            for (int i = 0; i < members.size(); i++) {

                if (question.equals(members.get(i).getSname())) {

                    members.get(i).setStatus("Inactive");
                    JOptionPane.showMessageDialog(null, "Member has been set to inactive: \n\n" + members.get(i).toString());
                    setVisible(true);
                }

            }
        }
        catch (NullPointerException n) {
            JOptionPane.showMessageDialog(null,"Cancel option selected, quitting now..");
        }
        }

    private void queryMember() {

        String question = JOptionPane.showInputDialog(null,"Enter the surname of the member you'd like to enquire about: ","Rowing Club",JOptionPane.INFORMATION_MESSAGE);

        try {
            for (int i = 0; i < members.size(); i++) {
                if (question.equals(members.get(i).getSname())) {
                        //  rowingMembers[i] = null;
                        JOptionPane.showMessageDialog(null, members.get(i).toString());
                }
                else {
                    JOptionPane.showMessageDialog(null,"No member found");
                }
            }
        }
        catch (NumberFormatException n) {
            System.out.print("yes");
        }

    }
    private void newSystem() {
        memLoad = new Member[50];
        count = 0;
    }
    private void displayActive() {

        JTextArea jta = new JTextArea();
        jta.setSize(400,400);

        JScrollPane scrollV = new JScrollPane (jta);
        scrollV.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        if (members.size()>0) {
            jta.setText("Tralee Rowing Club Members List: \n\n");
            for (int i = 0; i<members.size(); i++) {
                if(!memLoad[i].getStatus().equals("Inactive")) {
                    jta.append("Member ID: " + (i + 1) + "\n" + memLoad[i].toString() + "\n\n");
                }
            }
            JOptionPane.showMessageDialog(null,jta);
        }
        else
            JOptionPane.showMessageDialog(null,"No members in the system");
    }
    private void displayAll() {

        JTextArea jta = new JTextArea();
        jta.setFont(new Font("arian", Font.BOLD, 10));
        jta.setSize(400,300);

        if (members.size()>0) {
            jta.setText("Tralee Rowing Club Members List: \n\n");
            for (int i = 0; i<members.size(); i++) {
                jta.append("Member ID: " + (i + 1) + "\n" + memLoad[i].toString() + "\n\n");
            }
            JOptionPane.showMessageDialog(null,jta);
        }
        else
            JOptionPane.showMessageDialog(null,"No members in the system");


    }
    public void actionPerformed(ActionEvent e) {


        if(e.getActionCommand() .equals("Load")) {
                read();
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


           /* String[] memberships = {"Standard", "Athlete"};
            int question2 = JOptionPane.showOptionDialog(null, "What type of membership would you like?","Rowing Club",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null,memberships,memberships[1]);

            if (question2 == 0) {
                addStandard();

            } else if (question2 == 1) {
                addAthlete();
            } */
            addMember a = new addMember();

           // System.out.print(members.size());
            count++;
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

        else if (e.getActionCommand() .equals ("List Active Members")){
            displayActive();
        }

        else if (e.getActionCommand() .equals ("List All Members")){
            displayAll();
        }

        else if (e.getActionCommand() .equals ("Fees")) {
            feesFunc();
        }

        else {
            JOptionPane.showMessageDialog(null, "I have no idea what you clicked");
        }

        }
    private void feesFunc() {

        try {
            JOptionPane.showMessageDialog(null, "Total Membership fees: " + totalFees + "\n" +
                    "Total Members: " + members.size() + "\n\nThe first member was : " + members.get(0).getFname() + " " + members.get(0).getSname() + "\nThat member joined on: " + members.get(0).getDateregistered() +
                    "\n\nMembers who haven't paid their fees: ");
        }
        catch(IndexOutOfBoundsException i) {
            JOptionPane.showMessageDialog(null,"File not found to reference, quitting function now..");
        }
    }
}

