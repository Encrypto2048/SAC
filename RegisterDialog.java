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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
public class RegisterDialog extends JFrame {
   private String[]  compoundstk = {"CR","Remil","A3","A4","A5","A6","A7","A8","X36","X38","X39","X40"};
   private String[]  str_DNA = {"8.9","9.2","9.3","9.6","9.8"};
   private String[]  str_DNB = {"10.2","10.4","10.6","11.3"};
   private String[]  str_DNC = {"12.2","12.5","12.6","12.7","12.8","13.5"};
   private String[]  str_DND = {"15.4","15.6","15.7","15.8","16.0","16.4"};
   private String[]  str_DNE = {"18.8","19.1","19.4"};
   private String[]  str_DNF = {"22.2","22.4","22.6","22.7","23.5"};
   private String[]  str_DNG = {"29.0","29.3","29.5","29.8","30.0","30.7","31.4"};
   private String[]  str_DNH = {"38.2","40.3"};
   private String[]  str_size = {"DN6","DN8","DN10","DN12","DN16","DN20","DN25","DN32"};
   private String    username;
   private String    time;
   private String    date;
   private String    expired_date_str;
   private String[]  dia01,dia02,dia03,dia04,dia05,dia06,dia07;
   ArrayList<String> dn01 = new ArrayList<>();
   ArrayList<String> dn02 = new ArrayList<>();
   ArrayList<String> dn03 = new ArrayList<>();
   ArrayList<String> dn04 = new ArrayList<>();
   ArrayList<String> dn05 = new ArrayList<>();
   ArrayList<String> dn06 = new ArrayList<>();
   ArrayList<String> dn07 = new ArrayList<>();
   ArrayList<String> dn08 = new ArrayList<>();
   
   
    public static void main(String[] args) {

}
JButton badd = new JButton("Register");
JButton badd_comp = new JButton("ADD Compound");
JButton bremov_comp = new JButton("Remove Compound");
JButton badd_diameter = new JButton("ADD Diameter");
JButton bremov_diameter = new JButton("Remove Diameter");
JPanel panel = new JPanel();
JLabel labellot_number = new JLabel("Lot no.");
JLabel labelsize = new JLabel("Size");
JLabel labeldiameter = new JLabel("Diameter(mm)");
JLabel labelcompound = new JLabel("Compound");
JLabel labellot_compound = new JLabel("Lot compound");
JLabel labeldrum_number = new JLabel("Drum no.");
JLabel labellength = new JLabel("Length(m)");
JLabel labelblock = new JLabel("Block");
JLabel labeloperator = new JLabel("Operator");
JLabel labelbrnumber = new JLabel("Braiding no.");
JLabel labelremark = new JLabel("Remark");
JLabel labelpcnumber = new JLabel("PC No."); // CR jednipat 28.11.2017
JLabel labelexpired_date = new JLabel("Expired Date : ");
JTextField lot_number = new JTextField(30);
JTextField size = new JTextField(30);
JTextField txt_compo = new JTextField(30);
JComboBox combodiameter = new JComboBox();
JComboBox combocompound = new JComboBox(compoundstk);
JComboBox combosqlcomp = new JComboBox();
JComboBox combo_comp = new JComboBox();
JComboBox combo_dia = new JComboBox();
JComboBox combo_group = new JComboBox(str_size);
JTextField dia = new JTextField();
JTextField lot_compound = new JTextField(30);
JTextField drum_number = new JTextField(30);
JTextField length = new JTextField(30);
JTextField block = new JTextField(30);
JTextField operator = new JTextField(30);
JTextField brnumber = new JTextField(30);
JTextField remark = new JTextField(30);
JTextField pcnumber = new JTextField(30); // CR jednipat 28.11.2017
JTextField expired_date = new JTextField(30);
JLabel labelname = new JLabel("Register");

RegisterDialog(String username_login){
super("Register");
setSize(800,500);
setLocation(400,200);
panel.setLayout (null); 
username = username_login;
labellot_number.setBounds(20,50, 150, 30);
labelbrnumber.setBounds(400,50, 100, 30);
labelsize.setBounds(20,90, 150, 30);
labelremark.setBounds(400,90, 100, 30);
labeldiameter.setBounds(20,130, 150, 30);
labelexpired_date.setBounds(400,130, 100, 30);
badd_comp.setBounds(400, 210, 150, 30);
bremov_comp.setBounds(400, 170, 150, 30);
labelcompound.setBounds(20,170, 150, 30);
labellot_compound.setBounds(20,210, 150, 30);
labeldrum_number.setBounds(20,250, 150, 30);
labellength.setBounds(20,290, 150, 30);
labelblock.setBounds(20,330, 150, 30);
labeloperator.setBounds(20, 370, 150, 30);
labelpcnumber.setBounds(400, 370, 150, 30);
lot_number.setBounds(200, 50, 150, 30);
brnumber.setBounds(500, 50, 150, 30);
size.setBounds(200,90, 150, 30);
remark.setBounds(500,90, 250, 30);
remark.setBounds(500,90, 250, 30);
combodiameter.setBounds(200,130, 150, 30);
expired_date.setBounds(500,130, 250, 30);
//combocompound.setBounds(200,170, 150, 30);
combo_comp.setBounds(200,170, 150, 30);
lot_compound.setBounds(200,210, 150, 30);
drum_number.setBounds(200,250, 150, 30);
length.setBounds(200,290, 150, 30);
block.setBounds(200,330, 150, 30);
operator.setBounds(200,370, 150, 30);
pcnumber.setBounds(500,370, 150, 30); // CR jednipat 28.11.2017
badd.setBounds(200, 410, 150, 30);
labelname.setBounds(150,0,300,40);
combosqlcomp.setBounds(560,170, 170, 30);
txt_compo.setBounds(560,210, 170, 30);
dia.setBounds(560, 310, 200, 30);
combo_group.setBounds(560, 270, 200, 30);
badd_diameter.setBounds(400, 270, 150, 30);
bremov_diameter.setBounds(400, 310, 150, 30);
DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
                    Calendar cal;
                    cal = Calendar.getInstance();  
                    int year = cal.get(Calendar.YEAR)-543;
                    cal.set(Calendar.YEAR,year);
                    time = dateFormat.format(cal.getTime());
                    date = dateFormat2.format(cal.getTime());
                    cal = Calendar.getInstance();
                    cal.add(Calendar.DATE,10);
                    year = cal.get(Calendar.YEAR)-543;
                    cal.set(Calendar.YEAR,year);
                    expired_date_str = dateFormat2.format(cal.getTime());

labelname.setFont (labelname.getFont ().deriveFont (28.0f));
size.setEditable(false);
expired_date.setText(expired_date_str);
expired_date.setEditable(false);
getDataCombo();
getDataDia();
lot_number.getDocument().addDocumentListener(new DocumentListener() {

    @Override
    public void insertUpdate(DocumentEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        test();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       // new testttt();
        test();
    }
});
block.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e) {
        String text = block.getText();
        System.out.println(text);
        block.setText(text);
    }
});

