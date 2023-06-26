
package registrationstudent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Search {
    JTextField txtSearch;
    JTable table1;
    String[] column = {"name","ic_stud","email","phone_no","age","dob","address","state","gender","course_code","course_name","depart","reg_date","ref_lecturer","where_ic_stud"};
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/psp?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String username = "root";
    String password = "";
    
    Connection conn;
    
    Search(){
        JFrame mainFrame = new JFrame("Details");
        mainFrame.setLayout(null);
        
        JLabel lblsearch = new JLabel("Search by IC Student");
        txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Search");
        JButton btnBack = new JButton("Back");
        table1 = new JTable();
        mainFrame.add(lblsearch);
        lblsearch.setBounds(30, 30, 180, 40);
        mainFrame.add(txtSearch);
        txtSearch.setBounds(180, 30, 180, 30);
        mainFrame.add(btnSearch);
        btnSearch.setBounds(380,30,80,30);
        
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setBounds(30,80,1800,250);
                
                mainFrame.add(scrollPane);
                
                UpdateDb udp = new UpdateDb();
                table1 = new JTable(udp.updatedb(),column);
                scrollPane.setViewportView(table1);
            }
        });
        
        mainFrame.setSize(1900,700);
        mainFrame.setResizable(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }
    
    private class UpdateDb {
        public Object[][] updatedb(){
            String [][] data = new String [15][14];
            String search = txtSearch.getText();
            try{
                    Class.forName(driver);
                    Connection conn = DriverManager.getConnection(url, username, password);
                    Statement stat = conn.createStatement();
                    String sql = "SELECT * FROM student_details WHERE ic_stud = '"+
                         search + "'";   
                    ResultSet rs = stat.executeQuery(sql);
                    
                    int i=0;
                    
                    while(rs.next()) {
                        for (int j=0; j<14; j++){
                            data[i] [j] = rs.getString(j+1);
                        }
                        i=i+1;
                    }
                    conn.close();
                    
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ERROR: " +ex.getMessage());
            }
            return data;
        }
    }
    
public static void main (String[] args) {
        Search sd = new Search();
    }
}
