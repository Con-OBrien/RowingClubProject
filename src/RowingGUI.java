

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;


public class RowingGUI extends JFrame implements ActionListener{

    public static int count;
    static ArrayList<Member> members = new ArrayList<>();
    static ArrayList<Coach> coaches = new ArrayList<>();
    private Member[] memLoad;
    private Coach[] coachLoad;
    private JMenuBar menuBar;
    private int totalFees;


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
        coachMenu();
        searchMenu();


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
    //Save data to Member & Coach files.
    public static void save() throws IOException {

        try {

            File membersfile = new File("members.dat");

            FileOutputStream fos = new FileOutputStream(membersfile);

            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(members);

            oos.close();

        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "File not found", "Rowing Club", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
        try {

            File coachesfile = new File("coaches.dat");

            FileOutputStream fos2 = new FileOutputStream(coachesfile);

            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);

            oos2.writeObject(coaches);

            oos2.close();

        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "File not found", "Rowing Club", JOptionPane.WARNING_MESSAGE);

            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,"File saved successfully!");
    }

    //Load data from Member & Coach files.
    private void read()
    {
        int i;

        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("members.dat"));

            members = ((ArrayList) ois.readObject());

            for(i=0; i<members.size(); i++) {

                if(members.size() == 0) {
                  break;
                }
                memLoad[i] = members.get(i);

                if(members.get(i).getExperience().equals("Competitive")) {
                    totalFees += 200;
                }
                else {
                    totalFees += 100;
                }
            }

            ois.close();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "MEMBERS - File not found", "Rowing Club", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        try {
            ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream("coaches.dat"));
            coaches = ((ArrayList) ois2.readObject());

            for(i=0; i<coaches.size();i++) {

                if(coaches.size()==0) {
                    break;
                }
                try {
                    coachLoad[i] = coaches.get(i);
                }
                catch (NullPointerException n) {
                    JOptionPane.showMessageDialog(null,"Coaches have not been initialised");
                }
            }

            ois2.close();
            JOptionPane.showMessageDialog(null, "File loaded to the system.", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(ClassNotFoundException c) {
            JOptionPane.showMessageDialog(null, "CLASSNOTFOUNDEXCEPTION", "Rowing Club", JOptionPane.ERROR_MESSAGE);
            c.printStackTrace();
        }
        catch(IOException q) {
            JOptionPane.showMessageDialog(null, "IOEXCEPTION", "Rowing Club", JOptionPane.ERROR_MESSAGE);
            q.printStackTrace();
        }
        catch (NullPointerException e)
        {
            JOptionPane.showMessageDialog(null, "NULLPOINTEREXCEPTION", "Rowing Club", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        count = coaches.size();
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

        JMenu editMenu;
        editMenu = new JMenu("Members");
        menuBar.add(editMenu);

        JMenuItem addAction, updateAction, removeAction, listAllAction;

        addAction = new JMenuItem("Add Member");
        updateAction = new JMenuItem("Update Member");
        removeAction = new JMenuItem("Remove Member");
        listAllAction = new JMenuItem("List All Members");


        editMenu.add(addAction);
        editMenu.add(updateAction);
        editMenu.add(removeAction);
        editMenu.addSeparator();
        editMenu.add(listAllAction);


        addAction.addActionListener(this);
        updateAction.addActionListener(this);
        removeAction.addActionListener(this);
        listAllAction.addActionListener(this);

    }
    private void coachMenu() {

        JMenu coachMenu;
        coachMenu = new JMenu("Coaches");
        menuBar.add(coachMenu);

        JMenuItem addAction, updateAction, removeAction, listAllAction;

        addAction = new JMenuItem("Add Coach");
        updateAction = new JMenuItem("Update Coach");
        removeAction = new JMenuItem("Remove Coach");
        listAllAction = new JMenuItem("List All Coaches");

        coachMenu.add(addAction);
        coachMenu.add(updateAction);
        coachMenu.add(removeAction);
        coachMenu.addSeparator();
        coachMenu.add(listAllAction);

        addAction.addActionListener(this);
        updateAction.addActionListener(this);
        removeAction.addActionListener(this);
        listAllAction.addActionListener(this);

    }

    private void searchMenu() {

        JMenu searchMenu;
        searchMenu = new JMenu("Search");
        menuBar.add(searchMenu);

        JMenuItem searchAction, feesAction ;

        searchAction = new JMenuItem("Search");
        feesAction = new JMenuItem("Fees");
        searchAction.addActionListener(this);
        feesAction.addActionListener(this);
        searchMenu.add(searchAction);
        searchMenu.add(feesAction);

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
    private void updateCoach() {


        String question = JOptionPane.showInputDialog(null, "Enter the surname of the coach you'd like to update: ", "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
        int i, selected = 0;
       int confirmMsg = 1;

        try {
            for (i = 0; i < coaches.size(); i++) {
                if (question.equals(coaches.get(i).getCoachLastName())) {

                     confirmMsg = JOptionPane.showConfirmDialog(null,"Is this the member you'd like to select?\n\n" + coaches.get(i).toString(), "Rowing Club", JOptionPane.YES_NO_OPTION);
                /*    if (confirmMsg == 0) {
                        break;
                    } */
                selected = i;
                }
            }


            if (confirmMsg == 0) {

                String question2 = JOptionPane.showInputDialog(null, "What would you like to edit? (Enter the number equivalent)\n\n" + "" +
                        "1. First Name\n2.Last Name \n3. Phone Number");
                String updated;
                int updatedNum;

                switch (question2) {
                    case "1":
                        updated = JOptionPane.showInputDialog(null, "Enter new first name: ");
                        coaches.get(selected).setCoachFirstName(updated);
                        break;
                    case "2":
                        updated = JOptionPane.showInputDialog(null, "Enter new last name: ");
                        coaches.get(selected).setCoachLastName(updated);
                        break;
                    case "3":
                        updated = JOptionPane.showInputDialog(null, "Enter new phone number: ");
                        coaches.get(selected).setCoachPhone(updated);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid number entered, exiting update option now!");
                        setVisible(true);
                        break;

                }
                JOptionPane.showMessageDialog(null,"Field has been updated successfully!");
                JOptionPane.showMessageDialog(null, "Make sure to save!\n\n" + coaches.get(selected).toString(), "Rowing Club", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (NullPointerException n) {
            JOptionPane.showMessageDialog(null,"Cancel option selected! Exiting now..");
            setVisible(true);
        }
        catch (IndexOutOfBoundsException r) {
            JOptionPane.showMessageDialog(null,"Member not found! Exiting now..");
            setVisible(true);
            r.printStackTrace();
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
    private void removeCoach()
    {
        String question = JOptionPane.showInputDialog(null,"Enter the surname of the coach you'd like to remove: ");


        try {
            for (int i = 0; i < coaches.size(); i++) {

                if (question.equals(coaches.get(i).getCoachLastName())) {

                    coaches.get(i).setCoachStatus("Inactive");
                    JOptionPane.showMessageDialog(null, "Member has been set to inactive: \n\n" + coaches.get(i).toString());
                    setVisible(true);
                }

            }
        }
        catch (NullPointerException n) {
            JOptionPane.showMessageDialog(null,"Cancel option selected, quitting now..");
        }
    }
    private void newSystem() {
        memLoad = new Member[50];
        coachLoad = new Coach[50];

        count = 0;
    }
    private void displayAll() {

        JTextArea jta = new JTextArea();
        jta.setFont(new Font("arian", Font.BOLD, 10));
        jta.setSize(400,300);

        if (members.size()>0) {
            jta.setText("Tralee Rowing Club Members List: \n\n");
            for (int i = 0; i<members.size(); i++) {
                try {
                    jta.append("Member ID: " + (i + 1) + "\n" + memLoad[i].toString() + "\n\n");
                }
                catch (NullPointerException n) {
                    n.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(null,jta);
        }
        else
            JOptionPane.showMessageDialog(null,"No members in the system");


    }
    private void displayAllCoaches() {

        JTextArea jta = new JTextArea();
        jta.setFont(new Font("arian", Font.BOLD, 10));
        jta.setSize(400,300);
        try {
        if (coaches.size()>0) {
            jta.setText("Tralee Rowing Club Coaches List: \n\n");
            for (int i = 0; i<coaches.size(); i++) {

                    jta.append(coachLoad[i].toString() + "\n\n");

            }
            JOptionPane.showMessageDialog(null,jta,"Rowing Club",JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null,"No coaches in the system","Rowing Club",JOptionPane.WARNING_MESSAGE);
        }
        catch (NullPointerException n) {
            JOptionPane.showMessageDialog(null,"File must be saved and exited before displaying updated list!","Rowing Club",JOptionPane.WARNING_MESSAGE);

        }

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
        else if (e.getActionCommand() .equals ("Add Member")){
            AddMember a = new AddMember();
        }
        else if (e.getActionCommand() .equals ("Update Member")){
            updateMember();
        }
        else if (e.getActionCommand() .equals ("List All Members")){
            displayAll();
        }
        else if (e.getActionCommand() .equals ("Remove Member")){
            removeMember();
        }
        else if (e.getActionCommand() .equals ("Add Coach")){
            AddCoach a = new AddCoach();
            a.setVisible(true);
        }
        else if (e.getActionCommand() .equals ("Update Coach")){
            updateCoach();
        }
        else if (e.getActionCommand() .equals ("Remove Coach")){
            removeCoach();
        }
        else if (e.getActionCommand() .equals ("List All Coaches")){
            displayAllCoaches();
        }
        else if (e.getActionCommand() .equals ("Search")) {
            searchFunc();
        }
        else if(e.getActionCommand() .equals ("Fees")) {
            JOptionPane.showMessageDialog(null,"The total amount of fees from members for this year is: " + totalFees);
        }

        else {
            JOptionPane.showMessageDialog(null, "I have no idea what you clicked","Rowing Club",JOptionPane.WARNING_MESSAGE);
        }
    }
    private void searchFunc() {

        String[] options  = { "Forename", "Surname", "Gender", "Email", "Phone", "Age", "Height", "Date Registered","Paid Status"  };

        ArrayList<String> fnameArray = new ArrayList<>();
        ArrayList<String> snameArray = new ArrayList<>();
        ArrayList<String> genderArray = new ArrayList<>();
        ArrayList<String> emailArray = new ArrayList<>();
        ArrayList<String> phoneArray = new ArrayList<>();
        ArrayList<Integer> ageArray = new ArrayList<>();
        ArrayList<Integer> heightArray = new ArrayList<>();
        ArrayList<String> dateregisteredArray = new ArrayList<>();
        ArrayList<String> paidArray = new ArrayList<>();

        try {
            String input = (String) JOptionPane.showInputDialog(null, "Search Criteria",
                    "Rowing Club", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            String fname = "", sname = "", gender = "", email = "", phone = "", dateregistered = "", paid = "", age = "", height = "";

            for (int k = 0; k < members.size(); k++) {

                fnameArray.add(members.get(k).getFname());
                snameArray.add(members.get(k).getSname());
                genderArray.add(members.get(k).getGender());
                emailArray.add(members.get(k).getEmail());
                phoneArray.add(members.get(k).getPhone());
                ageArray.add(members.get(k).getAge());
                heightArray.add(members.get(k).getHeight());
                dateregisteredArray.add(members.get(k).getDateregistered());
                paidArray.add(members.get(k).getPaidstring());

            }

            Collections.sort(fnameArray);
            Collections.sort(snameArray);
            Collections.sort(genderArray);
            Collections.sort(emailArray);
            Collections.sort(ageArray);
            Collections.sort(heightArray);
            Collections.sort(dateregisteredArray);
            Collections.sort(paidArray);

            for (int t = 0; t < members.size(); t++) {
                fname += t + 1 + ". " + fnameArray.get(t) + "\n";
                sname += t + 1 + ". " + fnameArray.get(t) + " " + snameArray.get(t) + "\n";
                gender += t + 1 + ". " + fnameArray.get(t) + " " + snameArray.get(t) + "  " + genderArray.get(t) + "\n";
                email += t + 1 + ". " + fnameArray.get(t) + " " + snameArray.get(t) + "  " + emailArray.get(t) + "\n";
                phone += t + 1 + ". " + fnameArray.get(t) + " " + snameArray.get(t) + "  " + phoneArray.get(t) + "\n";
                age += t + 1 + ". " + fnameArray.get(t) + " " + snameArray.get(t) + "  " + ageArray.get(t) + "\n";
                height += t + 1 + ". " + fnameArray.get(t) + " " + snameArray.get(t) + "  " + heightArray.get(t) + "\n";
                dateregistered += t + 1 + ". " + fnameArray.get(t) + " " + snameArray.get(t) + "  " + dateregisteredArray.get(t) + "\n";
                paid += t + 1 + ". " + fnameArray.get(t) + " " + snameArray.get(t) + "  " + paidArray.get(t) + "\n";
            }

            switch (input) {
                case "Forename":
                    JOptionPane.showMessageDialog(null, "List of members by forename: \n" + fname);
                    break;

                case "Surname":
                    JOptionPane.showMessageDialog(null, "List of members sorted by surname: \n" + sname);
                    break;

                case "Gender":
                    JOptionPane.showMessageDialog(null, "List of members sorted by gender: \n" + gender);
                    break;

                case "Email":
                    JOptionPane.showMessageDialog(null, "List of members sorted by email: \n" + email);
                    break;

                case "Phone":
                    JOptionPane.showMessageDialog(null, "List of members sorted by phone: \n" + phone);
                    break;

                case "Age":
                    JOptionPane.showMessageDialog(null, "List of members sorted by age: \n" + age);
                    break;

                case "Height":
                    JOptionPane.showMessageDialog(null, "List of members sorted by height: \n" + height);
                    break;

                case "Date Registered":
                    JOptionPane.showMessageDialog(null, "List of members sorted by date registered: \n" + dateregistered);
                    break;

                case "Paid Status":
                    JOptionPane.showMessageDialog(null, "List of members sorted by paid status: \n" + paid);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Unsure what you clicked!");
                    break;

            }
        }
        catch(NullPointerException n) {
            JOptionPane.showMessageDialog(null,"Cancel option was selected.. quitting now!");
        }
    }
}

