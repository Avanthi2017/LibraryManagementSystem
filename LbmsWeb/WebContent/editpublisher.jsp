<%@include file="include.html"%>
<%@page import="com.gcit.lbms.service.PublisherService"%>
<%@page import="com.gcit.lbms.entity.Publisher"%>
<%
PublisherService service = new PublisherService();
Publisher publisher= new Publisher();
Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
publisher = service.readPublisherById(publisherId);
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
		<h4 class="modal-title">Edit Publisher</h4>
	</div>
	<form action="updatePublisher" method="post" id="myForm">
		<div class="modal-body">
			<p>Enter the details of publisher to update</p>
			<div class="form-group">
				<div class="col-lg-12">
			<input type="hidden" name="publisherId" value="<%=publisher.getPublisherId() %>">
			<input class="form-control required" type="text" name="publisherName" id="name" value="<%=publisher.getPublisherName()%>">
			<input  type="text" name="publisherAddress" class="form-control"  value="<%=publisher.getPublisherAddress()%>">
			<input  type="text" name="publisherPhone"  class="form-control" value="<%=publisher.getPublisherPhone()%>">
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
				var publisherName = $('#name');

				// Check if there is an entered value
				if (!publisherName.val()) {
					// Add errors highlight
					publisherName.closest('.form-group')
							.removeClass('has-success').addClass('has-error');

					// Stop submission of the form
					e.preventDefault();
				} else {
					// Remove the errors highlight
					publisherName.closest('.form-group').removeClass('has-error')
							.addClass('has-success');
				}
			});
</script>