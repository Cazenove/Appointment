package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pojo.User;
import util.DBUtil;


public class UserDao {
    public int query(int numbers) {
		String sql = "select * from draw_list where appointment_list_id='"+numbers+"'";
		User user = new User();
        try (Connection c = DBUtil.getConnection(); Statement ps = c.prepareStatement(sql)) {
           
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
            	user.setId(rs.getInt(1));
            	user.setNum(rs.getInt(2));
			}
       
        } catch (SQLException e) {

            e.printStackTrace();
        }  

        String id = ""+user.getId();
        if (!id.equals(null)) return numbers;
        else return 0;
    }
    
    public User queryUser(int numbers) {
		String sql = "select * from appointment_list where appointment_id='"+numbers+"'";
		User user = new User();
        try (Connection c = DBUtil.getConnection(); Statement ps = c.prepareStatement(sql)) {
           
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
            	user.setId(rs.getInt(1));
            	user.setName(rs.getString(2));
            	user.setUserId(rs.getString(3));
            	user.setTel(rs.getString(4));
            	user.setPurchase(rs.getInt(5));	
            	user.setNum(rs.getInt(6));
			}
       
        } catch (SQLException e) {

            e.printStackTrace();
        }  
        return user;
    }
}
