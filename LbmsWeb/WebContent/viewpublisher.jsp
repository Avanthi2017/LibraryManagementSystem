<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lbms.entity.Publisher"%>
<%@page import="com.gcit.lbms.service.PublisherService"%>
<%@page import="com.gcit.lbms.entity.Book"%>
<%
	PublisherService service = new PublisherService();
	List<Publisher> publishers = new ArrayList<>();
	Integer publisherCount = service.getPublisherCount();
	Integer noOfPages = 0;
	if (publisherCount % 10 > 0) {
		noOfPages = publisherCount / 10 + 1;
	} else {
		noOfPages = publisherCount / 10;
	}
	if (request.getAttribute("publishers") != null) {
		publishers = (List<Publisher>) request.getAttribute("publishers");
	} else {
		publishers = service.readAllPublishers(1);
	}
%>
${message}

<script>
	function searchPublishers() {
		$.ajax({
			url : "searchPublishers",
			method : "post",
			data : {
				searchString : $('#searchString').val()
			}
		}).done(function(data) {
			$('#publishersTable').html(data);
		});
	}
	$(document).ready(function() {
		$('.modal').on('hidden.bs.modal', function(e) {
			$(this).removeData();
		});
	});
</script>

<button type="button" class="btn btn-info" data-toggle="modal"
	data-target=".bs-example-modal-lg" href="addpublisher.jsp">AddPublisher</button>
	<p><a href="admin.jsp" class="btn btn-info pull-right" role="button">Back</a></p>

<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">...</div>
	</div>
</div>
<script>
	function searchPublishers() {
		$.ajax({
			url : "searchPublishers",
			data : {
				searchString : $('#searchString').val()
			}
		
		}).done(function(data) {
			$('#publishersTable').html(data);
		})
	}
</script>
<div class="input-group">
	<form action="searchPublishers">
		<input type="text" name="searchString" id="searchString"
			class="form-control" placeholder="Search for..."
			oninput="searchPublishers()">
	</form>
</div>
<!-- /input-group -->

<nav aria-label="Page navigation">
	<ul class="pagination">
		<li><a href="#" aria-label="Previous"> <span
				aria-hidden="true">&laquo;</span>
		</a></li>
		<%
			for (int i = 1; i <= noOfPages; i++) {
		%>
		<li><a href="pagePublishers?pageNo=<%=i%>"><%=i%></a></li>
		<%
			}
		%>

		<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
</nav>
<div class="col-md-6">
	<table class="table table-striped" id="publishersTable">
		<thead>
			<tr>
				<th>#</th>
				<th>Publisher Name</th>
				<th>Publisher Address</th>
				<th>Publisher Phone</th>
				<th>Books</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Publisher a : publishers) {
			%>
			<tr>
				<td><%=publishers.indexOf(a) + 1%></td>
				<td><%=a.getPublisherName()%></td>
				<td><%=a.getPublisherAddress()%></td>
				<td><%=a.getPublisherPhone()%></td>
				<td>
					<%
						if (a.getBooks() != null && !a.getBooks().isEmpty()) {
								List<Book> books = a.getBooks();
								for (Book b : books) {
									out.println(b.getTitle());
									out.println(", ");

								}
							}
					%>
				</td>
				<td><button type="button" class="btn btn-primary"
						data-toggle="modal" data-target="#editPublisherModal"
						href="editpublisher.jsp?publisherId=<%=a.getPublisherId()%>">update</button></td>
				<td><button type="button" class="btn btn-sm btn-danger"
						data-toggle="modal" data-target="#deletePublisherModal"
						href="deletepublisher.jsp?publisherId=<%=a.getPublisherId()%>">delete</button></td>
			</tr>
			<%
				}
			%>

		</tbody>
	</table>
</div>
<div class="modal fade delete-publisher-modal" id="deletePublisherModal"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document"></div>
			<div class="modal-content"></div>
</div>

<div class="modal fade edit-publisher-modal" id="editPublisherModal"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content"></div>
	</div>
</div>




