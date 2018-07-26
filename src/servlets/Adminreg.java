package servlets;

import java.awt.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabeans.DBTool;
import javabeans.User;

/**
 * Servlet implementation class Adminreg
 */
@WebServlet("/Adminreg")
public class Adminreg extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //数据库   
	Connection conn=null;
    PreparedStatement prst=null;
    int rs;
    
	User ur=null;//操作者
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Adminreg() {
    
        super();
        
    
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession hs=request.getSession();
		User ur=(User)hs.getAttribute("user");
	
		
		conn=DBTool.getConnection();
		int no;
		
		String action=request.getParameter("action");
	
		String sql="insert into _Admin(Aname,Asex,Acall,Sno) values("
				+ "?,?,?,?)";
		String sql1="update _Admin set Aname=?,Asex=?,Acall=?,Sno=? where Ano=?";
		try {
			if(action.equals("reg")){
			prst=conn.prepareStatement(sql);
			}
			else if(action.equals("modify")){
				no=(int) hs.getAttribute("mdfaid");
				prst=conn.prepareStatement(sql1);
				prst.setInt(5, no);
			}
			prst.setString(1, request.getParameter("name"));
			prst.setString(2,request.getParameter("sex"));
			prst.setString(3, request.getParameter("tele"));
			prst.setInt(4,Integer.parseInt(request.getParameter("warehouse")));
			 prst.execute();//数据更新 用EXECUTEQUERY会报‘该语句没有返回结果集’错
			response.sendRedirect("suc.jsp");

		} catch(SQLException e){
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		} 
		catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		finally{
			DBTool.close(prst, conn);
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
