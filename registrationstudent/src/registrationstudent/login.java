package registrationstudent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login {
    JFrame f;
    JLabel lbl1, lbluname, lblpass;
    JTextField txtusername;
    JPasswordField txtpassword;
    JButton btnLogin, btnRegister;

    String username = "root";
    String password = "";
    String url = "jdbc:mysql://localhost:3306/psp?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String driver = "com.mysql.cj.jdbc.Driver";

    login() {
        f = new JFrame();
        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);
        
        Container contentPane = f.getContentPane();
        contentPane.setBackground(new Color(159, 108, 189));

        lbl1 = new JLabel("Admin Login");
        lbl1.setForeground(Color.black);
        lbl1.setFont(new Font("ARIAL", Font.BOLD, 40));
        lbl1.setBounds(100, 30, 300, 60);

        lbluname = new JLabel("Username :");
        lbluname.setBounds(20, 100, 80, 30);

        txtusername = new JTextField();
        txtusername.setBounds(120, 100, 200, 30);

        lblpass = new JLabel("Password :");
        lblpass.setBounds(20, 150, 80, 30);

        txtpassword = new JPasswordField();
        txtpassword.setBounds(120, 150, 200, 30);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(235, 240, 80, 25);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = txtusername.getText();
                char[] enteredPassword = txtpassword.getPassword();
                String enteredPasswordString = new String(enteredPassword);

                try {
                    Class.forName(driver);
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();

                    String query = "SELECT * FROM admin WHERE username = '" + enteredUsername + "' AND password = '" + enteredPasswordString + "'";
                    ResultSet resultSet = statement.executeQuery(query);

                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "Successful login");
                        Search s = new Search();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password");
                    }

                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnRegister = new JButton("Register");
        btnRegister.setBounds(120, 240, 100, 25);
        
        
         btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                register shw = new register();
                
            }
        });

        f.add(lbl1);
        f.add(lbluname);
        f.add(lblpass);
        f.add(txtusername);
        f.add(txtpassword);
        f.add(btnLogin);
        f.add(btnRegister);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new login();
            }
        });
    }
}
