import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentDAO {
	public void doAppointmant() {
		String name = "hello";
		String id_num = "111111";
		String phone_num = "111111";
		int subscribe_num = 3;
		
		int rounds = 0;
		
		try {
			Connection conn = DBUtil.getConnection();
			
			//获取当前轮次
			String str_getRounds = "select max(id) as id from appointment";
			PreparedStatement pStmt = conn.prepareStatement(str_getRounds);
			ResultSet rSet = pStmt.executeQuery() ;
			while(rSet.next()) {
				//取得当前轮次
				rounds = rSet.getInt("id");
			}
			
			String str_getSameRound = "select id,appointment_id from appointment_list where id_number='"+id_num+" or phone_number='"+phone_num+"'";
			pStmt = conn.prepareStatement(str_getSameRound);
			rSet = pStmt.executeQuery() ;
			while(rSet.next()) {
				if(rounds==rSet.getInt("appointment_id")) {
					//如果手机号或者身份证号已经在本次摇号登记过了，预约失败
					System.out.println("手机号或者身份证号已经在本次摇号登记过了");
					return;
				} else if(rounds <= rSet.getInt("appointment_id")+3) {
					//当用户有三轮内预约的记录时，更具记录的id查找draw_list
				}
			}
			
		} catch (SQLException e) {
			System.out.print("ERROR!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
