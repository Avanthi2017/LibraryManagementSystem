<%@include file="include.html" %>
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
	BookService bookService = new BookService();
	Integer cardNo = Integer.parseInt(request.getParameter("cardNo").trim());
	System.out.println("checkin"+cardNo);
	List<BookLoan> bookLoans = bookLoanService.readActiveBookLoansbyCardNo(cardNo);
	if (request.getAttribute("message") != null) {
		out.println(request.getAttribute("message"));
	}
%>

<div class="col-md-6">
<form action="bookCheckIn"  id="myForm">
	<table class="table table-striped" id="booksTable">
		<thead>
			<tr>
				<th>BookName</th>
				<th>BranchName</th>
				<th>DateOut</th>
				<th>DueDate</th>
				<th>CheckIn</th>
			</tr>
		</thead>
		<tbody>
			<%  
			Book book=null;
			Branch branch=null;
				for (BookLoan bookLoan : bookLoans) {
					 book=bookService.readBookByPk(bookLoan.getBookId());
					 branch=branchService.readBranchById(bookLoan.getBranchId());
			%>
			<tr>
				<input type="hidden" name="cardNo" value="	<%=cardNo%>">
				<input type="hidden" name="bookId" value="<%=book.getBookId()%>">
				<td><%=book.getTitle()%></td>
				<input type="hidden" name="branchId" value="<%=branch.getBranchId()%>">
				<td><%=branch.getBranchName()%></td>
				<td><%=bookLoan.getDateOut() %>
				<td><%=bookLoan.getDueDate() %>
				<td><input type="submit" value=checkin></td>
			</tr>
			
			<%
				}
			%>
		</tbody>
	</table>
	</form>
					<a href="branchselection.jsp?cardNo=<%=cardNo%>"
					class="btn btn-info" role="button">CheckOutBook</a>
					<p><a href="Welcome.jsp" class="btn btn-info pull-right" role="button">Back</a></p>

</div>
