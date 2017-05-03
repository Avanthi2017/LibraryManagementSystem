<%@page import="com.gcit.lbms.service.BorrowerService"%>
<%@page import="com.gcit.lbms.entity.Borrower"%>
<%
	BorrowerService service = new BorrowerService();
	Borrower borrower= new Borrower();
	Integer borrowerId = Integer.parseInt(request.getParameter("borrowerId"));
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
		<h4 class="modal-title">Delete Borrower</h4>
	</div>
	<form action="deleteBorrower">
		<div class="modal-body">
			<p>You have selected to delete the borrower</p>
			<input type="hidden" name="borrowerId" value="<%=borrower.getCardNo() %>">
			<p>name:<%=borrower.getName()%></p>
			<p>address:<%=borrower.getAddress()%></p>
			<p>phone:<%=borrower.getPhone()%></p>
			
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">delete</button>
		</div>
	</form>
</div>
<!-- /.modal-content -->
