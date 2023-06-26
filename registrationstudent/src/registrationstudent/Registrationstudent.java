
package registrationstudent;


import javax.swing.border.TitledBorder;
import javax.swing.*;
import java.awt.Color;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;

public class Registrationstudent extends JFrame
{
    String password = "";
    String username = "root";
    String url="jdbc:mysql://localhost:3306/psp?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String driver = "com.mysql.cj.jdbc.Driver";

    Connection conn;
    private JLabel backgroundLabel;
    
Registrationstudent() throws SQLException {
    
        getContentPane().setBackground(new Color(159, 108, 189)); 
        setTitle("Registration Student");
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Arial", Font.BOLD, 16);
        RegisterForm register = new RegisterForm();
        register.setBorder(new TitledBorder("Registration Student"));
        add(register);

        // Set the background image
        backgroundLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/resources/logo.png"));
        Image image = imageIcon.getImage().getScaledInstance(400, 500, Image.SCALE_SMOOTH);
        backgroundLabel.setIcon(new ImageIcon(image));
        backgroundLabel.setText("");
        getContentPane().add(backgroundLabel);
        backgroundLabel.setBounds(0, 0, 400, 500); // Adjust the bounds to match your form or panel size
        backgroundLabel.setOpaque(false);

        setSize(900, 700);
        setVisible(true);
        pack();
        
        String dbcreate ="jdbc:mysql://localhost:3306?zeroDateTimeBehavior=CONVERT_TO_NULL";
    try{
            Class.forName(driver); // Load the JDBC driver
    Connection kon = DriverManager.getConnection(dbcreate, username, password); // Establish a database connection
    Statement stm = kon.createStatement(); // Create a Statement object
    
    String createDBQuery = "CREATE DATABASE IF NOT EXISTS psp"; // SQL statement to create database
    stm.executeUpdate(createDBQuery); // Execute SQL statement

    String useDBQuery = "USE psp"; // Switch to the psp database
    stm.execute(useDBQuery); // Execute SQL statement

    String createTableQuery = "CREATE TABLE IF NOT EXISTS student_details (" // Create the student table
            + "name VARCHAR(200), "
            + "ic_stud VARCHAR(200), "
            + "email VARCHAR(200), "
            + "phone_no VARCHAR(200), "
            + "age VARCHAR(200), "
            + "dob VARCHAR(200), "
            + "address VARCHAR(200), "
            + "state VARCHAR(200), "
            + "gender VARCHAR(200), "
            + "course_code VARCHAR(200), "
            + "course_name VARCHAR(200), "
            + "depart VARCHAR(200), "
            + "reg_date VARCHAR(200), "
            + "ref_lecturer VARCHAR(200))";
    stm.executeUpdate(createTableQuery); // Execute SQL statement
} catch (ClassNotFoundException | SQLException e) {
    JOptionPane.showMessageDialog(null, "Failed to create database and table: " + e.getMessage());
}
    }

public class RegisterForm extends JPanel implements ActionListener{
    
