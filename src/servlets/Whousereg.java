package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import javabeans.DBTool;
import javabeans.User;

@WebServlet("/Whousereg")
 public class Whousereg  extends HttpServlet {

	private static final long serialVersionUID = 1L;
    //数据库   
	Connection conn=null;
    PreparedStatement prst=null;
    int rs;
    
	User ur=null;//操作者
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	  public Whousereg() {
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
			
			int no;
			String action=request.getParameter("action");
			
			HttpSession hs=request.getSession();
			User ur=(User)hs.getAttribute("user");
			System.out.println("3232");
			conn=DBTool.getConnection();
			String sql="insert into _Warehouse(Sname,Sadress,Sbig) values("
					+ "?,?,?)";
			
			String sql1="update _Warehouse set Sname=?,Sadress=?,Sbig=? where Sno=?";
			
			try {
				if("reg".equals(action)){
					prst=conn.prepareStatement(sql);
					}
					else if(action.equals("modify")){
						no=(int) hs.getAttribute("mdfwid");
						prst=conn.prepareStatement(sql1);
						prst.setInt(4, no);
					}
			
				prst.setString(1, request.getParameter("warename"));
				prst.setString(2,request.getParameter("saddress"));
				prst.setInt(3,Integer.parseInt(request.getParameter("wbig")) );
			
				prst.execute();//数据更新 用EXECUTEQUERY会报‘该语句没有返回结果集’错
				//视图
				if("reg".equals(action)){
					String SQl ="create view  V_W" ;
					sql = "select Sno from _Warehouse where Sname = '" + request.getParameter("warename")+"'";
					Statement stmt=conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					rs.next();
					SQl = SQl + rs.getString(1);
					prst=conn.prepareStatement(SQl +  "  (Gname,Gid,Gnum,Gprice,Gsum)  as "
							+ "						select Gname,b.Gno,Knum,Gprice,Knum*Gprice "
							+ "						from  _Inventory a , _Goods  b "
							+ "							where  a.Gno = b.Gno and Sno ="+rs.getString(1));
					prst.execute();
				}
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