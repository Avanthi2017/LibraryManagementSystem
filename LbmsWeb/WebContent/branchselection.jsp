<%@include file="include.html"%>
<%@page import="com.gcit.lbms.service.BookService"%>
<%@page import="com.gcit.lbms.service.BranchService"%>
<%@page import="com.gcit.lbms.service.BookLoanService"%>
<%@page import="com.gcit.lbms.entity.BookLoan"%>
<%@page import="com.gcit.lbms.entity.Book"%>
<%@page import="com.gcit.lbms.entity.Branch"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
	BookLoanService bookLoanService = new BookLoanService();
	BranchService branchService = new BranchService();
	List<Branch> branchs = branchService.readAllBranches();
	BookService bookService = new BookService();
	Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
	List<BookLoan> bookLoans = bookLoanService.readActiveBookLoansbyCardNo(cardNo);
	if (request.getAttribute("message") != null) {
		out.println(request.getAttribute("message"));
	}
%>
<form action="branchSelection"  id="myForm">
	<input type="hidden" name="cardNo" value="<%=cardNo%>">
<p>
	Select Branch: <select id="branchId" name="branchId"
		class="selectpicker form-control">
		<%
			for (Branch branch : branchs) {
		%>
		<option value=<%=branch.getBranchId()%>><%=branch.getBranchName()%></option>
		<%
			}
		%>
	</select>
		<button type="submit" class="btn btn-primary">Submit</button>
	</p>
	</form>