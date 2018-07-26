package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import javabeans.*;

/**
 * Servlet implementation class CheckServlet
 */
@WebServlet("/Check")
public class CheckServlet extends HttpServlet {
	

	Connection conn=null;
    PreparedStatement prst=null;
    ResultSet rs=null;
    /*
    String url = "jdbc:sqlserver://localhost:1433;DatabaseName=CHAT;";
    String username="sa";
    String password="1234";
    String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	*/
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckServlet() {
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("doGet");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String username1=request.getParameter("username");
		String password1=request.getParameter("password");
		System.out.println("doPost:"+username1+password1);
        try {
        	
			conn=DBTool.getConnection();
	    	String sql="select *from _user where Uname=?";
			prst=conn.prepareStatement(sql);
			prst.setString(1, username1);
			rs=prst.executeQuery();
			
			User us=new User();
			
			if(rs.next()){
				//String power=rs.getString("grade");
				String un=rs.getString(1);
				String pd=rs.getString(2);
				int gd=rs.getInt("Ugrade");
				if(pd.equals(password1)){
				us.setUname(un);
				us.setUpass(pd);
				us.setUgrade(gd);//权限修改
				
				//将仓库号绑定
				String sql1="select *from _Admin where Ano=?";
				prst=conn.prepareStatement(sql1);
				prst.setInt(1, us.getUserid());
				rs=prst.executeQuery();
				while(rs.next()){
					us.setUwh(rs.getInt("Sno"));
				}
				
				
				HttpSession mysession=request.getSession();
				mysession.setAttribute("user", us);
				response.sendRedirect("index.jsp");
				}
				else{
					RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				}
			}
			else{
				RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		} catch(SQLServerException e){
			//e.printStackTrace();
			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			//System.out.println("用户名为空");
		}
        	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
        finally{
        	DBTool.close(rs, prst, conn);
        }
	}

}
