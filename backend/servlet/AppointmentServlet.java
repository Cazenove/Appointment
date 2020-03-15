package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AppointmentDAO;

/**
 * Servlet implementation class AppointmentServlet
 */
@WebServlet("/appointment")
public class AppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String id_num = request.getParameter("idnum");
		String phone_num = request.getParameter("telnum");
		String num = request.getParameter("aptnum");
		int  subscribe_num = Integer.valueOf(num);
		AppointmentDAO appointmentDAO = new AppointmentDAO();
		String appointmentRes = null;
		int flag = appointmentDAO.doAppointmant(name, id_num, phone_num, subscribe_num);
		if (flag==0) {
			appointmentRes = "预约失败";
		}
		else{
			appointmentRes = "预约成功";
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(appointmentRes);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
