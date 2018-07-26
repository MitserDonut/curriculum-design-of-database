package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabeans.*;

/**
 * Servlet implementation class Doquery
 */
@WebServlet("/Doquery")
public class Doquery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection conn=null;
    PreparedStatement prst=null;
    ResultSet rs=null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Doquery() {
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
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		conn=DBTool.getConnection();
		String sql;
		HttpSession hs=request.getSession();
		User ur=(User) hs.getAttribute("user");
		String type=request.getParameter("type");
		
		int grade=0;
		grade=ur.getUgrade();
		
		if(type.equals("q_inven")){//库存查询
			if(grade==1){//boss
				sql="select*from V_Inventory";
			}
			else{
				sql="select*from V_Inventory where Wid=?";
			}
				Vector<Inventory>inv=new Vector<Inventory>();
				try {
					prst=conn.prepareStatement(sql);
					if(grade!=1){
						prst.setInt(1, ur.getUwh());
					}
					rs=prst.executeQuery();
					while(rs.next()){

						Inventory ity=new Inventory();
						ity.setWrhs_no(rs.getInt("Wid"));
						ity.setGoods_name(rs.getString("Gname"));
						ity.setGoods_no(rs.getInt("Gid"));
						ity.setAmount(rs.getInt("Gnum"));
						ity.setGoods_price(rs.getFloat("Gprice"));
						ity.setTotal(rs.getFloat("Gsum"));
						inv.add(inv.size(),ity);
					}
					hs.setAttribute("Inventory", inv);
					request.setAttribute("pass", "pass");
					response.sendRedirect("queryInve.jsp");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally{
					DBTool.close(rs, prst, conn);
				}
			
		 }
		else if(type.equals("q_workerInfo")){//员工信息查询
			/*if(grade==1){//boss*/
			sql="select*from _Worker";
		/*}*/
		/*else{
			sql="select*from V_Inventory where Wid=?";
		}*/
			Vector<WorkerInfo>clt=new Vector<WorkerInfo>();
			try {
				prst=conn.prepareStatement(sql);
				/*if(grade!=1){
					prst.setInt(1, ur.getUwh());
				}*/
				rs=prst.executeQuery();
				while(rs.next()){

					WorkerInfo ity=new WorkerInfo();
					ity.setWk_no(rs.getInt("Wno"));
					ity.setWk_name(rs.getString("Wname"));
					ity.setWk_sex(rs.getString("Wsex"));
					ity.setWk_tete(rs.getString("Wcall"));
					//ity.setWk_place(rs.getInt("Sno"));
					clt.add(clt.size(),ity);
				}
				hs.setAttribute("workerInfo", clt);
				request.setAttribute("pass", "pass");
				response.sendRedirect("queryWorker.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				DBTool.close(rs, prst, conn);
			}
		}
		
		
		//客户
		else if(type.equals("q_clientInfo")){
			/*if(grade==1){//boss*/
				sql="select*from _Client";
			/*}*/
			/*else{
				sql="select*from V_Inventory where Wid=?";
			}*/
				Vector<ClientInfo>clt=new Vector<ClientInfo>();
				try {
					prst=conn.prepareStatement(sql);
					/*if(grade!=1){
						prst.setInt(1, ur.getUwh());
					}*/
					rs=prst.executeQuery();
					while(rs.next()){

						ClientInfo ity=new ClientInfo();
						ity.setClt_no(rs.getInt("Cno"));
						ity.setClt_name(rs.getString("Cname"));
						ity.setClt_type(rs.getString("Ctype"));
						ity.setClt_tele(rs.getString("Ccall"));
						ity.setClt_sex(rs.getString("Csex"));
						ity.setClt_bz(rs.getString("Cbz"));
						clt.add(clt.size(),ity);
					}
					hs.setAttribute("ClientInfo", clt);
					request.setAttribute("pass", "pass");
					response.sendRedirect("queryClient.jsp");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally{
					DBTool.close(rs, prst, conn);
				}
		}
		
		
		
		//供应商
		else if(type.equals("q_supplierInfo")){
			/*if(grade==1){//boss*/
			sql="select*from _Supplier";
		/*}*/
		/*else{
			sql="select*from V_Inventory where Wid=?";
		}*/
			Vector<SupplierInfo>clt=new Vector<SupplierInfo>();
			try {
				prst=conn.prepareStatement(sql);
				/*if(grade!=1){
					prst.setInt(1, ur.getUwh());
				}*/
				rs=prst.executeQuery();
				while(rs.next()){

					SupplierInfo ity=new SupplierInfo();
					ity.setSup_no(rs.getInt("Spno"));
					ity.setSup_name(rs.getString("Spname"));
					ity.setSup_type(rs.getString("Ssex"));
					ity.setSup_tele(rs.getString("Scall"));
					ity.setSup_bz(rs.getString("Spbz"));
					clt.add(clt.size(),ity);
				}
				hs.setAttribute("supplierInfo", clt);
				request.setAttribute("pass", "pass");
				response.sendRedirect("querySupplier.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				DBTool.close(rs, prst, conn);
			}
		}
		
		
		//仓库
		else if(type.equals("q_wrhsInfo")){
			if(grade==1){//boss
			sql="select*from _Warehouse";
		}
		else{
			sql="select*from _Warehouse where Sno=?";
		}
			Vector<WarehouseInfo>clt=new Vector<WarehouseInfo>();
			try {
				prst=conn.prepareStatement(sql);
				if(grade!=1){
					prst.setInt(1, ur.getUwh());
				}
				rs=prst.executeQuery();
				while(rs.next()){

					WarehouseInfo ity=new WarehouseInfo();
					ity.setWh_no(rs.getInt("Sno"));
					ity.setWh_name(rs.getString("Sname"));
					ity.setWh_adrs(rs.getString("Sadress"));
					ity.setWh_contain(rs.getInt("Sbig"));
					clt.add(clt.size(),ity);
				}
				
				for(int i=0;i<clt.size();i++){
					String ad="";
					String sql1="select*from _Admin where Sno=?";
					prst=conn.prepareStatement(sql1);
					prst.setInt(1, clt.get(i).getWh_no());
					rs=prst.executeQuery();
					while(rs.next()){
						ad+=rs.getString("Aname");
						ad+=";";
					}
					clt.get(i).setAdmins(ad);
				}
				
				hs.setAttribute("warehouseInfo", clt);
				request.setAttribute("pass", "pass");
				response.sendRedirect("queryWarehouse.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				DBTool.close(rs, prst, conn);
			}
		}
		
		
		//管理员
		else if(type.equals("q_adminInfo")){
			/*if(grade==1){//boss*/
			sql="select*from _Admin";
		/*}*/
		/*else{
			sql="select*from V_Inventory where Wid=?";
		}*/
			Vector<AdminInfo>clt=new Vector<AdminInfo>();
			try {
				prst=conn.prepareStatement(sql);
				/*if(grade!=1){
					prst.setInt(1, ur.getUwh());
				}*/
				rs=prst.executeQuery();
				while(rs.next()){

					AdminInfo ity=new AdminInfo();
					ity.setAd_no(rs.getInt("Ano"));
					ity.setAd_name(rs.getString("Aname"));
					ity.setAd_sex(rs.getString("Asex"));
					ity.setAd_tele(rs.getString("Acall"));
					ity.setAd_belong(rs.getInt("Sno"));
					clt.add(clt.size(),ity);
				}
				
				for(int i=0;i<clt.size();i++){
					String ad="";
					String sql1="select*from _Warehouse where Sno=?";
					prst=conn.prepareStatement(sql1);
					prst.setInt(1, clt.get(i).getAd_belong());
					rs=prst.executeQuery();
					while(rs.next()){
						clt.get(i).setAd_bname(rs.getString("Sname"));
					}
					
				}
				
				hs.setAttribute("adminInfo", clt);
				request.setAttribute("pass", "pass");
				response.sendRedirect("queryAdmin.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				DBTool.close(rs, prst, conn);
			}
		}
		
		
		//货物基本信息
		else if(type.equals("q_goodsList")){
			/*if(grade==1){//boss*/
			sql="select*from _Goods";
		/*}*/
		/*else{
			sql="select*from V_Inventory where Wid=?";
		}*/
			Vector<GoodsInfo>clt=new Vector<GoodsInfo>();
			try {
				prst=conn.prepareStatement(sql);
				/*if(grade!=1){
					prst.setInt(1, ur.getUwh());
				}*/
				rs=prst.executeQuery();
				while(rs.next()){

					GoodsInfo ity=new GoodsInfo();
					ity.setGoods_no(rs.getInt("Gno"));
					ity.setGoods_name(rs.getString("Gname"));
					ity.setGoods_fresh(rs.getString("Gdate"));
					ity.setGoods_factory(rs.getString("Gfactory"));
					ity.setGoods_price(rs.getFloat("Gprice"));
					clt.add(clt.size(),ity);
				}
				hs.setAttribute("goodsInfo", clt);
				request.setAttribute("pass", "pass");
				response.sendRedirect("queryGoods.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				DBTool.close(rs, prst, conn);
			}
		}
		
		//出库
		else if(type.equals("q_outRecord")){
			if(grade==1){//boss*/
			sql="select*from _Out";
		}
		else{
			sql="select*from _Out where Sno=?";
		}
			Vector<IORecord>clt=new Vector<IORecord>();
			try {
				prst=conn.prepareStatement(sql);
				if(grade!=1){
					prst.setInt(1, ur.getUwh());
				}
				rs=prst.executeQuery();
				while(rs.next()){

					IORecord ity=new IORecord();
					
					ity.setGoods_no(rs.getInt("Gno"));
					ity.setWorker_no(rs.getInt("Wno"));
					ity.setWrhs_no(rs.getInt("Sno"));
					ity.setAd_no(rs.getInt("Ano"));
					ity.setDate(rs.getString("Odate"));
					ity.setRole(rs.getInt("Cno"));
					ity.setAdrs(rs.getString("Oaddr"));
					ity.setAmount(rs.getInt("Onum"));
					ity.setBz(rs.getString("Obz"));
					ity.setNo(rs.getInt("Ono"));
					clt.add(clt.size(),ity);
				}
				
				String sql1="select*from _Admin where Ano=?";
				String sql2="select*from _Warehouse where Sno=?";
				String sql3="select*from _Worker where Wno=?";
				String sql4="select*from _Client where Cno=?";
				String sql5="select*from _Goods where Gno=?";
				
				for(int i=0;i<clt.size();i++){
					System.out.println(clt.get(i).getAd_no());
					prst=conn.prepareStatement(sql1);
					prst.setInt(1, clt.get(i).getAd_no());
					rs=prst.executeQuery();
					while(rs.next()){
					clt.get(i).setAd_name(rs.getString("Aname"));
					clt.get(i).setAd_tele(rs.getString("Acall"));
					}
					
					prst=conn.prepareStatement(sql2);
					prst.setInt(1, clt.get(i).getWrhs_no());
					rs=prst.executeQuery();
					while(rs.next()){
					clt.get(i).setWrhs_name(rs.getString("Sname"));
					}
					
					
					prst=conn.prepareStatement(sql3);
					prst.setInt(1, clt.get(i).getWorker_no());
					rs=prst.executeQuery();
					while(rs.next()){
					clt.get(i).setWorker_name(rs.getString("Wname"));
					}
					
					prst=conn.prepareStatement(sql4);
					prst.setInt(1, clt.get(i).getRole());
					rs=prst.executeQuery();
					while(rs.next()){
					clt.get(i).setRole_name(rs.getString("Cname"));
					}
					
					prst=conn.prepareStatement(sql5);
					prst.setInt(1, clt.get(i).getGoods_no());
					rs=prst.executeQuery();
					while(rs.next()){
					clt.get(i).setGoods_name(rs.getString("Gname"));
					clt.get(i).setTotal(clt.get(i).getAmount()*rs.getFloat("Gprice"));
					}
				}
				
				hs.setAttribute("outRecord", clt);
				request.setAttribute("pass", "pass");
				response.sendRedirect("queryOut.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendRedirect("error.jsp");
			}
			finally{
				DBTool.close(rs, prst, conn);
			}
		}
		
		//入库
		else if(type.equals("q_inRecord")){
			if(grade==1){//boss*/
				sql="select*from _In";
			}
			else{
				sql="select*from _In where Sno=?";
			}
				Vector<IORecord>clt=new Vector<IORecord>();
				try {
					prst=conn.prepareStatement(sql);
					if(grade!=1){
						prst.setInt(1, ur.getUwh());
					}
					rs=prst.executeQuery();
					while(rs.next()){

						IORecord ity=new IORecord();
						
						ity.setGoods_no(rs.getInt("Gno"));
						ity.setWorker_no(rs.getInt("Wno"));
						ity.setWrhs_no(rs.getInt("Sno"));
						ity.setAd_no(rs.getInt("Ano"));
						ity.setRole(rs.getInt("Sno"));
						ity.setAdrs(rs.getString("Iaddr"));
						ity.setBz(rs.getString("Ibz"));
						ity.setNo(rs.getInt("Ino"));
						ity.setDate(rs.getString("Idate"));
						ity.setAmount(rs.getInt("Inum"));
						clt.add(clt.size(),ity);
					}
					
					String sql1="select*from _Admin where Ano=?";
					String sql2="select*from _Warehouse where Sno=?";
					String sql3="select*from _Worker where Wno=?";
					String sql4="select*from _Client where Cno=?";
					String sql5="select*from _Goods where Gno=?";
					
					for(int i=0;i<clt.size();i++){
						System.out.println(clt.get(i).getAd_no());
						prst=conn.prepareStatement(sql1);
						prst.setInt(1, clt.get(i).getAd_no());
						rs=prst.executeQuery();
						while(rs.next()){
						clt.get(i).setAd_name(rs.getString("Aname"));
						clt.get(i).setAd_tele(rs.getString("Acall"));
						}
						
						prst=conn.prepareStatement(sql2);
						prst.setInt(1, clt.get(i).getWrhs_no());
						rs=prst.executeQuery();
						while(rs.next()){
						clt.get(i).setWrhs_name(rs.getString("Sname"));
						}
						
						
						prst=conn.prepareStatement(sql3);
						prst.setInt(1, clt.get(i).getWorker_no());
						rs=prst.executeQuery();
						while(rs.next()){
						clt.get(i).setWorker_name(rs.getString("Wname"));
						}
						
						prst=conn.prepareStatement(sql4);
						prst.setInt(1, clt.get(i).getRole());
						rs=prst.executeQuery();
						while(rs.next()){
						clt.get(i).setRole_name(rs.getString("Cname"));
						}
						
						prst=conn.prepareStatement(sql5);
						prst.setInt(1, clt.get(i).getGoods_no());
						rs=prst.executeQuery();
						while(rs.next()){
						clt.get(i).setGoods_name(rs.getString("Gname"));
						clt.get(i).setTotal(clt.get(i).getAmount()*rs.getFloat("Gprice"));
						}
					}
					
					hs.setAttribute("inRecord", clt);
					request.setAttribute("pass", "pass");
					response.sendRedirect("queryIn.jsp");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendRedirect("error.jsp");
				}
				finally{
					DBTool.close(rs, prst, conn);
				}
		}
		
		
		else if(type.equals("q_lossRecord")){
			if(grade==1){//boss*/
				sql="select*from _Loss";
			}
			else{
				sql="select*from _Loss where Sno=?";
			}
				Vector<LossRecord>clt=new Vector<LossRecord>();
				try {
					prst=conn.prepareStatement(sql);
					if(grade!=1){
						prst.setInt(1, ur.getUwh());
					}
					rs=prst.executeQuery();
					while(rs.next()){

						LossRecord ity=new LossRecord();
						
						ity.setGoods_no(rs.getInt("Gno"));
						ity.setWorker_no(rs.getInt("Wno"));
						ity.setWrhs_no(rs.getInt("Sno"));
						ity.setAd_no(rs.getInt("Ano"));
						ity.setDate(rs.getString("Ltime"));
						ity.setAmount(rs.getInt("Lnum"));
						ity.setBz(rs.getString("Lbz"));
						ity.setNo(rs.getInt("Lno"));
						clt.add(clt.size(),ity);
					}
					
					String sql1="select*from _Admin where Ano=?";
					String sql2="select*from _Warehouse where Sno=?";
					String sql3="select*from _Worker where Wno=?";
					String sql5="select*from _Goods where Gno=?";
					
					for(int i=0;i<clt.size();i++){
						System.out.println(clt.get(i).getAd_no());
						prst=conn.prepareStatement(sql1);
						prst.setInt(1, clt.get(i).getAd_no());
						rs=prst.executeQuery();
						while(rs.next()){
						clt.get(i).setAd_name(rs.getString("Aname"));
						clt.get(i).setAd_tele(rs.getString("Acall"));
						}
						
						prst=conn.prepareStatement(sql2);
						prst.setInt(1, clt.get(i).getWrhs_no());
						rs=prst.executeQuery();
						while(rs.next()){
						clt.get(i).setWrhs_name(rs.getString("Sname"));
						}
						
						
						prst=conn.prepareStatement(sql3);
						prst.setInt(1, clt.get(i).getWorker_no());
						rs=prst.executeQuery();
						while(rs.next()){
						clt.get(i).setWorker_name(rs.getString("Wname"));
						}
						
						prst=conn.prepareStatement(sql5);
						prst.setInt(1, clt.get(i).getGoods_no());
						rs=prst.executeQuery();
						while(rs.next()){
						clt.get(i).setGoods_name(rs.getString("Gname"));
						clt.get(i).setTotal(clt.get(i).getAmount()*rs.getFloat("Gprice"));
						}
					}
					
					hs.setAttribute("lossRecord", clt);
					request.setAttribute("pass", "pass");
					response.sendRedirect("queryLoss.jsp");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendRedirect("error.jsp");
				}
				finally{
					DBTool.close(rs, prst, conn);
				}
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
