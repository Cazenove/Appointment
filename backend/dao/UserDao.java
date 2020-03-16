package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pojo.User;
import util.DBUtil;


public class UserDao {
    public int query(int numbers) {
		String sql = "select * from draw_list WHERE appointment_list_id="+numbers;
		
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
        
        if (user.getId()!=0) return numbers;
        else return 0;
    }
    
    public User queryUser(int numbers) {
		String sql = "select * from appointment_list WHERE id="+numbers;
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
