/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processchecker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
public class menu extends JFrame{
        private		JTabbedPane tabbedPane;
        private         JPanel          panel;
        private         JPanel          paneltop;
        private         JPanel          panelbot;
        private         JLabel          username;
	private		JPanel		panel1;
	private		JPanel		panel2;
	private		JPanel		panel3;
        private		JPanel		panel11;
	private		JPanel		panel22;
	private		JPanel		panel33;
        private         JScrollPane     js;
        private         JScrollPane     js2;
        JButton         bcustomer;
        JButton         blog;
        private         JButton         btransaction;
        private         JLabel          p1laname;
        private         JLabel          p1lasur;
        private         JLabel          p1lanum;
        private         JLabel          p1lablack;
        private         JTextField      p1textname;
        private         JTextField      p1textsur;
        private         JComboBox       p1textnum;
        private         JComboBox      p1textblacklist;
        private         JLabel          p2laname;
        private         JLabel          p2lasur;
        private         JLabel          p2lanum;
        private         JLabel          p2lablack;
        private         JLabel          p2ladatefrom;
        private         JLabel          p2ladateto;
        private         JTextField      p2textname;
        private         JTextField      p2textsur;
        public     static JDatePickerImpl p2datefrom;
        public       static   JDatePickerImpl p2dateto;
        private         JComboBox       p2textnum;
        private         JComboBox      p2textblacklist;
       // private         String[]        droplistStr = {"true","false"};
       // private         String[]        droplistStr_action = {"ยึดเครื่อง","ลบออกจากระบบ","คืนจำนำ","รับจำนำ"};
        private         JComboBox       comboAction;
        private         Connection      conn;
        private         JLabel          p3lanum;
        private         JLabel          p3latype;
        private         JLabel          p3labrand;
        private         JLabel          p3lamodel;
        private         JLabel          p3laaction;
        private         JTextField      p3textnum;
        private         JTextField      p3texttype;
        private         JTextField      p3textbrand;
        private         JTextField      p3textmodel;
        private         JTextField      p3textaction;
        private         String          user;
        private         String          process;
        private         ResultSet       rs;
        private         ResultSet       rs1;
        private         String          role;
        private         static String          user_process;
        private         static String[]        droplistStr = {"","Extrusion","Braid In","Braid admin","Braid Out","Wraping","Mandrel"};
        private         static String[]        droplistDiameter = {"","8.9","9.2","9.3","9.6","9.8","10.2","10.4","10.6","11.3","12.2","12.5","12.6","12.7","12.8","13.5","15.4","15.6","15.8","16.0","16.4","19.1","19.4","22.2","22.4","22.6","22.7","23.5","29.0","29.3","29.5","30.0","30.7","31.4","38.2","40.3"};
        private         String pathfile;
        private         String pathFileTmp;
                 JDatePanelImpl datePanelcheckin;
                 JDatePanelImpl datePanelcheckout;
                 UtilDateModel model1;
                 UtilDateModel model2;
                 Properties    p;
        
        
public static void main(String[] args) {
    
}
//JButton border = new JButton("รับจำนำ");
JButton bnewcustomer = new JButton("Register");
JButton bcheckin = new JButton("Check in");
JButton blogout  = new JButton("Logout");
JButton bexport2 = new JButton("Export ข้อมูลสินค้า");
JButton bexport = new JButton("Export Logs ข้อมูล");

menu(String username_login,String role_login,String process_login){
super("Semperflex Asia Corp., Ltd. Version 1.0.2");

panel = new JPanel();
username = new JLabel(username_login+"   ("+ role_login+")",SwingConstants.RIGHT);
user = username_login;
process = process_login;
role = role_login;
username.setPreferredSize(new Dimension(780,20));
paneltop = new JPanel();
panelbot = new JPanel();
setSize(1350,600);
setLocation(0,0);
createPage1();
createPage2();
tabbedPane = new JTabbedPane();
tabbedPane.setPreferredSize(new Dimension(1330, 510));
tabbedPane.addTab( "ข้อมูลสินค้า", panel1 );
tabbedPane.addTab( "Log ข้อมูล", panel2 );
paneltop.add(bnewcustomer);
paneltop.add(bcheckin);
paneltop.add(bexport2);
paneltop.add(bexport);
paneltop.add(username);
paneltop.add(blogout);
panelbot.add(tabbedPane);
panel.add(paneltop);
panel.add(panelbot);

getContentPane().add(panel);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
actionlogin();
}

public void actionlogin(){

bnewcustomer.addActionListener(new ActionListener() {   
    @Override
    public void actionPerformed(ActionEvent ae) {
      new RegisterDialog(user);
    }
});

bcheckin.addActionListener(new ActionListener() {   
    @Override
    public void actionPerformed(ActionEvent ae) {
      new CheckinDialog(user,process);
    }
});

bexport.addActionListener(new ActionListener() {   
    @Override
    public void actionPerformed(ActionEvent ae) {
        String[] header = {"LOT no.","size","diameter","compound","lot_compound","Drum no.","length(m)","block","process","user","Operator","date","time","brnumber","remark","expired_date","pcnumber"};
         conn = new connect_db().connect_db();
    try {
        Statement st;
        st = conn.createStatement();
        rs = st.executeQuery("select * from transaction1 where lot_number LIKE '%"+p2textname.getText().toString()+"%' AND lot_compound LIKE '%"+p2textsur.getText().toString()+"%' AND "
                + "diameter LIKE '%"+p2textnum.getSelectedItem().toString()+"%' AND  process LIKE '%"+p2textblacklist.getSelectedItem().toString()+"%'");
    ResultSetMetaData metaData = rs.getMetaData();
    int columnCount = metaData.getColumnCount();
    boolean flag_del = false;
    
                        HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Excel Sheet");
			HSSFRow rowhead = sheet.createRow((short) 0);
                        
			rowhead.createCell((short) 0).setCellValue(header[0]);
			rowhead.createCell((short) 1).setCellValue(header[1]);
			rowhead.createCell((short) 2).setCellValue(header[2]);
			rowhead.createCell((short) 3).setCellValue(header[3]);
			rowhead.createCell((short) 4).setCellValue(header[4]);
                        rowhead.createCell((short) 5).setCellValue(header[5]);
                        rowhead.createCell((short) 6).setCellValue(header[6]);
                        rowhead.createCell((short) 7).setCellValue(header[7]);
                        rowhead.createCell((short) 8).setCellValue(header[8]);
                        rowhead.createCell((short) 9).setCellValue(header[9]);
                        rowhead.createCell((short) 10).setCellValue(header[10]);
                        rowhead.createCell((short) 11).setCellValue(header[11]);
                        rowhead.createCell((short) 12).setCellValue(header[12]);
                        rowhead.createCell((short) 13).setCellValue(header[13]);
                        rowhead.createCell((short) 14).setCellValue(header[14]);
                        rowhead.createCell((short) 15).setCellValue(header[15]);
                        rowhead.createCell((short) 16).setCellValue(header[16]);
                        
			int index = 1;
			while (rs.next()) {
                             for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                                flag_del = false;
                            if(columnIndex == 12){
                                String tmp = rs.getObject(columnIndex).toString();
                                tmp = tmp.substring(0, 10);
                                if(!((tmp.compareTo(p2datefrom.getJFormattedTextField().getText().toString()) >= 0) && (tmp.compareTo(p2dateto.getJFormattedTextField().getText().toString()) <= 0))) { flag_del = true;  break;}   
                            }
                            }
                            if(flag_del == true) continue;
				HSSFRow row = sheet.createRow((short) index);
				row.createCell((short) 0).setCellValue(rs.getString(1));
				row.createCell((short) 1).setCellValue(rs.getString(2));
				row.createCell((short) 2).setCellValue(rs.getString(3));
				row.createCell((short) 3).setCellValue(rs.getString(4));
				row.createCell((short) 4).setCellValue(rs.getString(5));
                                row.createCell((short) 5).setCellValue(rs.getString(6));
                                row.createCell((short) 6).setCellValue(rs.getString(7));
                                row.createCell((short) 7).setCellValue(rs.getString(8));
				row.createCell((short) 8).setCellValue(rs.getString(9));
				row.createCell((short) 9).setCellValue(rs.getString(10));
				row.createCell((short) 10).setCellValue(rs.getString(11));
                                row.createCell((short) 11).setCellValue(rs.getString(12));
                                row.createCell((short) 12).setCellValue(rs.getString(13));
                                row.createCell((short) 13).setCellValue(rs.getString(14));
                                row.createCell((short) 14).setCellValue(rs.getString(15));
                                row.createCell((short) 15).setCellValue(rs.getString(16));
                                row.createCell((short) 16).setCellValue(rs.getString(17));
				index++;
			}
                        pathFileTmp = promptForFolder();
			FileOutputStream fileOut = new FileOutputStream(pathFileTmp);
			wb.write(fileOut);
			fileOut.close();
			JOptionPane.showMessageDialog(null,"Export ข้อมูลเรียบร้อยแล้ว");
                        
    }
    catch (SQLException ex) {
        Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null,ex);
    }   catch (FileNotFoundException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex);
        } catch (IOException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex);
        }
}});

