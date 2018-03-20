/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processchecker;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author jednipat.pong
 */
class ButtonEditor extends DefaultCellEditor {
  protected JButton button;

  private String label;
  private String type;
  private boolean isPushed;
  private String table_name;
  private String lot_number;
  //CR jednipat 28.11.2017
  private String size;
  private String diameter;
  private String compound;
  private String lot_compound;
  private String drum_number;
  private String length;
  private String block;
  private String process;
  private String user;
  private String Operator;
  private String date;
  private String time;
  private String brnumber;
  private String remark;
  private String expired_date;
  private String pcnumber;
//CR jednipat 28.11.2017
  private String role;

  public ButtonEditor(JCheckBox checkBox,int buttype,String role_login,String user_login) {
    super(checkBox);
    switch(buttype)
    {
        case 1: label = "Delete";   break;
        case 2: label = "Edit";     break;
    }
    role = role_login;
    user = user_login;
    System.out.print(role);
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
      }
    });
  }

  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {
    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else {
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
        
    if(table.getName().toString().equals("item")){
    table_name = table.getName().toString();
    lot_number = table.getModel().getValueAt(row, 0).toString();
    size = table.getModel().getValueAt(row, 1).toString();
    diameter = table.getModel().getValueAt(row, 2).toString();
    compound= table.getModel().getValueAt(row, 3).toString();
    lot_compound= table.getModel().getValueAt(row, 4).toString();
    drum_number= table.getModel().getValueAt(row, 5).toString();
    length= table.getModel().getValueAt(row, 6).toString();
    block= table.getModel().getValueAt(row, 7).toString();
    process= table.getModel().getValueAt(row, 8).toString();
    Operator= table.getModel().getValueAt(row, 10).toString();
    date= table.getModel().getValueAt(row, 11).toString();
    time= table.getModel().getValueAt(row, 12).toString();
    brnumber= table.getModel().getValueAt(row,13).toString();
    remark= table.getModel().getValueAt(row, 14).toString();
    expired_date= table.getModel().getValueAt(row, 15).toString();
    pcnumber= table.getModel().getValueAt(row, 16).toString();
    }
    isPushed = true;
    return button;
  }
    
  public Object getCellEditorValue() {
    if (isPushed) {
      if(label.equals("Delete") && role.equals("admin") ){
          int dialogButton = JOptionPane.YES_NO_OPTION;
      int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure to delete?","Warning",dialogButton);
      if(dialogResult == JOptionPane.YES_OPTION){
      new DeleteDataDatabase(table_name,"lot_number",lot_number);
      }
      }
      else if(label.equals("Edit") && role.equals("admin") ){
    	  //CR jednipat 28.11.2017
      new UpdateDataDatabase(table_name,"lot_number",lot_number,size,diameter,compound,lot_compound,drum_number,length,block,process,user,Operator,date,time,brnumber,remark,expired_date,pcnumber);
      }
      else { JOptionPane.showMessageDialog(null,"No Authorization"); }
      isPushed = false;
    } 
    return new String(label);
  }

  public boolean stopCellEditing() {
    isPushed = false;
    return super.stopCellEditing();
  }

  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }
}
