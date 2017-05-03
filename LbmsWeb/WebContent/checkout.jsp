<%@include file="include.html"%>
<%@page import="com.gcit.lbms.service.BookService"%>
<%@page import="com.gcit.lbms.service.BranchService"%>
<%@page import="com.gcit.lbms.service.BookLoanService"%>
<%@page import="com.gcit.lbms.entity.BookCopies"%>
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
	Integer branchId = Integer.parseInt(request.getParameter("branchId"));
	List<BookCopies> bookcopies = branchService.readBookCopiesByBranchId(branchId);
	if (request.getAttribute("message") != null) {
		out.println(request.getAttribute("message"));
	}
%>
${message} 
<form action="bookSelection"  id="myForm">

<input type="hidden" name="cardNo" value="<%=cardNo%>">
<input type="hidden" name="branchId" value="<%=branchId%>">
<p>
	Select Book: <select id="bookId" name="bookId"
		class="selectpicker form-control">
		
		<%
			for (BookCopies bookcopie : bookcopies) {
		%>
	<option value=<%=bookcopie.getBookId()%>><%=bookService.readBookByPk(bookcopie.getBookId()).getTitle()%></option>
		
		<%
			}
		%>
	</select>
	
	<button type="submit" class="btn btn-primary">Checkout</button>
	</p>
	</form>
	<p><a href="admin.jsp" class="btn btn-info pull-right" role="button">Back</a></p>