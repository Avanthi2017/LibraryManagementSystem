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
		<h4 class="modal-title">Edit Borrower</h4>
	</div>
	<form action="updateBorrower" method="post" id="myForm">
		<div class="modal-body">
			<p>Enter the details of borrower to update</p>
			<div class="form-group">
				<div class="col-lg-12">
			<input type="hidden" name="borrowerId" value="<%=borrower.getCardNo() %>">
		BorrowerName:<input class="form-control required" type="text" name="borrowerName" id="name" value="<%=borrower.getName()%>">
		BorrowerAddress:<input  type="text" name="borrowerAddress" class="form-control"  value="<%=borrower.getAddress()%>">
			BorrowerPhone:<input  type="text" name="borrowerPhone"  class="form-control" value="<%=borrower.getPhone()%>">
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
				var borrowerName = $('#name');

				// Check if there is an entered value
				if (!borrowerName.val()) {
					// Add errors highlight
					borrowerName.closest('.form-group')
							.removeClass('has-success').addClass('has-error');

					// Stop submission of the form
					e.preventDefault();
				} else {
					// Remove the errors highlight
					borrowerName.closest('.form-group').removeClass('has-error')
							.addClass('has-success');
				}
			});
</script>