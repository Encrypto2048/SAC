/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processchecker;

import com.mysql.jdbc.PacketTooBigException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
public class CheckinDialog extends JFrame {
   private String    username;
   private String    process;
   private String 	time;
   private String 	date;
   private String[]  droplistStr = {"Extrusion","Braid In","Braid Out","Wraping","Mandrel"};
   private int curr_process_index,process_index;
   
    public static void main(String[] args) {

}
JButton bsim = new JButton("Check in Item");
JButton bbraidadmin = new JButton("Braid admin Item");
JPanel panel = new JPanel();
JLabel labellot_number = new JLabel("Lot no.");
JLabel labeloperator = new JLabel("Operator");
JLabel labellength = new JLabel("Length");
JTextField lot_number = new JTextField(30);
JTextField operator = new JTextField(30);
JTextField newlength = new JTextField(30);
JLabel labelname = new JLabel("Check in");

CheckinDialog(String username_login,String process_login){
super("Check in");
setSize(700,250);
setLocation(400,200);
panel.setLayout (null); 
username = username_login;
process = process_login;
labellot_number.setBounds(20,50, 150, 30);
lot_number.setBounds(200, 50, 150, 30);
labellength.setBounds(20,90, 150, 30);
labeloperator.setBounds(20, 130, 150, 30);
operator.setBounds(200, 130, 150, 30);
newlength.setBounds(200, 90, 150, 30);
bsim.setBounds(400, 90, 150, 30);
bbraidadmin.setBounds(400, 130, 150, 30);
labelname.setBounds(150,0,300,40);
labelname.setFont (labelname.getFont ().deriveFont (28.0f));

panel.add(labelname);
panel.add(bsim);
panel.add(bbraidadmin);
panel.add(labellot_number);
panel.add(labeloperator);
panel.add(operator  );
panel.add(lot_number);
panel.add(labellength);
panel.add(newlength);
getContentPane().add(panel);

//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
actionlogin();
}

public void actionlogin() {

bsim.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
    

Connection conn = new connect_db().connect_db();
 try {
        Statement st;
        st = conn.createStatement();
        ResultSet rs3 = st.executeQuery("SELECT * FROM item WHERE lot_number = '"+lot_number.getText().toString()+"' ");
        String size = "",diameter = "",compound = "",lot_compound = "",drum_number = "",length = "",block = "";
        String curr_process = ""; // CR added by jednipat 28.11.2017 
        int tmp = 0;
         while(rs3.next()) {
             //rs3.getString("lot_number");
            size = rs3.getString("size");
            diameter =  rs3.getString("diameter");
            compound = rs3.getString("compound");
            lot_compound = rs3.getString("lot_compound");
            drum_number = rs3.getString("drum_number");
            length = rs3.getString("length");
            block = rs3.getString("block");
            curr_process = rs3.getString("process"); // CR added by jednipat 28.11.2017 
             //rs3.getString("process");
             //rs3.getString("user");
             //rs3.getString("operator");
             //rs3.getString("date_time");
             tmp++;
          // CR start added by jednipat 28.11.2017
             
             for(int i=0;i< droplistStr.length;i++) {
            	 if(curr_process.equals("Braid admin")) curr_process_index = 1; // same process with index 1 = Braid In
            	 else if(curr_process.equals(droplistStr[i])) curr_process_index = i;
            	 if(process.equals(droplistStr[i])) process_index = i;
             }
             if(curr_process_index != process_index && process_index - curr_process_index != 1) tmp = -1; //Check skip process
          // CR end added by jednipat 28.11.2017
         }   
       if(tmp > 0){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); // CR jednipat 28.11.2017
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd"); // CR jednipat 28.11.2017
        Calendar cal;
        cal = Calendar.getInstance();  
        int year = cal.get(Calendar.YEAR)-543;
        cal.set(Calendar.YEAR,year);         
        date = dateFormat2.format(cal.getTime());
        time = dateFormat.format(cal.getTime());
        st.executeUpdate("UPDATE item SET process ='"+process+"', user = '"+username+"', date ='"+date+"', time ='"+time+"', operator ='"+operator.getText().toString()+"', length ='"+newlength.getText().toString()+"' WHERE lot_number = '"+lot_number.getText().toString()+"' ");
        PreparedStatement pre = conn.prepareStatement("INSERT INTO transaction1 (lot_number,size,diameter,compound,lot_compound,drum_number,length,block,process,user,operator,date,time) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                  pre.setString(1,lot_number.getText().toString());
		   pre.setString(2,size);
                   pre.setString(3,diameter);
                   pre.setString(4,compound);
                   pre.setString(5,lot_compound);
                   pre.setString(6,drum_number);
                   pre.setString(7,newlength.getText().toString());
                   pre.setString(8,block);
                   pre.setString(9,process);
                   pre.setString(10,username);
                   pre.setString(11,operator.getText().toString());
                   pre.setString(12,date);
                   pre.setString(13,time);
                   pre.executeUpdate();
        JOptionPane.showMessageDialog(null,"Check in itemเรียบร้อยแล้ว");
        menu tmpmenu = Login.getInstance();
        tmpmenu.bcustomer.doClick();
        dispose();
        }
       else if(tmp == -1) JOptionPane.showMessageDialog(null,"ไม่สามารถ  Check in ข้าม processได้","Error",JOptionPane.ERROR_MESSAGE); // CR added by jednipat 28.11.2017
       else JOptionPane.showMessageDialog(null,"Lot no นี้ไม่มีในระบบ","Error",JOptionPane.ERROR_MESSAGE);
        
    } catch (SQLException ex) {
        Logger.getLogger(CheckinDialog.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
});
bbraidadmin.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
    

Connection conn = new connect_db().connect_db();
 try {
        Statement st;
        st = conn.createStatement();
        ResultSet rs3 = st.executeQuery("SELECT * FROM item WHERE lot_number = '"+lot_number.getText().toString()+"' ");
        String size = "",diameter = "",compound = "",lot_compound = "",drum_number = "",length = "",block = "";
        int tmp = 0;
        String tmp_process = "Braid admin";
         while(rs3.next()) {
             //rs3.getString("lot_number");
            size = rs3.getString("size");
            diameter =  rs3.getString("diameter");
            compound = rs3.getString("compound");
            lot_compound = rs3.getString("lot_compound");
            drum_number = rs3.getString("drum_number");
            length = rs3.getString("length");
            block = rs3.getString("block");
             //rs3.getString("process");
             //rs3.getString("user");
             //rs3.getString("operator");
             //rs3.getString("date_time");
             tmp++;
         }   
       if(tmp != 0){
    	   DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); // CR jednipat 28.11.2017
           DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd"); // CR jednipat 28.11.2017
        Calendar cal;
        cal = Calendar.getInstance();  
        int year = cal.get(Calendar.YEAR)-543;
        
        cal.set(Calendar.YEAR,year);         
        date = dateFormat2.format(cal.getTime());
        time = dateFormat.format(cal.getTime());
        st.executeUpdate("UPDATE item SET process ='"+tmp_process+"', user = '"+username+"', date ='"+date+"', time ='"+time+"', operator ='"+operator.getText().toString()+"', length ='"+newlength.getText().toString()+"' WHERE lot_number = '"+lot_number.getText().toString()+"' ");
        PreparedStatement pre = conn.prepareStatement("INSERT INTO transaction1 (lot_number,size,diameter,compound,lot_compound,drum_number,length,block,process,user,operator,date,time) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                  pre.setString(1,lot_number.getText().toString());
		   pre.setString(2,size);
                   pre.setString(3,diameter);
                   pre.setString(4,compound);
                   pre.setString(5,lot_compound);
                   pre.setString(6,drum_number);
                   pre.setString(7,newlength.getText().toString());
                   pre.setString(8,block);
                   pre.setString(9,tmp_process);
                   pre.setString(10,username);
                   pre.setString(11,operator.getText().toString());
                   pre.setString(12,date);
                   pre.setString(13,time);
                   pre.executeUpdate();
        JOptionPane.showMessageDialog(null,"Braid admin Itemเรียบร้อยแล้ว");
        menu tmpmenu = Login.getInstance();
        tmpmenu.bcustomer.doClick();
        dispose();
        }
        else JOptionPane.showMessageDialog(null,"Lot no นี้ไม่มีในระบบ","Error",JOptionPane.ERROR_MESSAGE);
        
    } catch (SQLException ex) {
        Logger.getLogger(CheckinDialog.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
});

}

}
