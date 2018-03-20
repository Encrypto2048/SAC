package processchecker;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

public class UpdateDataDatabase extends JFrame {
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
	   private String[]  droplistStr = {"Extrusion","Braid In","Braid admin","Braid Out","Wraping","Mandrel"};
	   private String    username;
	   private String    time;
	   private String    date;
	   private String    dbname;
	   private String    field;
	   private String    expired_date_str;
	   private String Slot_number;
	   private String Ssize;
	    private String Sdia;
	    private String Scompound;
	    private String Slot_compound;
	    private String Sdrum_number;
	    private String Slength;
	    private String Sblock;
	    private String Sprocess;
	    private String Soperator;
	    private String Sbrnumber;
	    private String Sremark;
	    private String Sexpired_date;
	    private String Spcnumber; // CR jednipat 28.11.2017	 
	   private String[]  dia01,dia02,dia03,dia04,dia05,dia06,dia07;
	   ArrayList<String> dn01 = new ArrayList<>();
	   ArrayList<String> dn02 = new ArrayList<>();
	   ArrayList<String> dn03 = new ArrayList<>();
	   ArrayList<String> dn04 = new ArrayList<>();
	   ArrayList<String> dn05 = new ArrayList<>();
	   ArrayList<String> dn06 = new ArrayList<>();
	   ArrayList<String> dn07 = new ArrayList<>();
	   ArrayList<String> dn08 = new ArrayList<>();
	   JComboBox comboBlacklist = new JComboBox(droplistStr);
	   JPanel panel = new JPanel();
	    JButton bupdate = new JButton("Update data to Database");
	    JLabel labellot_number = new JLabel("Lot no.");
	    JLabel labelsize = new JLabel("Size");
	    JLabel labeldiameter = new JLabel("Diameter(mm)");
	    JLabel labelcompound = new JLabel("Compound");
	    JLabel labellot_compound = new JLabel("Lot compound");
	    JLabel labeldrum_number = new JLabel("Drum no.");
	    JLabel labellength = new JLabel("Length(m)");
	    JLabel labelblock = new JLabel("Block");
	    JLabel labelprocess = new JLabel("Process");
	    JLabel labeloperator = new JLabel("Operator");
	    JLabel labelbrnumber = new JLabel("Braiding no.");
	    JLabel labelremark = new JLabel("Remark");
	    JLabel labelexpired_date = new JLabel("Expired Date : ");
	    JLabel labelpcnumber = new JLabel("PC No."); 
	    JTextField lot_number = new JTextField();
	    JTextField size = new JTextField();
	    JTextField dia = new JTextField();
	    JTextField compound = new JTextField(30);
	    JTextField lot_compound = new JTextField(30);
	    JTextField drum_number = new JTextField(30);
	    JTextField length = new JTextField(30);
	    JTextField block = new JTextField(30);
	    JTextField process = new JTextField(30);
	    JTextField operator = new JTextField(30);
	    JTextField brnumber = new JTextField(30);
	    JTextField remark = new JTextField(30);
	    JTextField expired_date = new JTextField(30);
	    JTextField pcnumber = new JTextField(30); // CR jednipat 28.11.2017

    public static void main(String[] args) {
    }
    
    
    
    UpdateDataDatabase(String db_name_1,String field_1,String lot_number_1,String size_1,String diameter_1,String compound_1,String lot_compound_1,String drum_number_1,String length_1,String block_1,String process_1,String user_1,String Operator_1
  		  ,String date_1,String time_1,String brnumber_1,String remark_1,String expired_date_1,String pcnumber_1)
    {
    	username = user_1;
    	dbname = db_name_1;
    	field = field_1;
    	setSize(800,500);
    	setLocation(400,200);
    	panel.setLayout (null);
    	labellot_number.setBounds(20,50, 150, 30);
    	labeloperator.setBounds(400,50, 150, 30);
    	labelbrnumber.setBounds(400,90, 100, 30);
    	labelremark.setBounds(400,130, 100, 30);
    	labelexpired_date.setBounds(400,170, 100, 30);
    	labelpcnumber.setBounds(400, 210, 150, 30);
    	labelsize.setBounds(20,90, 150, 30);
    	labeldiameter.setBounds(20,130, 150, 30);
    	labelcompound.setBounds(20,170, 150, 30);
    	labellot_compound.setBounds(20,210, 150, 30);
    	labeldrum_number.setBounds(20,250, 150, 30);
    	labellength.setBounds(20,290, 150, 30);
    	labelblock.setBounds(20,330, 150, 30);
    	labelprocess.setBounds(20, 370, 150, 30);
    	
    	lot_number.setBounds(200, 50, 150, 30);
    	
    	size.setBounds(200,90, 150, 30);
    	
    	compound.setBounds(200,170, 150, 30);
    	lot_compound.setBounds(200,210, 150, 30);
    	drum_number.setBounds(200,250, 150, 30);
    	length.setBounds(200,290, 150, 30);
    	block.setBounds(200,330, 150, 30);
    	comboBlacklist.setBounds(200,370, 150, 30);
    	
    	dia.setBounds(200,130, 150, 30);
    	operator.setBounds(500,50, 250, 30);
    	brnumber.setBounds(500, 90, 250, 30);
    	remark.setBounds(500,130, 250, 30);
    	expired_date.setBounds(500,170, 250, 30);
    	pcnumber.setBounds(500,210, 250, 30); 
    	bupdate.setBounds(450,300, 250, 100);
    	lot_number.setEditable(false);
    	
    	lot_number.setText(lot_number_1);
    	size.setText(size_1);
    	dia.setText(diameter_1);
    	compound.setText(compound_1);
    	lot_compound.setText(lot_compound_1);
    	drum_number.setText(drum_number_1);
    	length.setText(length_1);
    	block.setText(block_1);
    	operator.setText(Operator_1);
    	brnumber.setText(brnumber_1);
    	remark.setText(remark_1);
    	expired_date.setText(expired_date_1);
    	pcnumber.setText(pcnumber_1);
    	comboBlacklist.setSelectedItem(process_1);
    	panel.add(block);
    	//panel.add(bsim);
    	//panel.add(combocompound);
    	
    	panel.add(drum_number);
    	panel.add(labelsize);
    	panel.add(labelblock);
    	panel.add(labelcompound);
    	panel.add(labeldiameter);
    	panel.add(labeldrum_number);
    	panel.add(labellength);
    	panel.add(labellot_compound);
    	panel.add(labelprocess);
    	panel.add(labellot_number);
    	panel.add(labeloperator);
    	panel.add(length);
    	panel.add(lot_compound);
    	panel.add(lot_number);
    	panel.add(size);
    	panel.add(operator);
    	panel.add(brnumber);
    	panel.add(remark);
    	panel.add(comboBlacklist);
    	panel.add(labelbrnumber);
    	panel.add(labelremark);
    	panel.add(labelexpired_date);
    	panel.add(labelpcnumber);
    	panel.add(pcnumber);
    	panel.add(compound);
    	panel.add(expired_date);
    	panel.add(dia);
    	panel.add(bupdate);
 
    	getContentPane().add(panel);
    	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setVisible(true);
    	actionlogin();

     
    }
    
