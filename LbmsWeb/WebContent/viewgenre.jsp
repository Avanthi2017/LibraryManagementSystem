<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lbms.entity.Genre"%>
<%@page import="com.gcit.lbms.service.GenreService"%>
<%@page import="com.gcit.lbms.entity.Book"%>
<%
	GenreService service = new GenreService();
	List<Genre> genres = new ArrayList<>();
	Integer genreCount = service.getGenreCount();
	Integer noOfPages = 0;
	if (genreCount % 10 > 0) {
		noOfPages = genreCount / 10 + 1;
	} else {
		noOfPages = genreCount / 10;
	}
	if (request.getAttribute("genres") != null) {
		genres = (List<Genre>) request.getAttribute("genres");
	} else {
		genres = service.readAllGenres(1);
	}
%>
${message}

<script>
	function searchGenres() {
		$.ajax({
			url : "searchGenres",
			method : "post",
			data : {
				searchString : $('#searchString').val()
			}
		}).done(function(data) {
			$('#genresTable').html(data);
		});
	}
	$(document).ready(function() {
		$('.modal').on('hidden.bs.modal', function(e) {
			$(this).removeData();
		});
	});
</script>

<button type="button" class="btn btn-info" data-toggle="modal"
	data-target=".bs-example-modal-lg" href="addgenre.jsp">AddGenre</button>
	<p><a href="admin.jsp" class="btn btn-info pull-right" role="button">Back</a></p>

<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">...</div>
	</div>
</div>
<script>
	function searchGenres() {
		$.ajax({
			url : "searchGenres",
			data : {
				searchString : $('#searchString').val()
			}
		
		}).done(function(data) {
			$('#genresTable').html(data);
		})
	}
</script>
<div class="input-group">
	<form action="searchGenres">
		<input type="text" name="searchString" id="searchString"
			class="form-control" placeholder="Search for..."
			oninput="searchGenres()">
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
		<li><a href="pageGenres?pageNo=<%=i%>"><%=i%></a></li>
		<%
			}
		%>

		<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
</nav>
<div class="col-md-6">
	<table class="table table-striped" id="genresTable">
		<thead>
		  <col width="50">
  		  <col width="600">
  		  <col width="1500">
  		  <col width="50">
			<tr>
				<th>#</th>
				<th>Genre Name</th>
				<th>Books</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Genre a : genres) {
			%>
			<tr>
				<td><%=genres.indexOf(a) + 1%></td>
				<td><%=a.getGenreName()%></td>

				<td>
				<div class=scrollable>
					<%
						if (a.getBooks() != null && !a.getBooks().isEmpty()) {
								List<Book> books = a.getBooks();
								for (Book b : books) {
									out.println(b.getTitle());
									out.println(", ");

								}
							}
					%>
					</div>
				</td>
				
				<td><button type="button" class="btn btn-primary"
						data-toggle="modal" data-target="#editGenreModal"
						href="editgenre.jsp?genreId=<%=a.getGenreId()%>">update</button></td>
				<td><button type="button" class="btn btn-sm btn-danger"
						data-toggle="modal" data-target="#deleteGenreModal"
						href="deletegenre.jsp?genreId=<%=a.getGenreId()%>">delete</button></td>
			</tr>
			<%
				}
			%>

		</tbody>
	</table>
</div>
<div class="modal fade delete-genre-modal" id="deleteGenreModal"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document"></div>
			<div class="modal-content"></div>
</div>

<div class="modal fade edit-genre-modal" id="editGenreModal"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content"></div>
	</div>
</div>




