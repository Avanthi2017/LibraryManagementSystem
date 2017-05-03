
<%@page import="com.gcit.lbms.service.BranchService"%>
<%@page import="com.gcit.lbms.entity.Branch"%>
<%@page import="com.gcit.lbms.entity.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lbms.service.BookService"%>
<%
	Integer branchId = Integer.parseInt(request.getParameter("branchId").trim());
	BranchService service = new BranchService();
	Branch branch = new Branch();
	branch = service.readBranchById(branchId);
	if (request.getAttribute("message") != null) {
		out.println(request.getAttribute("message"));
	}
%>
<form action="updatebranch" method="post" id="myForm">
	<p>Enter the details of branch to update</p>
	<input type="hidden" name="branchId" value="<%=branch.getBranchId()%>">
	<p> BranchName:</p>
	<input class="form-control required" type="text"
		name="branchName" id="name" value="<%=branch.getBranchName()%>">
	<p>BranchAddress:</p> 
	<input type="text" name="branchAddress"
		class="form-control" value="<%=branch.getBranchAddress()%>">

</form>
