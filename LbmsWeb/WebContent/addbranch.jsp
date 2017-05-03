
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
		<h4 class="modal-title">Add New Branch</h4>
	</div>

	<form action="addBranch" method="post" id="myForm">
		<div class="modal-body">

			<p>Enter new branch name</p>
			<div class="form-group">
				<div class="col-lg-12">
					branchName:<input type="text" class="form-control required"	id="name" name="branchName"> 
					branchAddress: <input type="text" class="form-control " name="branchAddress"> 
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
				var branchName = $('#name');

				// Check if there is an entered value
				if (!branchName.val()) {
					// Add errors highlight
					branchName.closest('.form-group').removeClass(
							'has-success').addClass('has-error');

					// Stop submission of the form
					e.preventDefault();
				} else {
					// Remove the errors highlight
					branchName.closest('.form-group').removeClass(
							'has-error').addClass('has-success');
				}
			});
</script>