<%@ page language="java" import="java.util.*,javabeans.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>报损记录查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="css/style.css">
	
	<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap-social.css" rel="stylesheet" type="text/css">	
	<link href="css/templatemo_style.css" rel="stylesheet" type="text/css">	
	<link rel="stylesheet" href="css/reset1.min.css">
	<link rel="stylesheet" href="css/style1.css">
	<%
	Vector<LossRecord> inv=null;
	int power=99;
	String pass;
	pass=(String)request.getAttribute("pass");
	if(pass!=null){
		response.sendRedirect("Doquery?type=q_lossRecord");
	}
	HttpSession ms=request.getSession();
	User us=new User();
	us=(User)ms.getAttribute("user");
	if(us!=null){
		if(us.getUgrade()==0){
			String grade=String.valueOf(us.getUgrade());
			power=Integer.parseInt(grade);//获取权限
		}/*
		else{
			response.sendRedirect("login.jsp");
		}*/
	}
	else{
		response.sendRedirect("login.jsp");
	} %>
	<%
	if(ms.getAttribute("lossRecord")!=null){
		inv=(Vector<LossRecord>)ms.getAttribute("lossRecord");
	}
	else{
		response.sendRedirect("login.jsp");
	}
	//ms.removeAttribute("username");//删除会话 %>
	<!--  
	<frameset rows="80,*">
		<frame src="head.jsp" name="top" scrolling="no" noresize="noresize">
		<frameset cols="30%,*">
			<frame src="login.jsp" name="menu" scrollong="no" noresize="noresize">
			<frame src="login.jsp" name="right" scrolling="auto" noresize="noresize">
		</frameset>
	</frameset>
	-->
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
<center>
<div class="show sss">
	<table>
		<thead>
			<tr>
				<th>记录编号</th>
				<th>仓库号</th>
				<th>仓库名</th>
				<th>管理员编号</th>
				<th>管理员姓名</th>
				<th>管理员电话</th>
				<th>员工编号</th>
				<th>员工姓名</th>
				<th>货物编号</th>
				<th>货物名称</th>
				<th>货物数量</th>
				<th>货物总价</th>
				<th>提交时间</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		<%int i,j=0;
		if(inv.size()!=0){
		for(i=0;i<inv.size();i++){ 
		System.out.println(i);%>
			<tr>
				<td><%=inv.get(i).getNo() %></td>
				<td><%=inv.get(i).getWrhs_no() %></td>
				<td><%=inv.get(i).getWrhs_name() %></td>
				<td><%=inv.get(i).getAd_no() %></td>
				<td><%=inv.get(i).getAd_name() %></td>
				<td><%=inv.get(i).getAd_tele() %></td>
				<td><%=inv.get(i).getWorker_no() %>
				<td><%=inv.get(i).getWorker_name() %>
				<td><%=inv.get(i).getGoods_no() %>
				<td><%=inv.get(i).getGoods_name() %>
				<td><%=inv.get(i).getAmount() %>
				<td><%=inv.get(i).getTotal() %>
				<td><%=inv.get(i).getDate() %>
				<td><%=inv.get(i).getBz() %>
			</tr>
			<%} 
			}%>
			</tbody>
	</table>

</div>
</center>


<script src="css/zepto.js"></script>
<script src="css/jquery.tablesort.js"></script>
<script type="text/javascript">

	$(function() {

		var table = $('<table></table>');
		$('table').tablesort().data('tablesort');

		$('thead th.number').data('sortBy', function(th, td, sorter) {
			return parseInt(td.text(), 10);
		});

	});
</script>
</body>
</html>
