package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import pojo.User;

/**
 * 中签查询功能
 */
@WebServlet("/query")
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int number = Integer.parseInt(request.getParameter("aptid"));   //接收表单编号
		UserDao userDao = new UserDao();
		User user = new User();
		if(userDao.query(number)!=0){  //查询是否中奖
			user = userDao.queryUser(number);
			String userRes = user.getName()+" "+user.getUserId()+" "+user.getTel()+" "+user.getPurchase();
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(userRes);
			out.close();
		}
		else{
			//抱歉未中奖
			String userRes = "抱歉未中奖";
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(userRes);
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
