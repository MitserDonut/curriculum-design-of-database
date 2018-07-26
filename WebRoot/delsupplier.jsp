<%@ page language="java" import="java.util.*,javabeans.*,java.sql.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册管理员</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="css/style.css">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="css/templatemo_style.css" rel="stylesheet" type="text/css">	
	
	<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap-social.css" rel="stylesheet" type="text/css">	
	
	<%
	int power=99;
	HttpSession ms=request.getSession();
	User us=new User();
	us=(User)ms.getAttribute("user");
	if(us!=null){
	String grade=String.valueOf(us.getUgrade());
	power=Integer.parseInt(grade);//获取权限
	}
	else{
		response.sendRedirect("login.jsp");
	} %>
	<%
	//ms.removeAttribute("username");//删除会话 %>
  </head>
  
  <body class="templatemo-bg-image-1">
<div style="text-align:center;clear:both">
<script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>
<script src="/follow.js" type="text/javascript"></script>
</div>
 <ul id="menu">
     <li ><a href="#" class="drop">首页</a>
     </li>
     
    <li><a href="#" class="drop">查询与修改</a>
        <div class="dropdown_1column">
                <div class="col_1">
                    <ul class="simple">
                    <%if(power==1)
                    {
                    	out.print("<li><a href="+"Doquery?type=q_wrhsInfo"+">仓库信息</a></li>");
                    	out.print("<li><a href="+"Doquery?type=q_adminInfo"+">管理员信息</a></li>");
                    } %>
                        <li><a href="Doquery?type=q_inven">库存状态</a></li>
                        <li><a href="Doquery?type=q_workerInfo">员工信息</a></li>
                        <li><a href="Doquery?type=q_clientInfo">客户信息</a></li>
                        <li><a href="Doquery?type=q_supplierInfo">供应商信息</a></li>
                        <li><a href="Doquery?type=q_goodsList">货物列表</a></li>
                        <li><a href="Doquery?type=q_outRecord">出库记录</a></li>
                        <li><a href="Doquery?type=q_inRecord">入库记录</a></li>
                        <li><a href="Doquery?type=q_lossRecord">报损记录</a></li>
                        
                    </ul> 
                </div>
        </div>
    </li>
 
    <li><a href="#" class="drop">注册</a>   
        <div class="dropdown_1column">      
                <div class="col_1">               
                    <ul class="simple">
                    <%if(power==1)
                    {
                    	out.print("<li><a href="+"regwarehouse.jsp"+">注册仓库</a></li>");
                    	out.print("<li><a href="+"regadmin.jsp"+">注册管理员</a></li>");
                    	out.print("<li><a href="+"regworker.jsp"+">注册员工</a></li>");
                    } %>
                        <li><a href="regclient.jsp">注册客户</a></li>
                        <li><a href="regsupplier.jsp">注册供应商</a></li>
                        <li><a href="reggoods.jsp">注册货物</a></li>
                    </ul>                         
                </div>                
        </div>
    </li>
 
     <li><a href="#" class="drop">删除</a>
        <div class="dropdown_1column">
                <div class="col_1">
                    <ul class="simple"><%if(power==1)
                    {
                    	out.print("<li><a href="+"delwarehouse.jsp"+">删除仓库</a></li>");
                    	out.print("<li><a href="+"deladmin.jsp"+">删除管理员</a></li>");
                    	out.print("<li><a href="+"delworker.jsp"+">删除员工</a></li>");
                    } %>
                    <li><a href="delclient.jsp">删除客户</a></li>
                          <li><a href="delsupplier.jsp">删除供应商</a></li>
                                <li><a href="delgoods.jsp">删除货物</a></li>
                    </ul>   
                </div>
        </div>
        </li>
      
          
    <li><a href="#" class="drop">库存更新</a>
        <div class="dropdown_1column">
                <div class="col_1">
                    <ul class="simple">
                        <li><a href="regin.jsp">入库</a></li>
                        <li><a href="outreg.jsp">出库</a></li>
                        <li><a href="regLoss.jsp">报损</a></li>
                    </ul>   
                </div>
        </div>
    </li>
      
     <li class="menu_right"><a href="Logout" class="drop">注销</a>
     </li>
</ul>
<br/>
<br/>
<br/>
<br/><br/>
<br/><br/>
<br/>

<h1 class="margin-bottom-18">删除供应商</h1>
	<div class="container">
		<div class="col-md-12">			
			<form class="form-horizontal templatemo-create-account templatemo-container" role="form" action="supplierdel" method="post">
				<div class="form-inner">
					
			       
			          <div class="form-group">
			          <div class="col-md-12">		          	
			           
			            <label for="admin" class="control-label center">供应商id</label>
			          <select style="width:100px;" name = "admin">
			          <% 
			          Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
     				Connection conn= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=CangKu;user=sa;password=123");
     
			          String sql = "select Spno,Spname from _Supplier";
    		 		Statement stmt=conn.createStatement();
    				 ResultSet rs = stmt.executeQuery(sql);
    				 while(rs.next()){%>    				
						<option value ="<%=rs.getString(1)+" "+rs.getString(2) %>"  > <%=rs.getString(1)+" "+rs.getString(2)   %></option>
			      	 	 <%}	stmt.close();
			      	 		rs.close();
			      	 		conn.close();	%>	 
			          	
				</select>			        		            
			          </div>
			          </div>	
			        
			   
			        <div class="form-group">
			          <div class="col-md-12">
			            <input type="submit" value="删除" class="btn btn-info">
			        
			          </div>
			        </div>	
				</div>				    	
		      </form>		      
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>

</body>
</html>