package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pojo.User;
import util.DBUtil;


public class UserDao {
    public int query(int numbers) {
		String sql = "select * from user where **='"+numbers+"'";
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
		String sql = "select * from user where **='"+numbers+"'";
		User user = new User();
        try (Connection c = DBUtil.getConnection(); Statement ps = c.prepareStatement(sql)) {
           
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
            	user.setId(rs.getInt(1));
            	user.setNum(rs.getInt(2));
            	user.setTel(rs.getString(3));
            	user.setName(rs.getString(4));
            	user.setPurchase(rs.getInt(5));
			}
       
        } catch (SQLException e) {

            e.printStackTrace();
        }  

        String id = ""+user.getId();
        if (!id.equals(null)) return numbers;
        else return 0;
    }
}
