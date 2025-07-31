<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%     /* 자바 코드 사용 가능 */
	String kname = request.getParameter("kname");


	String id = "root";
	String pw = "1234";
	String url = "jdbc:mysql://localhost:3305/DoItSQL";
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String sql = "select * from order0729";
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	con = DriverManager.getConnection(url,id,pw);
	stmt = con.createStatement();
	rs =stmt.executeQuery(sql);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table, th , td {
border: 1px solid #css;
border-collapse: collapse;
}
th,td{padding:10px 20px;}
</style>
</head>
<body>
<h1>
<%= kname %>님, 환영합니다.
</h1>
<table border=1>
<%	
	while(rs.next()) {
		
		String orderId= rs.getString("order_id");
		String product= rs.getString("product");
		int quantity= rs.getInt("quantity");
		
%>
	<tr>
	<td> <%=orderId %></td>	
	<td> <%=product %></td>	
	<td> <%=quantity %></td>	
	</tr>
<%		
		}
%>
</table>

</body>
</html>