
package processchecker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends JFrame {
private String username_login;
private String role_login;
private String process_login;
static private menu menu_call;

public static void main(String[] args) {

}

JButton blogin = new JButton("Login");
JButton bchange = new JButton("Change Password");
JButton badduser = new JButton("Add New User");
JPanel panel = new JPanel();
JLabel labelUser = new JLabel("Username");
JLabel labelPass = new JLabel("Password");
JLabel labelname = new JLabel("Semperflex Asia Corp.");
JTextField txuser = new JTextField(20);
JPasswordField pass = new JPasswordField(20);

Login(){ 
super(" Semperflex Asia Corp., Ltd. ");
setSize(1350,600);
setLocation(0,0);
panel.setLayout (null); 


txuser.setBounds(500,200,150,30);
pass.setBounds(500,235,150,30);
blogin.setBounds(500,270,150,30);
bchange.setBounds(800,30,150,30);
badduser.setBounds(1000,30,150,30);
labelUser.setBounds(420,200,150,30);
labelPass.setBounds(420,235,150,30);
labelname.setBounds(390,150,400,30);
labelname.setFont (labelname.getFont ().deriveFont (32.0f));

panel.add(blogin);
panel.add(txuser);
panel.add(pass);
panel.add(labelUser);
panel.add(labelPass);
panel.add(labelname);
panel.add(badduser);
panel.add(bchange);

getContentPane().add(panel);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
actionlogin();
}

public void actionlogin(){
blogin.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
Connection conn = new connect_db().connect_db();
 try {
        Statement st;
        st = conn.createStatement();
        ResultSet rs3 = st.executeQuery("SELECT * FROM user WHERE username = '"+txuser.getText().toString()+"' "
                + "AND password = '"+pass.getText().toString()+"' ");
        int tmp = 0;
         while(rs3.next()) {
            username_login =  rs3.getString("username");
            role_login = rs3.getString("role");
            process_login = rs3.getString("process");
             tmp++;
         }   
       if(tmp != 0){
        menu_call = new menu(username_login,role_login,process_login);
        menu_call.setVisible(true);
        dispose();
        }
        else JOptionPane.showMessageDialog(null,"Username/Password ไม่ถูกต้อง!!");
        
    } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }
}
});
bchange.addActionListener(new ActionListener() {   
    @Override
    public void actionPerformed(ActionEvent ae) {
       new ChangePasswordDialog();
    }
});

badduser.addActionListener(new ActionListener() {   
    @Override
    public void actionPerformed(ActionEvent ae) {
       new Adduser();
    }
});
}

   static public menu getInstance() { return menu_call; }

}