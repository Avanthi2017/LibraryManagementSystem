<%@page import="com.gcit.lbms.service.BranchService"%>
<%@page import="com.gcit.lbms.entity.BookCopies"%>
<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lbms.entity.Book"%>
<%@page import="com.gcit.lbms.service.BookService"%>
<%@page import="com.gcit.lbms.entity.Genre"%>
<%@page import="com.gcit.lbms.entity.Author"%>
<%@page import="com.gcit.lbms.entity.Branch"%>
<%
	BookService service = new BookService();
	List<Book> books = new ArrayList<>();
	Integer bookCount = service.getBookCount();
	Integer noOfPages = 0;
	if (bookCount % 10 > 0) {
		noOfPages = bookCount / 10 + 1;
	} else {
		noOfPages = bookCount / 10;
	}
	if (request.getAttribute("books") != null) {
		books = (List<Book>) request.getAttribute("books");
	} else {
		books = service.readAllBooks(1);
	}
%>
${message}

<script>
	function searchBooks() {
		$.ajax({
			url : "searchBooks",
			method : "post",
			data : {
				searchString : $('#searchString').val()
			}
		}).done(function(data) {
			$('#booksTable').html(data);
		});
	}
	$(document).ready(function() {
		$('.modal').on('hidden.bs.modal', function(e) {
			$(this).removeData();
		});
	});
</script>
<p><a href="addbook.jsp" class="btn btn-info pull-center" role="button">AddBook</a></p>
<p><a href="admin.jsp" class="btn btn-info pull-right" role="button">Back</a></p>

<script>
	function searchBooks() {
		$.ajax({
			url : "searchBooks",
			data : {
				searchString : $('#searchString').val()
			}
		
		}).done(function(data) {
			$('#booksTable').html(data);
		})
	}
</script>
<div class="input-group">
	<form action="searchBooks">
		<input type="text" name="searchString" id="searchString"
			class="form-control" placeholder="Search for..."
			oninput="searchBooks()">
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
		<li><a href="pageBooks?pageNo=<%=i%>"><%=i%></a></li>
		<%
			}
		%>

		<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
</nav>
<div class="col-md-6">

	<table class="table table-striped" id="booksTable" >
		<thead>
			<tr>
				<th>#</th>
				<th>Book Name</th>
				<th>Publisher</th>
				<th>Genre</th>
				<th>Author</th>
				<th>Branch</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Book a : books) {
			%>
			<tr>
				<td><%=books.indexOf(a) + 1%></td>
				<td><%=a.getTitle()%></td>
				<td><%=a.getPublisher().getPublisherName()%></td>
				<td> 
				<div class=scrollable>
					<%
						if (a.getGenres() != null && !a.getGenres().isEmpty()) {
								List<Genre> genres = a.getGenres();
								for (Genre g : genres) {
									out.println(g.getGenreName());
									out.println(", ");

								}
							}
					%>
					</div>
				</td>
				<td>
				<div class=scrollable>
					<%
						if (a.getAuthors() != null && !a.getAuthors().isEmpty()) {
								List<Author> authors = a.getAuthors();
								for (Author author : authors) {
									out.println(author.getAuthorName());
									out.println(", ");

								}
							}
					%>
					</div>
				</td>
				<td>
				<div class=scrollable>
					<%
					BranchService branchService= new BranchService();
						if (a.getBranchs() != null && !a.getBranchs().isEmpty()) {
								List<Branch> branchs = a.getBranchs();
								for (Branch branch : branchs) {
									int bookCopies=branchService.readBookCopiesByBookIdAndBranchId(a.getBookId(),branch.getBranchId());
									if(bookCopies>0)
									{
										out.println("Branch:"+branch.getBranchName());
										out.println("NoOfCopies:"+ bookCopies);
									}
									
									out.println(", ");

								}
							}
					%>
					</div>
				</td>
			<td><a href="editbook.jsp?bookId=<%=a.getBookId()%>" class="btn btn-info" role="button">update</a></td>
				
				<td><button type="button" class="btn btn-sm btn-danger"
						data-toggle="modal" data-target="#deleteBookModal"
						href="deletebook.jsp?bookId=<%=a.getBookId()%>">delete</button></td>
			</tr>
			<%
				}
			%>

		</tbody>
	</table>
</div>
<div class="modal fade delete-book-modal" id="deleteBookModal"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document"></div>
			<div class="modal-content"></div>
</div>





