/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class ChangePasswordDialog extends JFrame {
    
    private String oldpassword_db;
    public static void main(String[] args) {

}

JButton bchange = new JButton("Change Password");
JPanel panel = new JPanel();
JLabel labelUser = new JLabel("Username");
JLabel labelPass = new JLabel("Old Password");
JLabel labelNewPass = new JLabel("New Password");
JLabel labelNewComfirmPass = new JLabel("Comfirm New Password");
JTextField txuser = new JTextField(20);
JPasswordField oldpass = new JPasswordField(20);
JPasswordField newpass = new JPasswordField(20);
JPasswordField newconfirmpass = new JPasswordField(20);
JLabel labelname = new JLabel("Change Password");

ChangePasswordDialog(){
super("Change Password");
setSize(500,350);
setLocation(400,200);
panel.setLayout (null); 


txuser.setBounds(200,50,150,30);
oldpass.setBounds(200,90,150,30);
newpass.setBounds(200,130,150,30);
newconfirmpass.setBounds(200,170,150,30);
bchange.setBounds(200,230,150,30);
labelUser.setBounds(20,50,150,30);
labelPass.setBounds(20,90,150,30);
labelNewPass.setBounds(20,130,150,30);
labelNewComfirmPass.setBounds(20,170,150,30);
labelname.setBounds(100,0,300,40);
labelname.setFont (labelname.getFont ().deriveFont (28.0f));

panel.add(txuser);
panel.add(newpass);
panel.add(labelUser);
panel.add(labelPass);
panel.add(bchange);
panel.add(oldpass);
panel.add(newconfirmpass);
panel.add(labelNewPass);
panel.add(labelNewComfirmPass);
panel.add(labelname);


getContentPane().add(panel);
//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
actionlogin();
}

public void actionlogin(){
bchange.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {

Connection conn = new connect_db().connect_db();
 try {
        Statement st;
        st = conn.createStatement();
        ResultSet rs3 = st.executeQuery("SELECT * FROM user WHERE username = '"+txuser.getText().toString()+"' ");
        int tmp = 0;
         while(rs3.next()) {
             oldpassword_db = rs3.getString("password");
             tmp++;
         }   
       if(tmp != 0){
        if(newpass.getText().toString().equals(newconfirmpass.getText().toString()) && oldpass.getText().toString().equals(oldpassword_db)){
        st.executeUpdate("UPDATE user SET password ='"+newpass.getText().toString()+"' WHERE username = '"+txuser.getText().toString()+"' ");
        JOptionPane.showMessageDialog(null,"แก้ไขรหัสเรียบร้อยแล้ว");
        dispose();
        }
        else JOptionPane.showMessageDialog(null,"กรุณาใส่ข้อมูล Password ให้ถูกต้อง !!");
        }
        else JOptionPane.showMessageDialog(null,"Username นี้ไม่มีในระบบ");
        
    } catch (SQLException ex) {
        Logger.getLogger(ChangePasswordDialog.class.getName()).log(Level.SEVERE, null, ex);
    }
}
});
}

}