    JLabel lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7,lbl8,lbl9,lbl10,lbl11,lbl12,lbl13,lbl14,lbl15,lbl16,lbl17;
    JTextField txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8;
    JComboBox cbo1,cbo2,cbo3,cbo4;
    JButton btndelete,btnupdate,btncreate,btnclear,btnsearch;
    JRadioButton rb1,rb2;
    ButtonGroup bg;
    JTextArea txta1;
    String state[] = {"-Select-","Sabah","Selangor","Pahang","Terengganu","Penang","Sarawak","Kedah","Kelantan","Malacca","Johor","Negeri Sembilan","Perak","Perlis"};
    String Ccode[] = {"-Select-","DDT","DEP","DEE","DTK","DJL","DKM","DMT","DTP","DAT","DLS","DIB","DPM"};
    String Cname[] = {"-Select-","Diploma Teknologi Maklumat (Teknologi Digital)","Diploma Akauntasi","Diploma Pengajian Perniagaan","Diploma Kewangan Dan Perbankan Islam",
                      "Diploma Pengurusan Logistik Dan Rangkaian Bekalan","Diploma Kejuruteraan Elektronik (Komputer)","Diploma Kejuruteraan Elektronik (Komunikasi)",
                      "Diploma Kejuruteraan Elektrik Dan Elektronik","Diploma Kejuruteraan Mekanikal","Diploma Kejuruteraan Mekanikal (Loji)","Diploma Kejuruteraan Mekanikal (Pembuatan)",
                      "Diploma Kejuruteraan Mekanikal (Tekstil)"};
    
String Dept[] = {"-Select-","JTMK","JP","JKE","JKM"};
String gender;


public RegisterForm() {
    setBackground(new Color(159, 108, 189));
    setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Font font = new Font("Arial", Font.BOLD,16);
    
    lbl1 = new JLabel("Student Name: ");
    lbl1.setFont(font);
    add(lbl1);
    lbl1.setBounds(30,40,150,25);
    
    txt1 = new JTextField(10);
    add(txt1);
    txt1.setBounds(210,40,300,25);
    
    lbl2 = new JLabel("IC Student: ");
    lbl2.setFont(font);
    add(lbl2);
    lbl2.setBounds(30,80,150,25);
    
    txt2 = new JTextField(10);
    add(txt2);
    txt2.setBounds(210,80,300,25);
    
    lbl3 = new JLabel("E-Mail: ");
    lbl3.setFont(font);
    add(lbl3);
    lbl3.setBounds(30,120,150,25);
    
    txt3 = new JTextField(10);
    add(txt3);
    txt3.setBounds(210, 120, 300, 25);
    
    lbl4 = new JLabel("Phone Number: ");
    lbl4.setFont(font);
    add(lbl4);
    lbl4.setBounds(30,160,150,25);
    
    txt4 = new JTextField(10);
    add(txt4);
    txt4.setBounds(210, 160, 300, 25);
    
    lbl5 = new JLabel("Age: ");
    lbl5.setFont(font);
    add(lbl5);
    lbl5.setBounds(30,200,150,25);
    
    txt5 = new JTextField(10);
    add(txt5);
    txt5.setBounds(210, 200, 150, 25);
    txt5.setEnabled(false);
    
    lbl6 = new JLabel("years old");
    lbl6.setFont(font);
    add(lbl6);
    lbl6.setBounds(380, 200, 150, 25);
    
    lbl7 = new JLabel("Date Of Birth: ");
    lbl7.setFont(font);
    add(lbl7);
    lbl7.setBounds(30,240,150,25);
    
    txt6 = new JTextField(10);
    add(txt6);
    txt6.setBounds(210, 240, 200, 25);
    txt6.setEnabled(false);
    
    lbl8 = new JLabel("format date: dd/mm/yyyy");
    lbl8.setFont(font);
    add(lbl8);
    lbl8.setBounds(210, 260, 250, 25);
    
    lbl9 = new JLabel("Address: ");
    lbl9.setFont(font);
    add(lbl9);
    lbl9.setBounds(30, 300, 150, 25);
    
    txta1 = new JTextArea(5,20);
    txta1.setFont(font);
    add(txta1);
    txta1.setBounds(210,300,300,100);
    
    lbl10 = new JLabel("State: ");
    lbl10.setFont(font);
    add(lbl10);
    lbl10.setBounds(30, 420, 150, 25);
    
    cbo1 = new JComboBox(state);
    add(cbo1);
    cbo1.setBounds(210, 420, 300, 25);
    
    lbl11 = new JLabel("Gender: ");
    lbl11.setFont(font);
    add(lbl11);
    lbl11.setBounds(30,460,150,25);
    
    rb1 = new JRadioButton("Male");
    add(rb1);
    rb1.setBounds(210,460,80,25);
    rb1.setEnabled(false);
    rb1.addItemListener(new ItemListener () {
      @Override
        public void itemStateChanged(ItemEvent e) {
            gender = rb1.getText();
        }
    });
    
        rb2 = new JRadioButton("Female");
        add(rb2);
        rb2.setBounds(310, 460, 80, 25);
    rb2.setEnabled(false);
        rb2.addItemListener(new ItemListener () {
         @Override
           public void itemStateChanged(ItemEvent e) {
               gender = rb2.getText();
           }
    });
        
        bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        
        lbl12 = new JLabel("Course Code: ");
        lbl12.setFont(font);
        add(lbl12);
        lbl12.setBounds(30, 500, 150, 25);
        
        cbo2 = new JComboBox(Ccode);
        add(cbo2);
        cbo2.setBounds(210, 500, 150, 25);
        
        lbl13 = new JLabel("Course Name: ");
        lbl13.setFont(font);
        add(lbl13);
        lbl13.setBounds(30, 540, 150, 25);
        
        cbo3 = new JComboBox(Cname);
        add(cbo3);
        cbo3.setBounds(210, 540, 330, 25);
        
        lbl14 = new JLabel("Department: ");
        lbl14.setFont(font);
        add(lbl14);
        lbl14.setBounds(30, 580, 150, 25);
        
        cbo4 = new JComboBox(Dept);
        add(cbo4);
        cbo4.setBounds(210, 580, 150, 25);
        
        lbl15 = new JLabel("Register date: ");
        lbl15.setFont(font);
        add(lbl15);
        lbl15.setBounds(30, 620, 150, 25);
        
        txt7 = new JTextField(10);
        add(txt7);
        txt7.setBounds(210, 620, 200, 25);
        
        lbl16 = new JLabel("format date: dd/mm/yyyy");
        lbl16.setFont(font);
        add(lbl16);
        lbl16.setBounds(210, 640, 250, 25);
        
        lbl17 = new JLabel("Reference Of Lecturer: ");
        lbl17.setFont(font);
        add(lbl17);
        lbl17.setBounds(30, 680, 280, 25);
        
        txt8 = new JTextField(10);
        add(txt8);
        txt8.setBounds(210, 680, 330, 25);
        
        btndelete = new JButton("DELETE");
        add(btndelete);
        btndelete.setBounds(30, 740, 100, 25);
        btndelete.addActionListener(this);
        
        btnupdate = new JButton("UPDATE");
        add(btnupdate);
        btnupdate.setBounds(140,740,100,25);
        btnupdate.addActionListener(this);
        
        btncreate = new JButton("INSERT");
        add(btncreate);
        btncreate.setBounds(250, 740, 100, 25);
        btncreate.addActionListener(this);
        
        btnclear = new JButton("CLEAR");
        add(btnclear);
        btnclear.setBounds(360, 740, 100, 25);
        btnclear.addActionListener(this);
        
        btnsearch = new JButton("SEARCH");
        add(btnsearch);
        btnsearch.setBounds(470,740,100,25);
        btnsearch.addActionListener(this);
        
        setPreferredSize(new Dimension (600,850));
    }        

