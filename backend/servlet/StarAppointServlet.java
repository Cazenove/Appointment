package servlet;

import java.io.IOException;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBUtil;

/**
 * Servlet implementation class StarAppointServlet
 */
@WebServlet("/StarAppointServlet")
public class StarAppointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StarAppointServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String start=request.getParameter("startDate");
		String end=request.getParameter("endDate");
		String max=request.getParameter("maxNum");
		
		start=start+" 00:00:00";
		end=end+" 23:59:59";
		
		String sql="INSERT INTO appointment (start,end,max) VALUE('"+start+"','"+end+"',"+max+")";
		try (Connection c = DBUtil.getConnection(); Statement ps = c.prepareStatement(sql)) {
	           
            ps.executeUpdate(sql);
       
        } catch (SQLException e) {

            e.printStackTrace();
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
