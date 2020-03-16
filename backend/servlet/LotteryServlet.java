package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

    
    //�Լ�����ķ���,���ڻ�ȡ��ǰ��ԤԼ����
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
    
    //�Լ�����ķ��������ڻ�ȡ��ǰԤԼʱ���ڵ�ԤԼ��
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
    
    //�Լ�����ķ��������ڻ�ȡ����ԤԼ�ṩ�Ŀ�������
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
    
    //�Լ�����ķ��������н���ԤԼ�ߵ�ԤԼid�浽ԤԼ����
    private void popLottery(int all,int turn,List<Appointer> appointers)
    {
    	for(int i=0;all>0;i++)
		{
    		if(i==appointers.size())
    		{
    			break;
    		}
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
		
		String status=request.getParameter("status");
		
		if(status.contentEquals("end")==true)
		{
			int turn = getTurn();
			
			List<Appointer> appointers=getAppointers(turn);
			
			int all=getAll(turn);
			
			popLottery(all,turn,appointers);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write("结束成功");
			out.close();
			
		}
		else
		{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write("结束失败");
			out.close();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}
	
	

}