panel.add(labelname);
panel.add(badd);
panel.add(block);
//panel.add(bsim);
//panel.add(combocompound);
panel.add(combodiameter);
panel.add(drum_number);
panel.add(labelsize);
panel.add(labelblock);
panel.add(labelcompound);
panel.add(labeldiameter);
panel.add(labeldrum_number);
panel.add(labellength);
panel.add(labellot_compound);
panel.add(labellot_number);
panel.add(labeloperator);
panel.add(length);
panel.add(lot_compound);
panel.add(lot_number);
panel.add(size);
panel.add(operator);
panel.add(brnumber);
panel.add(remark);
panel.add(labelbrnumber);
panel.add(labelremark);
panel.add(labelexpired_date);
panel.add(labelpcnumber);
panel.add(pcnumber);
panel.add(expired_date);
panel.add(badd_comp);
panel.add(combosqlcomp);
panel.add(txt_compo);
panel.add(bremov_comp);
panel.add(combo_comp);
panel.add(badd_diameter);
panel.add(bremov_diameter);
panel.add(dia);
panel.add(combo_group);
getContentPane().add(panel);
//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
actionlogin();
}
public void actionlogin() {
badd.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
Connection conn = new connect_db().connect_db();

    try {
        Statement st;
        st = conn.createStatement();
        ResultSet rs3 = st.executeQuery("SELECT * FROM item WHERE lot_number = '"+lot_number.getText().toString()+"' ");
        int tmp = 0;
         while(rs3.next()) tmp++;   
       if(tmp == 0){
                   
		   PreparedStatement pre =
		   conn.prepareStatement("INSERT INTO item (lot_number,size,diameter,compound,lot_compound,drum_number,length,block,process,user,operator,date,time,brnumber,remark,expired_date,pcnumber) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); // CR jednipat 28.11.2017
		 
		   pre.setString(1,lot_number.getText().toString());
		   pre.setString(2,size.getText().toString());
                   pre.setString(3,combodiameter.getSelectedItem().toString());
                   pre.setString(4,combo_comp.getSelectedItem().toString());
                   pre.setString(5,lot_compound.getText().toString());
                   pre.setString(6,drum_number.getText().toString());
                   pre.setString(7,length.getText().toString());
                   pre.setString(8,block.getText().toString());
                   pre.setString(9,"Extrusion");
                   pre.setString(10,username);
                   pre.setString(11,operator.getText().toString());
                   pre.setString(12,date); // CR jednipat 28.11.2017
                   pre.setString(13,time); // CR jednipat 28.11.2017
                   pre.setString(14,brnumber.getText().toString());
                   pre.setString(15,remark.getText().toString());
                   pre.setString(16,expired_date.getText().toString());
                   pre.setString(17,pcnumber.getText().toString()); // CR jednipat 28.11.2017
		   
                  
                  // System.out.print(pre.toString());
                   pre.executeUpdate();
                  pre = conn.prepareStatement("INSERT INTO transaction1 (lot_number,size,diameter,compound,lot_compound,drum_number,length,block,process,user,operator,date,time,brnumber,remark,expired_date,pcnumber) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); // CR jednipat 28.11.2017
                  pre.setString(1,lot_number.getText().toString());
		   pre.setString(2,size.getText().toString());
                   pre.setString(3,combodiameter.getSelectedItem().toString());
                   pre.setString(4,combo_comp.getSelectedItem().toString());
                   pre.setString(5,lot_compound.getText().toString());
                   pre.setString(6,drum_number.getText().toString());
                   pre.setString(7,length.getText().toString());
                   pre.setString(8,block.getText().toString());
                   pre.setString(9,"Extrusion");
                   pre.setString(10,username);
                   pre.setString(11,operator.getText().toString());
                   pre.setString(12,date); // CR jednipat 28.11.2017
                   pre.setString(13,time); // CR jednipat 28.11.2017
                   pre.setString(14,brnumber.getText().toString());
                   pre.setString(15,remark.getText().toString());
                   pre.setString(16,expired_date.getText().toString());
                   pre.setString(17,pcnumber.getText().toString()); // CR jednipat 28.11.2017
                   pre.executeUpdate();
        JOptionPane.showMessageDialog(null,"บันทึกข้อมูลเรียบร้อยแล้ว");
       menu tmpmenu = Login.getInstance();
       tmpmenu.bcustomer.doClick();
       dispose();
        }
        else JOptionPane.showMessageDialog(null,"มีข้อมูล Lot no อยู่แล้ว");
        
    } catch (SQLException ex) {
        Logger.getLogger(RegisterDialog.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null,"กรุณาระบุข้อมูลให้ถูกต้อง");
    }



}
});
badd_comp.addActionListener(new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
        String compo_name = txt_compo.getText();
        String query = "insert into compound values ('"+compo_name+"')";
        try {
            Connection c = new connect_db().connect_db();
            Statement stmt = c.createStatement();
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Saved");
            getDataCombo();
        }catch(Exception ee){
            ee.printStackTrace();
        }
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
});
bremov_comp.addActionListener(new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
        String compo_name = combosqlcomp.getSelectedItem().toString();
        String query = "DELETE FROM compound WHERE comp_name =('"+compo_name+"')" ;
        try{
            Connection c = new connect_db().connect_db();
            Statement stmt = c.createStatement();
            stmt.executeUpdate(query);
            //getDataCombo();
            JOptionPane.showMessageDialog(null,"ลบ "+compo_name+" สำเร็จ");
        }catch(SQLException ee){
            Logger.getLogger(RegisterDialog.class.getName()).log(Level.SEVERE, null, ee);
            JOptionPane.showMessageDialog(null,"ไม่สามารถเชื่อมต่อฐานข้อมูล");
        }
        
    }
});
bremov_diameter.addActionListener(new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
        String dia_value = combo_group.getSelectedItem().toString();
        String diaString = dia.getText();
        String query = "DELETE FROM "+dia_value+" WHERE size =('"+diaString+"')" ;
        try{
            Connection c = new connect_db().connect_db();
            Statement stmt = c.createStatement();
            stmt.executeUpdate(query);
            //getDataCombo();
            JOptionPane.showMessageDialog(null,"ลบ "+diaString+" สำเร็จ");
        }catch(SQLException ee){
            Logger.getLogger(RegisterDialog.class.getName()).log(Level.SEVERE, null, ee);
            JOptionPane.showMessageDialog(null,"ไม่สามารถเชื่อมต่อฐานข้อมูล");
        }
    }
});
badd_diameter.addActionListener(new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
        String dia_group = combo_group.getSelectedItem().toString();
        String dia_name = dia.getText();
        String query = "insert into "+dia_group+" values ('"+dia_name+"')";
        try {
            Connection c = new connect_db().connect_db();
            Statement stmt = c.createStatement();
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Saved");
            getDataDia();
           
        }catch(Exception ee){
            Logger.getLogger(RegisterDialog.class.getName()).log(Level.SEVERE, null, ee);
            JOptionPane.showMessageDialog(null,"ไม่สามารถเชื่อมต่อฐานข้อมูล");
        }
    }
});
/*
bsim.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
    String tmp = String.valueOf(lot_number.getText().toString().charAt(0));
    tmp = tmp.toLowerCase();
    switch(tmp){
        case  "a": size.setText(str_size[0]); combodiameter.setModel(new DefaultComboBoxModel(str_DNA)); break;
        case  "b": size.setText(str_size[1]); combodiameter.setModel(new DefaultComboBoxModel(str_DNB)); break;
        case  "c": size.setText(str_size[2]); combodiameter.setModel(new DefaultComboBoxModel(str_DNC)); break;
        case  "d": size.setText(str_size[3]); combodiameter.setModel(new DefaultComboBoxModel(str_DND)); break;
        case  "e": size.setText(str_size[4]); combodiameter.setModel(new DefaultComboBoxModel(str_DNE)); break;
        case  "f": size.setText(str_size[5]); combodiameter.setModel(new DefaultComboBoxModel(str_DNF)); break;
        case  "g": size.setText(str_size[6]); combodiameter.setModel(new DefaultComboBoxModel(str_DNG)); break;
        case  "h": size.setText(str_size[7]); combodiameter.setModel(new DefaultComboBoxModel(str_DNH)); break;
    }
}
});
*/
/*badd_comp.addActionListener(new ActionListener(){
   public void actionPerformed(ActionEvent ad){
    Connection conn = new connect_db().connect_db();
    try{
        Statement st;
        st = conn.createStatement();
        ResultSet rs4 = st.executeQuery("SELECT * FROM compound WHERE comp_name");
        while(rs4.next()){
            String comp_name = rs4.getString("comp_name");
        }
    
    }catch (SQLException ex) {
        Logger.getLogger(RegisterDialog.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null,"กรุณาระบุข้อมูลให้ถูกต้อง");
    }
}
});*/

}
public void getDataCombo(){
   Connection conn = new connect_db().connect_db();
   //System.out.println("ddddd");
    try{
        Statement st;
        st = conn.createStatement();
        ResultSet rs4 = st.executeQuery("SELECT * FROM producttracker.compound");
        while(rs4.next()){
            combosqlcomp.addItem(rs4.getString(1));
            combo_comp.addItem(rs4.getString(1));

        }
    
    }catch (SQLException ex) {
        Logger.getLogger(RegisterDialog.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null,"ไม่สามารถติดต่อฐานข้อมูล");
    }
}
public void getDataDia(){
   Connection conn = new connect_db().connect_db();
   //System.out.println("ddddd");
    try{
        Statement st,st2,st3,st4,st5,st6,st7,st8;
        st = conn.createStatement();
        st2 = conn.createStatement();
        st3 = conn.createStatement();
        st4 = conn.createStatement();
        st5 = conn.createStatement();
        st6 = conn.createStatement();
        st7 = conn.createStatement();
        st8 = conn.createStatement();
        ResultSet rs1 = st.executeQuery("SELECT * FROM producttracker.dn6");
        ResultSet rs2 = st2.executeQuery("SELECT * FROM producttracker.dn8");
        ResultSet rs3 = st3.executeQuery("SELECT * FROM producttracker.dn10");
        ResultSet rs4 = st4.executeQuery("SELECT * FROM producttracker.dn12");
        ResultSet rs5 = st5.executeQuery("SELECT * FROM producttracker.dn16");
        ResultSet rs6 = st6.executeQuery("SELECT * FROM producttracker.dn20");
        ResultSet rs7 = st7.executeQuery("SELECT * FROM producttracker.dn25");
        ResultSet rs8 = st8.executeQuery("SELECT * FROM producttracker.dn32");
        while(rs1.next()){
            dn01.add(rs1.getString(1));
        }
        while(rs2.next()){
            dn02.add(rs2.getString(1));
        }
        while(rs3.next()){
            dn03.add(rs3.getString(1));
        }
        while(rs4.next()){
            dn04.add(rs4.getString(1));
        }
        while(rs5.next()){
            dn05.add(rs5.getString(1));
        }
        while(rs6.next()){
            dn06.add(rs6.getString(1));
        }
        while(rs7.next()){
            dn07.add(rs7.getString(1));
        }
        while(rs8.next()){
            dn08.add(rs8.getString(1));
        }
    }catch (SQLException ex) {
        Logger.getLogger(RegisterDialog.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null,"บันทึกไม่สำเร็จ<>");
    }
}

