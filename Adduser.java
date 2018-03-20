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
public class Adduser extends JFrame {
    public         String[]        droplistStr = {"Extrusion","Braid In","Braid Out","Wraping","Mandrel"};
    public static void main(String[] args) {

}
    
JButton bchange = new JButton("Add New User");
JPanel panel = new JPanel();
JComboBox comboBlacklist = new JComboBox(droplistStr);
JLabel labelUser = new JLabel("Username");
JLabel labelPass = new JLabel("Password");
JLabel labelComfirmPass = new JLabel("Comfirm Password");
JLabel labelProcess = new JLabel("Process");
JTextField txuser = new JTextField(20);
JPasswordField pass = new JPasswordField(20);
JPasswordField confirmpass = new JPasswordField(20);
JLabel labelname = new JLabel("Add New User");

Adduser(){
super("Add New User");
setSize(500,450);
setLocation(400,200);
panel.setLayout (null); 


txuser.setBounds(200,50,150,30);
confirmpass.setBounds(200,130,150,30);
labelComfirmPass.setBounds(20,130,150,30);
labelProcess.setBounds(20,170,150,30);
comboBlacklist.setBounds(200,170,150,30);
pass.setBounds(200,90,150,30);
bchange.setBounds(200,220,150,30);
labelUser.setBounds(20,50,150,30);
labelPass.setBounds(20,90,150,30);
labelname.setBounds(150,0,300,40);
labelname.setFont (labelname.getFont ().deriveFont (28.0f));

panel.add(txuser);
panel.add(pass);
panel.add(labelUser);
panel.add(labelPass);
panel.add(bchange);
panel.add(labelPass);
panel.add(labelname);
panel.add(labelComfirmPass);
panel.add(confirmpass);
panel.add(labelProcess);
panel.add(comboBlacklist);


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
             tmp++;
         }   
       if(tmp == 0){
        if(pass.getText().toString().equals(confirmpass.getText().toString())){
        st.executeUpdate("INSERT INTO user " + "VALUES ('"+txuser.getText().toString()+"', '"+pass.getText().toString()+"', 'user', '"+comboBlacklist.getSelectedItem().toString()+"')");
        JOptionPane.showMessageDialog(null,"เพิ่ม User เรียบร้อยแล้ว");
        dispose();
        }
        else JOptionPane.showMessageDialog(null,"กรุณาใส่ Password ให้ถูกต้อง !!");
        }
        else JOptionPane.showMessageDialog(null,"Username นี้มีในระบบแล้ว!!");
        
    } catch (SQLException ex) {
        Logger.getLogger(Adduser.class.getName()).log(Level.SEVERE, null, ex);
    }
}
});
}

}

