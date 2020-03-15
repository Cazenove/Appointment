package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBUtil;
import pojo.Appointer;

/**
 * Servlet implementation class AppointServlet
 */
@WebServlet("/LotteryServlet")
public class LotteryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LotteryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    //自己定义的方法,用于获取当前的预约轮数
    private int getTurn()
	{
		String sql = "select * from appointment_list order by id DESC limit 1;" ;
		int turn = -1;
		try (Connection c = DBUtil.getConnection(); Statement ps = c.prepareStatement(sql)) {
	           
	        ResultSet rs = ps.executeQuery(sql);
	        while (rs.next()) {
	        	turn=rs.getInt("appointment_id");
	        	
			}
	   
	    } catch (SQLException e) {

	        e.printStackTrace();
	    }
		return turn;
	}
    
    //自己定义的方法，用于获取当前预约时间内的预约者
    private List<Appointer> getAppointers(int turn)
    {
    	List<Appointer> appointers=new ArrayList<Appointer>();
		String sql="select * from appointment_list WHERE appointment_id="+turn;
		try (Connection c = DBUtil.getConnection(); Statement ps = c.prepareStatement(sql)) {
	           
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
            	Appointer temp=new Appointer(rs.getInt("id"),rs.getInt("subscribe_number"));
            	appointers.add(temp);
			}
       
        } catch (SQLException e) {

            e.printStackTrace();
        }
		Collections.shuffle(appointers);
		return appointers;
    }
    
    //自己定义的方法，用于获取本轮预约提供的口罩数量
    private int getAll(int turn)
    {
    	int all=0;
		String sql="select * from appointment WHERE id="+turn;
		try (Connection c = DBUtil.getConnection(); Statement ps = c.prepareStatement(sql)) {
	           
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
            	all=rs.getInt("max");
			}
       
        } catch (SQLException e) {

            e.printStackTrace();
        }
		return all;
    }
    
    //自己定义的方法，把中奖的预约者的预约id存到预约表内
    private void popLottery(int all,int turn,List<Appointer> appointers)
    {
    	for(int i=0;all>0;i++)
		{
			all=all-appointers.get(i).getSubscribe();
			if(all>0)
			{
				String sql="INSERT INTO draw_list (appointment_list_id) VALUE("+appointers.get(i).getId()+")";
				try (Connection c = DBUtil.getConnection(); Statement ps = c.prepareStatement(sql)) {
			           
		            ps.executeUpdate(sql);
		       
		        } catch (SQLException e) {

		            e.printStackTrace();
		        }
			}
		}
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int turn = getTurn();
		
		List<Appointer> appointers=getAppointers(turn);
		
		int all=getAll(turn);
		
		popLottery(all,turn,appointers);
		
		request.getRequestDispatcher("D:\\Users\\qaz70\\Documents\\GitHub\\live-project\\frontend\\index.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}