public void test(){
    String[] d01 = dn01.toArray(new String[0]);
    String[] d02 = dn02.toArray(new String[0]);
    String[] d03 = dn03.toArray(new String[0]);
    String[] d04 = dn04.toArray(new String[0]);
    String[] d05 = dn05.toArray(new String[0]);
    String[] d06 = dn06.toArray(new String[0]);
    String[] d07 = dn07.toArray(new String[0]);
    String[] d08 = dn08.toArray(new String[0]);
    String tmp = String.valueOf(lot_number.getText().toString().charAt(0));
    tmp = tmp.toLowerCase();
    switch(tmp){
        case  "a": size.setText(str_size[0]); combodiameter.setModel(new DefaultComboBoxModel(d01)); break;
        case  "b": size.setText(str_size[1]); combodiameter.setModel(new DefaultComboBoxModel(d02)); break;
        case  "c": size.setText(str_size[2]); combodiameter.setModel(new DefaultComboBoxModel(d03)); break;
        case  "d": size.setText(str_size[3]); combodiameter.setModel(new DefaultComboBoxModel(d04)); break;
        case  "e": size.setText(str_size[4]); combodiameter.setModel(new DefaultComboBoxModel(d05)); break;
        case  "f": size.setText(str_size[5]); combodiameter.setModel(new DefaultComboBoxModel(d06)); break;
        case  "g": size.setText(str_size[6]); combodiameter.setModel(new DefaultComboBoxModel(d07)); break;
        case  "h": size.setText(str_size[7]); combodiameter.setModel(new DefaultComboBoxModel(d08)); break;
    }
}

}