bexport2.addActionListener(new ActionListener() {   
    @Override
    public void actionPerformed(ActionEvent ae) {
        String[] header = {"LOT no.","size","diameter","compound","lot_compound","Drum no.","length(m)","block","process","user","Operator","date","time","brnumber","remark","expired_date","pcnumber"};
         conn = new connect_db().connect_db();
    try {
        Statement st;
        st = conn.createStatement();
        rs = st.executeQuery("select * from item where lot_number LIKE '%"+p1textname.getText().toString()+"%' AND lot_compound LIKE '%"+p1textsur.getText().toString()+"%' AND "
                + "diameter LIKE '%"+p1textnum.getSelectedItem().toString()+"%' AND  process LIKE '%"+p1textblacklist.getSelectedItem().toString()+"%'");
                        HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Excel Sheet");
			HSSFRow rowhead = sheet.createRow((short) 0);
                        
			rowhead.createCell((short) 0).setCellValue(header[0]);
			rowhead.createCell((short) 1).setCellValue(header[1]);
			rowhead.createCell((short) 2).setCellValue(header[2]);
			rowhead.createCell((short) 3).setCellValue(header[3]);
			rowhead.createCell((short) 4).setCellValue(header[4]);
                        rowhead.createCell((short) 5).setCellValue(header[5]);
                        rowhead.createCell((short) 6).setCellValue(header[6]);
                        rowhead.createCell((short) 7).setCellValue(header[7]);
                        rowhead.createCell((short) 8).setCellValue(header[8]);
                        rowhead.createCell((short) 9).setCellValue(header[9]);
                        rowhead.createCell((short) 10).setCellValue(header[10]);
                        rowhead.createCell((short) 11).setCellValue(header[11]);
                        rowhead.createCell((short) 12).setCellValue(header[12]);
                        rowhead.createCell((short) 13).setCellValue(header[13]);
                        rowhead.createCell((short) 14).setCellValue(header[14]);
                        rowhead.createCell((short) 15).setCellValue(header[15]);
                        rowhead.createCell((short) 16).setCellValue(header[16]);
                        
			int index = 1;
			while (rs.next()) {

				HSSFRow row = sheet.createRow((short) index);
				row.createCell((short) 0).setCellValue(rs.getString(1));
				row.createCell((short) 1).setCellValue(rs.getString(2));
				row.createCell((short) 2).setCellValue(rs.getString(3));
				row.createCell((short) 3).setCellValue(rs.getString(4));
				row.createCell((short) 4).setCellValue(rs.getString(5));
                                row.createCell((short) 5).setCellValue(rs.getString(6));
                                row.createCell((short) 6).setCellValue(rs.getString(7));
                                row.createCell((short) 7).setCellValue(rs.getString(8));
				row.createCell((short) 8).setCellValue(rs.getString(9));
				row.createCell((short) 9).setCellValue(rs.getString(10));
				row.createCell((short) 10).setCellValue(rs.getString(11));
                                row.createCell((short) 11).setCellValue(rs.getString(12));
                                row.createCell((short) 12).setCellValue(rs.getString(13));
                                row.createCell((short) 13).setCellValue(rs.getString(14));
                                row.createCell((short) 14).setCellValue(rs.getString(15));
                                row.createCell((short) 15).setCellValue(rs.getString(16));
                                row.createCell((short) 16).setCellValue(rs.getString(17));
                                
				index++;
			}
                        pathFileTmp = promptForFolder();
			FileOutputStream fileOut = new FileOutputStream(pathFileTmp);
			wb.write(fileOut);
			fileOut.close();
			JOptionPane.showMessageDialog(null,"Export ข้อมูลเรียบร้อยแล้ว");
                        
    }
    catch (SQLException ex) {
        Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null,ex);
    }   catch (FileNotFoundException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex);
        } catch (IOException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex);
        }
}});