 @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btncreate){
        try{ 
            //DB CONNECTION
           //1. load the driver
           Class.forName(driver);
           
           //2. Create a connection
           Connection conn = DriverManager.getConnection(url, username, password);
           
           //3. Create statement
           Statement stat = conn.createStatement();
           
        String gender;
        String name = txt1.getText();
        String icnum = txt2.getText();
        String email = txt3.getText();
        String phonenum = txt4.getText();
        String age;
        String DOB;
        String address = txta1.getText();
        String state = (String) cbo1.getSelectedItem();
        String coursecode = (String) cbo2.getSelectedItem();
        String coursename = (String) cbo3.getSelectedItem();
        String department = (String) cbo4.getSelectedItem();
        String registerDate = txt7.getText();
        String reference = txt8.getText();
           Long gend = Long.parseLong(txt2.getText());
           gend %=2;
           
    if (gend == 0) {
        rb1.setSelected(false);
        rb2.setSelected(true);
        gender="Female";
    } else {
        rb1.setSelected(true);
        rb2.setSelected(false);
        gender="Male";
    }
        int year = Integer.parseInt(txt2.getText().substring(0, 2));
        if(year >=0 && year <=23){
            year+=2000;
        }
        else{
            year+=1900;
        }
        int umur=2023-year;
        age=String.valueOf(umur);
        txt5.setText(age);
        int month = Integer.parseInt(txt2.getText().substring(2,4 ));
        int day =Integer.parseInt(txt2.getText().substring(4,6 ));
        DOB = day+"/"+month+"/"+year;
        txt6.setText(DOB);
           
           //4. Statement/ Result
            if(!name.equals("") && !icnum.equals("") && !email.equals("") &&     //if form not null
                        !phonenum.equals("") && !age.equals("")&& 
                        !DOB.equals("") && !address.equals("") && 
                        !state.equals("") && !coursecode.equals("") && 
                        !coursename.equals("") && !department.equals("")&&
                        !registerDate.equals("") && !reference.equals("")){
                    String selectQuery = 
                            "SELECT ic_stud FROM student_details WHERE ic_stud = ?";          //Check if ID exists in peserta table
                    PreparedStatement selectStatement = 
                            conn.prepareStatement(selectQuery);                      //Prepare SQL statement for execution
                    selectStatement.setString(1, icnum);
                    ResultSet resultSet = selectStatement.executeQuery();           //Execute the SQL query and retrieve the result set

                    if (resultSet.next()) {                                         //Check if there is a next row in the result set
                        JOptionPane.showMessageDialog
                        (null, "You have already registered.");                     //popup message
                    } else {
           String sql = "Insert Into student_details values ('"+name+"'," 
                        + "'"+icnum+"','"+email+"','"+phonenum+"','"+age+"',"
                   + "  '"+DOB+"','"+address+"','"+state+"','"+gender+"',"
                   + "  '"+coursecode+"','"+coursename+"','"+department+"',"
                   + "  '"+registerDate+"','"+reference+"')";
           
            stat.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Successfully Inserted!");
            conn.close();}   
                }
                else{
                    JOptionPane.showMessageDialog(null,"Please fill all the data"); //popup message
                }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Please insert all the required fields!");
        }
    }
    
