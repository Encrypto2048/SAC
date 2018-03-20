/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author New
 */
public class DeleteDataDatabase {
    public static void main(String[] args) {
    }
    DeleteDataDatabase(String db_name,String field,String data){

        Connection conn = new connect_db().connect_db();

    try {
        Statement st;
        st = conn.createStatement();
        st.executeUpdate("DELETE FROM "+db_name+" WHERE "+field+" = '"+data+"' ");
        JOptionPane.showMessageDialog(null,"Done");
        conn.close();
        menu tmpmenu = Login.getInstance();
        tmpmenu.bcustomer.doClick();
    } catch (SQLException ex) {
        Logger.getLogger(DeleteDataDatabase.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null,"Error");
    }
    }
}