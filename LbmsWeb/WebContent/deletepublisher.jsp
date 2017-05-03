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
		<h4 class="modal-title">Delete Publisher</h4>
	</div>
	<form action="deletePublisher">
		<div class="modal-body">
			<p>You have selected to delete the publisher</p>
			<input type="hidden" name="publisherId" value="<%=publisher.getPublisherId() %>">
			<p>name:<%=publisher.getPublisherName()%></p>
			<p>address:<%=publisher.getPublisherAddress()%></p>
			<p>phone:<%=publisher.getPublisherPhone()%></p>
			
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">delete</button>
		</div>
	</form>
</div>
<!-- /.modal-content -->
