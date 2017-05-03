<%@page import="com.gcit.lbms.service.BookLoanService"%>
<%@page import="com.gcit.lbms.service.BorrowerService"%>
<%@page import="com.gcit.lbms.service.BookService"%>
<%@page import="com.gcit.lbms.service.BranchService"%>
<%@page import="com.gcit.lbms.entity.Borrower"%>
<%@page import="com.gcit.lbms.entity.Branch"%>
<%@page import="com.gcit.lbms.entity.Book"%>
<%@page import="com.gcit.lbms.entity.BookLoan"%>
<%
BorrowerService service = new BorrowerService();
BookService bookService= new BookService();
BranchService branchService= new BranchService();
BookLoanService bookLoanService = new BookLoanService();
Borrower borrower= new Borrower();
Branch branch= new Branch();
Book book= new Book();
BookLoan bookLoan= new BookLoan();

Integer borrowerId = Integer.parseInt(request.getParameter("borrowerId"));
Integer branchId = Integer.parseInt(request.getParameter("branchId"));
Integer bookId = Integer.parseInt(request.getParameter("bookId"));
bookLoan=bookLoanService.readBookLoansbyIds(borrowerId, branchId, bookId);
book=bookService.readBookByPk(bookId);
branch=branchService.readBranchById(branchId);

borrower = service.readBorrowerByCardNo(borrowerId);
if (request.getAttribute("message") != null) {
	out.println(request.getAttribute("message"));
}
%>
<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Edit BookLoan</h4>
	</div>
	<form action="updateBookLoan" method="post" id="myForm">
		<div class="modal-body">
			<p>Enter the number of days to override book</p>
			<div class="form-group">
				<div class="col-lg-12">
			<input type="hidden" name="borrowerId" value="<%=borrower.getCardNo() %>">
		BorrowerName:<input class="form-control required" type="text" name="borrowerName" id="name" value="<%=borrower.getName()%>">
			<input type="hidden" name="branchId" value="<%=branchId %>">
		BranchName:<input  type="text" name="branchName" disabled="disabled" class="form-control"  value="<%=branch.getBranchName()%>">
		<input type="hidden" name="bookId" value="<%=bookId %>">
			BookName:<input  type="text" name="bookName" disabled="disabled"class="form-control"  value="<%=book.getTitle()%>">
				DateOut:<input   name="dateOut" disabled="disabled"class="form-control"  value="<%=bookLoan.getDateOut()%>">
					DueDate:<input   name="dueDate" disabled="disabled" class="form-control"  value="<%=bookLoan.getDueDate()%>">
			NoOfDays:<input  type="number" name="noOfdays"  class="form-control" value=" ">
		</div>
		</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save changes</button>
		</div>
	</form>
</div>
<!-- /.modal-content -->
<script type="text/javascript">
	$('#myForm').on(
			'submit',
			function(e) {
				var noOfdays = $('#noOfdays');

				// Check if there is an entered value
				if (noOfdays.val()<0) {
					// Add errors highlight
					noOfdays.closest('.form-group')
							.removeClass('has-success').addClass('has-error');

					// Stop submission of the form
					e.preventDefault();
				} else {
					// Remove the errors highlight
					noOfdays.closest('.form-group').removeClass('has-error')
							.addClass('has-success');
				}
			});
</script>