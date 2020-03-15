package information;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    static String ip = "localhost";
    static int port = 3306;
    static String database = "appointment";
    static String loginName = "root";
    static String password = "123456";
    public Connection c;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s", ip, port, database);
        return DriverManager.getConnection(url, loginName, password);
    }

    /* 关闭连接的方法 */
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null)
                rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (stmt != null)
                stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (conn != null)
                conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public boolean exeSQL(String sql){
        try{
            if(c==null)
                return false;
            Statement s=c.createStatement( ); // 创建SQL语句对象
            s.executeUpdate(sql);
            s.close();
            return true;
        }catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