blogout.addActionListener(new ActionListener() {   
    @Override
    public void actionPerformed(ActionEvent ae) {
      dispose();
      new Login();
    }
});

}
public void createPage1(){
    panel1 = new JPanel();
    panel11 = new JPanel(new GridLayout());
    panel1.setLayout(new BorderLayout());
    bcustomer = new JButton("ค้นหา");
    p1laname = new JLabel("Lot no",SwingConstants.CENTER);
    p1lasur = new JLabel("Lot Compound",SwingConstants.CENTER);
    p1lanum = new JLabel("Diameter",SwingConstants.CENTER);
    p1lablack = new JLabel("Process",SwingConstants.CENTER);
    p1textname  = new JTextField();
    p1textsur  = new JTextField();
    p1textnum  = new JComboBox(droplistDiameter);
    p1textblacklist = new JComboBox(droplistStr);
    conn = new connect_db().connect_db();
    
    bcustomer.addActionListener(new ActionListener() {   
    @Override
    public void actionPerformed(ActionEvent ae){
     conn = new connect_db().connect_db();
    try {
        Statement st;
        st = conn.createStatement();
         rs1 = st.executeQuery("select * from user where username = '"+user+"' ");
         while(rs1.next()){
             user_process = rs1.getString("process"); 
         }
         rs = st.executeQuery("select * from item where lot_number LIKE '%"+p1textname.getText().toString()+"%' AND lot_compound LIKE '%"+p1textsur.getText().toString()+"%' AND "
                + "diameter LIKE '%"+p1textnum.getSelectedItem().toString()+"%' AND  process LIKE '%"+p1textblacklist.getSelectedItem().toString()+"%'");
        panel1.remove(js);
    // It creates and displays the table
        JTable table = new JTable(buildTableModel(rs,2));
        table.setName("item");
        table.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        table.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(),1,role,user));
        table.getColumn("Edit").setCellRenderer(new ButtonRenderer());
        table.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(),2,role,user));
        table.setFillsViewportHeight(true);
        js = new JScrollPane(table);
        js.setVisible(true);
        js.setPreferredSize(new Dimension(1100, 460));
        // bcustomer.setPreferredSize(new Dimension(100, 20));
        // p1laname.setPreferredSize(new Dimension(100, 20));
        panel1.add(js,BorderLayout.PAGE_END);
        panel1.revalidate();
    }
    catch (SQLException ex) {
        Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }});
    
