<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lbms.entity.Author"%>
<%@page import="com.gcit.lbms.service.AuthorService"%>
<%@page import="com.gcit.lbms.entity.Book"%>
<%
	AuthorService service = new AuthorService();
	List<Author> authors = new ArrayList<>();
	Integer authoursCount = service.getAuthoursCount();
	Integer noOfPages = 0;
	if (authoursCount % 10 > 0) {
		noOfPages = authoursCount / 10 + 1;
	} else {
		noOfPages = authoursCount / 10;
	}
	if (request.getAttribute("authors") != null) {
		authors = (List<Author>) request.getAttribute("authors");
	} else {
		authors = service.readAllAuthors(1);
	}
%>
${message}

<script>
	function searchAuthors() {
		$.ajax({
			url : "searchAuthors",
			method : "post",
			data : {
				searchString : $('#searchString').val()
			}
		}).done(function(data) {
			$('#authorsTable').html(data);
		});
	}
	$(document).ready(function() {
		$('.modal').on('hidden.bs.modal', function(e) {
			$(this).removeData();
		});
	});
</script>

<h1>GCIT Library Management System</h1>
<h2>Welcome to GCIT Library Management System. Have fun shopping
	books!!</h2>
<h3>Hello Admin - Below are the list of Authors in LMS.</h3>

<p><a href="addauthor.jsp" class="btn btn-info pull-center" role="button">AddBook</a></p>
<p><a href="admin.jsp" class="btn btn-info pull-right" role="button">Back</a></p>

<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">...</div>
	</div>
</div>
<script>
	function searchAuthors() {
		$.ajax({
			url : "searchAuthors",
			data : {
				searchString : $('#searchString').val()
			}
		
		}).done(function(data) {
			$('#authorsTable').html(data);
		})
	}
</script>
<div class="input-group">
	<form action="searchAuthors">
		<input type="text" name="searchString" id="searchString"
			class="form-control" placeholder="Search for..."
			oninput="searchAuthors()">
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
		<li><a href="pageAuthors?pageNo=<%=i%>"><%=i%></a></li>
		<%
			}
		%>

		<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
</nav>
<div class="col-md-6">
	<table class="table table-striped" id="authorsTable">
		<thead>
			<tr>
				<th>#</th>
				<th>Author Name</th>
				<th>Books Written</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Author a : authors) {
			%>
			<tr>
				<td><%=authors.indexOf(a) + 1%></td>
				<td><%=a.getAuthorName()%></td>

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
						data-toggle="modal" data-target="#editAuthourModal"
						href="editauthor.jsp?authorId=<%=a.getAuthorId()%>">update</button></td>
				<td><button type="button" class="btn btn-sm btn-danger"
						data-toggle="modal" data-target="#deleteAuthourModal"
						href="deleteauthor.jsp?authorId=<%=a.getAuthorId()%>">delete</button></td>
			</tr>
			<%
				}
			%>

		</tbody>
	</table>
</div>
<div class="modal fade delete-authour-modal" id="deleteAuthourModal"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document"></div>
			<div class="modal-content"></div>
</div>

<div class="modal fade edit-authour-modal" id="editAuthourModal"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content"></div>
	</div>
</div>




