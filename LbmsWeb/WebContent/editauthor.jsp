<%@page import="com.gcit.lbms.service.AuthorService"%>
<%@page import="com.gcit.lbms.entity.Author"%>
<%
	AuthorService service = new AuthorService();
	Author author = new Author();
	Integer authorId = Integer.parseInt(request.getParameter("authorId"));
	author = service.readAuthorById(authorId);
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
		<h4 class="modal-title">Edit Author</h4>
	</div>
	<form action="updateAuthor" method="post" id="myForm">
		<div class="modal-body">
			<p>Enter the details of author to update</p>
			<div class="form-group">
				<div class="col-lg-12">
			<input type="hidden" name="authorId" value="<%=author.getAuthorId() %>">
			<input class="form-control required" type="text" name="authorName" id="name" value="<%=author.getAuthorName()%>">
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
				var authorName = $('#name');

				// Check if there is an entered value
				if (!authorName.val()) {
					// Add errors highlight
					authorName.closest('.form-group')
							.removeClass('has-success').addClass('has-error');

					// Stop submission of the form
					e.preventDefault();
				} else {
					// Remove the errors highlight
					authorName.closest('.form-group').removeClass('has-error')
							.addClass('has-success');
				}
			});
</script>