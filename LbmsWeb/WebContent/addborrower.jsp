
<%
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
		<h4 class="modal-title">Add New Borrower</h4>
	</div>

	<form action="addBorrower" method="post" id="myForm">
		<div class="modal-body">

			<p>Enter new borrower name</p>
			<div class="form-group">
				<div class="col-lg-12">
					borrowerName:<input type="text" class="form-control required"	id="name" name="borrowerName"> 
					borrowerAddress: <input type="text" class="form-control " name="borrowerAddress"> 
					borrowerPhone:<input type="text" class="form-control" name="borrowerPhone">
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default"
				class="form-control required" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save</button>
		</div>
	</form>
</div>
<script type="text/javascript">
	$('#myForm').on(
			'submit',
			function(e) {
				var borrowerName = $('#name');

				// Check if there is an entered value
				if (!borrowerName.val()) {
					// Add errors highlight
					borrowerName.closest('.form-group').removeClass(
							'has-success').addClass('has-error');

					// Stop submission of the form
					e.preventDefault();
				} else {
					// Remove the errors highlight
					borrowerName.closest('.form-group').removeClass(
							'has-error').addClass('has-success');
				}
			});
</script>