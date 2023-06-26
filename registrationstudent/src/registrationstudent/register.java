
package registrationstudent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
public class register {
    
    String username = "root";
    String password = "";
    String url = "jdbc:mysql://localhost:3306/psp?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String driver = "com.mysql.cj.jdbc.Driver";
    
    JFrame f;
    JLabel lbl1, lbluname, lblpass,lblmail;
    JTextField txtusername,txtmail;
    JPasswordField txtpassword;
    JButton btnRegister;
    
    register(){
        f = new JFrame();
        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);
        
        Container contentPane = f.getContentPane();
        contentPane.setBackground(new Color(159, 108, 189));
        String dbcreate ="jdbc:mysql://localhost:3306?zeroDateTimeBehavior=CONVERT_TO_NULL";
    try{
            Class.forName(driver); // Load the JDBC driver
    Connection kon = DriverManager.getConnection(dbcreate, username, password); // Establish a database connection
    Statement stm = kon.createStatement(); // Create a Statement object
    
    String createDBQuery = "CREATE DATABASE IF NOT EXISTS psp"; // SQL statement to create database
    stm.executeUpdate(createDBQuery); // Execute SQL statement

    String useDBQuery = "USE psp"; // Switch to the psp database
    stm.execute(useDBQuery); // Execute SQL statement

    String createTableQuery = "CREATE TABLE IF NOT EXISTS admin (" // Create the student table
            + "username VARCHAR(200), "
            + "password VARCHAR(200), "
            + "email VARCHAR(200), ";
    stm.executeUpdate(createTableQuery); // Execute SQL statement
} catch (ClassNotFoundException | SQLException e) {
    JOptionPane.showMessageDialog(null, "Failed to create database and table: " + e.getMessage());
}

        lbl1 = new JLabel("Admin Registration");
        lbl1.setForeground(Color.black);
        lbl1.setFont(new Font("ARIAL", Font.BOLD, 20));
        lbl1.setBounds(100, 30, 300, 60);

        lbluname = new JLabel("Username :");
        lbluname.setBounds(20, 100, 80, 30);

        txtusername = new JTextField();
        txtusername.setBounds(120, 100, 200, 30);

        lblpass = new JLabel("Password :");
        lblpass.setBounds(20, 150, 80, 30);

        txtpassword = new JPasswordField();
        txtpassword.setBounds(120, 150, 200, 30);
        
        lblmail = new JLabel("Email :");
        lblmail.setBounds(20,200,80,30);
        
        txtmail = new JTextField();
        txtmail.setBounds(120,200,200,30);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(235, 240, 80, 25);
        
        
        btnRegister.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        if (!txtusername.getText().isEmpty() && !txtpassword.getPassword().equals("") && !txtmail.getText().isEmpty()) {
            try {
                // DB CONNECTION
                // 1. Load the driver
                Class.forName(driver);

                // 2. Create a connection
                Connection conn = DriverManager.getConnection(url, username, password);

                // 3. Create a statement
                Statement stat = conn.createStatement();

                String selectQuery = "SELECT username FROM admin WHERE username = ?";
                PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
                selectStatement.setString(1, txtusername.getText());
                ResultSet resultSet = selectStatement.executeQuery();

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "You have already registered.");
                } else {
                    String sql = "INSERT INTO admin (username, password, email) VALUES (?, ?, ?)";
                    PreparedStatement insertStatement = conn.prepareStatement(sql);
                    insertStatement.setString(1, txtusername.getText());
                    insertStatement.setString(2, new String(txtpassword.getPassword()));
                    insertStatement.setString(3, txtmail.getText());
                    insertStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data successfully inserted!");
                }

                resultSet.close();
                selectStatement.close();
                stat.close();
                conn.close();
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error occurred while inserting data." + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please fill all the fields.");
        }
    }
});

f.add(lbl1);
f.add(lbluname);
f.add(lblpass);
f.add(txtusername);
f.add(txtpassword);
f.add(lblmail);
f.add(txtmail);
f.add(btnRegister);

        
    }
    
    
    
       public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new register();
            }
        });
    }
    
}
