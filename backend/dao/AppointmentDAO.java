import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentDAO {
	public void doAppointmant() {
		String name = "hello";
		String id_num = "111111";
		String phone_num = "111111";
		int subscribe_num = 3;
		
		int rounds;
		
		try {
			Connection conn = DBUtil.getConnection();
			String str = "select max(id) as id from appointment";
			PreparedStatement pStmt = conn.prepareStatement(str);
			ResultSet rSet = pStmt.executeQuery() ;
			while(rSet.next()) {
				//取得当前轮次
				rounds = rSet.getInt("id");
			}
		} catch (SQLException e) {
			System.out.print("ERROR!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
