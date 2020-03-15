package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;

public class AppointmentDAO {
	
	public AppointmentDAO() {
		// TODO Auto-generated constructor stub
		
	}
	
	public int doAppointmant(String name,String id_num,String phone_num,int subscribe_num) {
		/*String name = "hello";
		String id_num = "111111";
		String phone_num = "212121";
		int subscribe_num = 3;*/
		
		int rounds = 0;
		
		try {
			Connection conn = DBUtil.getConnection();
			
			//获取当前轮次
			String str_getRounds = "select max(id) as id from appointment";
			PreparedStatement pStmt = conn.prepareStatement(str_getRounds);
			ResultSet rSet = pStmt.executeQuery();
			while(rSet.next()) {
				//取得当前轮次
				rounds = rSet.getInt("id");
			}
			
			String str_getSameRound = "select id,appointment_id from appointment_list where id_number='"+id_num+"' or phone_number='"+phone_num+"'";
			PreparedStatement Stmt = conn.prepareStatement(str_getSameRound);
			ResultSet Set = Stmt.executeQuery() ;
			while(Set.next()) {
				if(rounds==Set.getInt("appointment_id")) {
					//如果手机号或者身份证号已经在本次摇号登记过了，预约失败
					//System.out.println("手机号或者身份证号已经在本次摇号登记过了");
					return 0;
				} else if(rounds <= Set.getInt("appointment_id")+3) {
					//当用户有三轮内预约的记录时，根据记录的id查找draw_list
					int appointment_list_id = Set.getInt("id");
					String str = "select * from draw_list where appointment_list_id='"+appointment_list_id+"'";
					PreparedStatement ps = conn.prepareStatement(str);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						//System.out.println("手机号或者身份证号在此前三次预约中成功中签");
						return 0;
					}
				}
			}
			
			String strInsert = "insert into appointment_list(name,id_number,phone_number,subscribe_number,appointment_id) "
					+ "values ('"+name+"','"+id_num+"','"+phone_num+"','"+subscribe_num+"','"+rounds+"')";
			PreparedStatement stmt = conn.prepareStatement(strInsert);
			int i=stmt.executeUpdate();
			//System.out.println("预约成功");
			return 1;
			
		} catch (SQLException e) {
			System.out.print("ERROR!\n");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
}