    public void actionlogin() {
    bupdate.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Connection conn = new connect_db().connect_db();
			Slot_number = lot_number.getText().toString();
			Ssize = size.getText().toString();
			Sdia = dia.getText().toString();
			Scompound = compound.getText().toString();
		    Slot_compound = lot_compound.getText().toString();
		    Sdrum_number= drum_number.getText().toString();
		    Slength = length.getText().toString();
		    Sblock = block.getText().toString();
		    Sprocess = comboBlacklist.getSelectedItem().toString();
		    Soperator = operator.getText().toString();
		    Sbrnumber = brnumber.getText().toString();
		    Sremark = remark.getText().toString();
		    Sexpired_date = expired_date.getText().toString();
		    Spcnumber = pcnumber.getText().toString(); 
			
		    try {
		    	Statement st;
		        st = conn.createStatement();
		        ResultSet rs3 = st.executeQuery("SELECT * FROM item WHERE lot_number = '"+lot_number.getText().toString()+"' ");
		        String size = "",diameter = "",compound = "",lot_compound = "",drum_number = "",length = "",block = "";
		         
		        int tmp = 0;
		            
		       
		        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); // CR jednipat 28.11.2017
		        DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd"); // CR jednipat 28.11.2017
		        Calendar cal;
		        cal = Calendar.getInstance();  
		        int year = cal.get(Calendar.YEAR)-543;
		        cal.set(Calendar.YEAR,year);         
		        date = dateFormat2.format(cal.getTime());
		        time = dateFormat.format(cal.getTime());
		        st.executeUpdate("UPDATE item SET size ='"+Ssize+"', diameter ='"+Sdia+"', compound = '"+Scompound+"', lot_compound = '"+Slot_compound+"', drum_number = '"+Sdrum_number+"', length ='"+Slength+"', block = '"+Sblock+"', process = '"+Sprocess+"', user = '"+username+"', operator = '"+Soperator+"', date = '"+date+"', time = '"+time+"', brnumber = '"+Sbrnumber+"', remark = '"+Sremark+"', expired_date = '"+Sexpired_date+"', pcnumber = '"+Spcnumber+"' WHERE lot_number = '"+Slot_number+"' ");
		        PreparedStatement pre = conn.prepareStatement("INSERT INTO transaction1 (lot_number,size,diameter,compound,lot_compound,drum_number,length,block,process,user,operator,date,time,brnumber,remark,expired_date,pcnumber) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		                   pre.setString(1,Slot_number);
		                   pre.setString(2,Ssize);
		                   pre.setString(3,Sdia);
		                   pre.setString(4,Scompound);
		                   pre.setString(5,Slot_compound);
		                   pre.setString(6,Sdrum_number);
		                   pre.setString(7,Slength);
		                   pre.setString(8,Sblock);
		                   pre.setString(9,Sprocess);
		                   pre.setString(10,username);
		                   pre.setString(11,Soperator);
		                   pre.setString(12,date);
		                   pre.setString(13,time);
		                   pre.setString(14,Sbrnumber);
		                   pre.setString(15,Sremark+"Updated by admin");
		                   pre.setString(16,Sexpired_date);
		                   pre.setString(17,Spcnumber);
		                   pre.executeUpdate();
		        JOptionPane.showMessageDialog(null,"บันทึกข้อมูลเรียบร้อยแล้ว");
		        menu tmpmenu = Login.getInstance();
		        tmpmenu.bcustomer.doClick();
		        conn.close();
		        dispose();
		    } catch (SQLException ex) {
		        Logger.getLogger(DeleteDataDatabase.class.getName()).log(Level.SEVERE, null, ex);
		        JOptionPane.showMessageDialog(null,"Error");
		    }
		}
	});
   }

}