    else if (e.getSource() == btnupdate){
        String gender;
        String name = txt1.getText();
        String icnum = txt2.getText();
        String email = txt3.getText();
        String phonenum = txt4.getText();
        String age ;
        String DOB ;
        String address = txta1.getText();
        String state = (String) cbo1.getSelectedItem();
        String coursecode = (String) cbo2.getSelectedItem();
        String coursename = (String) cbo3.getSelectedItem();
        String department = (String) cbo4.getSelectedItem();
        String registerdate = txt7.getText();
        String reference = txt8.getText();
    
        try{
             //DB CONNECTION
           //1. load the driver
           Class.forName(driver);
           
           //2. Create a connection
           Connection conn = DriverManager.getConnection(url, username, password);
           Long gend = Long.parseLong(txt2.getText());
           gend %=2;
           
    if (gend == 0) {
        rb1.setSelected(false);
        rb2.setSelected(true);
        gender="Female";
    } else {
        rb1.setSelected(true);
        rb2.setSelected(false);
        gender="Male";
    }
        int year = Integer.parseInt(txt2.getText().substring(0, 2));
        if(year >=0 && year <=23){
            year+=2000;
        }
        else{
            year+=1900;
        }
        int umur=2023-year;
        age=String.valueOf(umur);
        txt5.setText(age);
        int month = Integer.parseInt(txt2.getText().substring(2,4 ));
        int day =Integer.parseInt(txt2.getText().substring(4,6 ));
        DOB = day+"/"+month+"/"+year;
        txt6.setText(DOB);
           //3. Create statement
           Statement stat = conn.createStatement();
         String sql = "UPDATE student_details SET name='" + name + "', ic_stud='" + icnum + "', email='" + email + "', phone_no='" + phonenum + "', age='" + age + "', dob='" + DOB + "', " +
        "address='" + address + "', state='" + state + "', gender='" + gender + "', course_code='" + coursecode + "', " +
        "course_name='" + coursename + "', depart='" + department + "', reg_date='" + registerdate + "', " +
        "ref_lecturer='" + reference + "' WHERE ic_stud='" + icnum + "'";

            stat.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Successfully Updated!");
            
           conn.close();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Please insert all the required fields!");
        }
    }
    
    else if (e.getSource()== btndelete){
        try{
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stat = conn.createStatement();
            String input = JOptionPane.showInputDialog("Enter Your IC Number");
            String sql = "Delete from student_details where ic_stud='"+input+"'";
            stat.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Successfully Deleted!");
            
            conn.close();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR: "+ex.getMessage());
        }
    }

    else if(e.getSource()==btnclear){
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
        txt5.setText("");
        txt6.setText("");
        txta1.setText("");
        cbo1.setSelectedIndex(0);
        bg.clearSelection();
        cbo2.setSelectedIndex(0);
        cbo3.setSelectedIndex(0);
        cbo4.setSelectedIndex(0);
        txt7.setText("");
        txt8.setText("");
    }

  else{ 
      if (e.getSource()== btnsearch){
        login s = new login();
      }
    }
   }
  }  


  public static void main(String[]args) throws SQLException {
      Registrationstudent P = new Registrationstudent();
}
}