conn = new connect_db().connect_db();
 try {
        Statement st;
        st = conn.createStatement();
        //ResultSet rs = st.executeQuery("select * from item");
        
    // It creates and displays the table
        //JTable table = new JTable(buildTableModel(rs,1));
        //table.getColumn("Change").setCellRenderer(new ButtonRenderer());
        //table.getColumn("Change").setCellEditor(new ButtonEditor(new JCheckBox(),1));
        //table.setFillsViewportHeight(true);
        js = new JScrollPane();
        js.setVisible(true);
        js.setPreferredSize(new Dimension(1100, 460));
        bcustomer.setPreferredSize(new Dimension(100, 20));
        p1laname.setPreferredSize(new Dimension(100, 20));
                
        
        panel1.add(js,BorderLayout.PAGE_END);       
        panel11.add(p1laname);
        panel11.add(p1textname);
        panel11.add(p1lasur);
        panel11.add(p1textsur);
        panel11.add(p1lanum);
        panel11.add(p1textnum);
        panel11.add(p1lablack);  
        panel11.add(p1textblacklist);
        panel11.add(bcustomer);
        panel1.add(panel11);
 }
 catch (SQLException ex) {
        Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
    }
		
	}

public void createPage2(){
    panel2 = new JPanel();
    panel22 = new JPanel(new GridLayout());
    panel2.setLayout(new BorderLayout());
    blog = new JButton("ค้นหา");
    p2laname = new JLabel("Lot no",SwingConstants.CENTER);
    p2lasur = new JLabel("Lot Compound",SwingConstants.CENTER);
    p2lanum = new JLabel("Diameter",SwingConstants.CENTER);
    p2lablack = new JLabel("Process",SwingConstants.CENTER);
    p2ladatefrom = new JLabel("Date From - To :",SwingConstants.CENTER);
    //p2ladateto = new JLabel("To :",SwingConstants.CENTER);
    p2textname  = new JTextField();
    p2textsur  = new JTextField();
    p2textnum  = new JComboBox(droplistDiameter);
    p2textblacklist = new JComboBox(droplistStr);
     model1 = new UtilDateModel();
     model2 = new UtilDateModel();
    Calendar now = Calendar.getInstance();
    int year = now.get(Calendar.YEAR)-543;
    int month = now.get(Calendar.MONTH); // Note: zero based!
    int day = now.get(Calendar.DAY_OF_MONTH);
    model1.setDate(year, month, day); 
    model2.setDate(year, month, day);  
    model1.setSelected(true);
    model2.setSelected(true);
    //p = new Properties();
    datePanelcheckin = new JDatePanelImpl(model1);
    datePanelcheckout = new JDatePanelImpl(model2);
    p2datefrom = new JDatePickerImpl(datePanelcheckin,new DateLabelFormatter());
    p2dateto = new JDatePickerImpl(datePanelcheckout,new DateLabelFormatter());
    conn = new connect_db().connect_db();
    
    blog.addActionListener(new ActionListener() {   
    @Override
    public void actionPerformed(ActionEvent ae){
     conn = new connect_db().connect_db();
    try {
        Statement st;
        st = conn.createStatement();
         rs1 = st.executeQuery("select * from user where username = '"+user+"' ");
         while(rs1.next()){
             user_process = rs1.getString("process"); 
         }
         rs = st.executeQuery("select * from transaction1 where lot_number LIKE '%"+p2textname.getText().toString()+"%' AND lot_compound LIKE '%"+p2textsur.getText().toString()+"%' AND "
                + "diameter LIKE '%"+p2textnum.getSelectedItem().toString()+"%' AND  process LIKE '%"+p2textblacklist.getSelectedItem().toString()+"%'");
        panel2.remove(js2);
    // It creates and displays the table
        JTable table = new JTable(buildTableModel2(rs,0));
        table.setName("transaction1");
        //table.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        //table.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(),1,role));
        table.setFillsViewportHeight(true);
        js2 = new JScrollPane(table);
        js2.setVisible(true);
        js2.setPreferredSize(new Dimension(1100, 460));
        // bcustomer.setPreferredSize(new Dimension(100, 20));
        // p1laname.setPreferredSize(new Dimension(100, 20));
        panel2.add(js2,BorderLayout.PAGE_END);
        panel2.revalidate();
    }
    catch (SQLException ex) {
        Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }});
    
conn = new connect_db().connect_db();
 try {
        Statement st;
        st = conn.createStatement();
        //ResultSet rs = st.executeQuery("select * from item");
        
    // It creates and displays the table
        //JTable table = new JTable(buildTableModel(rs,1));
        //table.getColumn("Change").setCellRenderer(new ButtonRenderer());
        //table.getColumn("Change").setCellEditor(new ButtonEditor(new JCheckBox(),1));
        //table.setFillsViewportHeight(true);
        js2 = new JScrollPane();
        js2.setVisible(true);
        js2.setPreferredSize(new Dimension(1100, 460));
        blog.setPreferredSize(new Dimension(100, 20));
        p2laname.setPreferredSize(new Dimension(100, 20));
                
        
        panel2.add(js2,BorderLayout.PAGE_END);       
        panel22.add(p2laname);
        panel22.add(p2textname);
        panel22.add(p2lasur);
        panel22.add(p2textsur);
        panel22.add(p2lanum);
        panel22.add(p2textnum);
        panel22.add(p2lablack);  
        panel22.add(p2textblacklist);
        panel22.add(p2ladatefrom);
        panel22.add(p2datefrom);
        //panel22.add(p2ladateto);
        panel22.add(p2dateto);
        panel22.add(blog);
        panel2.add(panel22);
 }
 catch (SQLException ex) {
        Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
    }
		
	}

        
        public static DefaultTableModel buildTableModel2(ResultSet rs,Integer page)
        throws SQLException {
                

    ResultSetMetaData metaData = rs.getMetaData();
    boolean flag_del = false;
    // names of columns
    Vector<String> columnNames = new Vector<String>();
    int columnCount = metaData.getColumnCount();
    String[] header = {".","LOT no.","size","diameter","compound","lot_compound","Drum no.","length(m)","block","process","user","Operator","date","time","brnumber","remark","expired_date","pcnumber"};
    for (int column = 1; column <= columnCount+page; column++) {
        columnNames.add(header[column]);
    }

    // data of the table
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (rs.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            flag_del = false;
            if(columnIndex == 12){
               String tmp = rs.getObject(columnIndex).toString();
               tmp = tmp.substring(0, 10);
               if(!((tmp.compareTo(p2datefrom.getJFormattedTextField().getText().toString()) >= 0) && (tmp.compareTo(p2dateto.getJFormattedTextField().getText().toString()) <= 0))) { flag_del = true;  break;}
            }
            vector.add(rs.getObject(columnIndex));
        }
        if(flag_del == false)  data.add(vector);
        
        
    }
    return new DefaultTableModel(data, columnNames){
    boolean[] canEdit = new boolean[]{
                    false, false, false, false,false,false,false, false, false, false,false, false,false,false,false,false,false
            };
    public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
    };
        }
        
        
        public static DefaultTableModel buildTableModel(ResultSet rs,Integer page)
        throws SQLException {
                

    ResultSetMetaData metaData = rs.getMetaData();
   
    // names of columns
    Vector<String> columnNames = new Vector<String>();
    int columnCount = metaData.getColumnCount();
    String[] header = {".","LOT no.","size","diameter","compound","lot_compound","Drum no.","length(m)","block","process","user","Operator","date","time","brnumber","remark","expired_date","pcnumber","Edit","Delete"};
    for (int column = 1; column <= columnCount+page; column++) {
        columnNames.add(header[column]);
    }

    // data of the table
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (rs.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            
            vector.add(rs.getObject(columnIndex));
        }
        vector.add("Edit");
        vector.add("Delete");
        //System.out.println(vector.get(8));
        
        if(droplistStr[0].equals(user_process) && vector.get(8).equals(droplistStr[0])) data.add(vector);
        else if(droplistStr[1].equals(user_process) && vector.get(8).equals(droplistStr[0]) || vector.get(8).equals(droplistStr[1])) data.add(vector);
        else if(droplistStr[2].equals(user_process) && vector.get(8).equals(droplistStr[1]) || vector.get(8).equals(droplistStr[2])) data.add(vector);
        else if(droplistStr[3].equals(user_process) && vector.get(8).equals(droplistStr[2]) || vector.get(8).equals(droplistStr[3])) data.add(vector);
        else if(droplistStr[4].equals(user_process) && vector.get(8).equals(droplistStr[3]) || vector.get(8).equals(droplistStr[4])) data.add(vector);
        //data.add(vector);
    }
    return new DefaultTableModel(data, columnNames){
    boolean[] canEdit = new boolean[]{
                    false, false,false,false, false, false,false, false, false, false,false, false,false,false,false,false,false,true,true
            };
    public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
    };
        }
        
public String promptForFolder( )
{
    JFileChooser fc = new JFileChooser();
   // fc.addChoosableFileFilter(new xlssave);
    //fc.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES );
    fc.setSelectedFile(new File("D:/Files.xls"));
    //pathfile = fc.getCurrentDirectory().toString()+ "\\" +fc.getSelectedFile().getName().toString()+"Files.xls";
    
    if( fc.showSaveDialog( menu.this ) == JFileChooser.APPROVE_OPTION )
    {
        
        pathfile = fc.getCurrentDirectory().toString()+ "\\"+ fc.getSelectedFile().getName().toString();
        pathfile = pathfile.replace('\\', '/');
        //System.out.println(pathfile);
        return pathfile;
    }

    return null;
}
}
