<%@include file="include.html"%>
<%@page import="com.gcit.lbms.service.GenreService"%>
<%@page import="com.gcit.lbms.service.AuthorService"%>
<%@page import="com.gcit.lbms.service.BranchService"%>
<%@page import="com.gcit.lbms.service.PublisherService"%>
<%@page import="com.gcit.lbms.service.BookService"%>
<%@page import="com.gcit.lbms.entity.Book"%>
<%@page import="com.gcit.lbms.entity.Publisher"%>
<%@page import="com.gcit.lbms.entity.Branch"%>
<%@page import="com.gcit.lbms.entity.Author"%>
<%@page import="com.gcit.lbms.entity.Genre"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
	BookService service = new BookService();
	PublisherService publisherService = new PublisherService();
	BranchService branchService = new BranchService();
	AuthorService authorService = new AuthorService();
	GenreService genreService = new GenreService();
	List<Publisher> publishers = new ArrayList<>();
	publishers = publisherService.readAllPublishers();
	List<Author> authors = new ArrayList<>();
	authors = authorService.readAllAuthors();
	List<Genre> genres = new ArrayList<>();
	genres = genreService.readAllGenres();
	List<Branch> branchs = new ArrayList<>();
	branchs = branchService.readAllBranches();

	Book book = new Book();
	Integer bookId = Integer.parseInt(request.getParameter("bookId"));
	book = service.readBookByPk(bookId);
	if (request.getAttribute("message") != null) {
		out.println(request.getAttribute("message"));
	}
%>
<div>

	<form action="updateBook" method="post" id="myForm">

		<p>Enter the details of book to update</p>
		<div class="form-group">
			<div class="col-lg-12">
				<input type="hidden" name="bookId" value="<%=book.getBookId()%>">
				<p>
					Title:<input class="form-control " type="text" name="bookName"
						id="name" value="<%=book.getTitle()%>">
				</p>
				<p>
					Publisher: <select id="publisherId" name="publisherId"
						class="selectpicker form-control">
						<%
							for (Publisher publisher : publishers) {
						%>
						<%
							if (publisher.getPublisherName().equals(book.getPublisher().getPublisherName())) {
						%>
						<option value=<%=publisher.getPublisherId()%> selected><%=publisher.getPublisherName()%></option>
						<%
							} else {
						%>
						<option value=<%=publisher.getPublisherId()%>><%=publisher.getPublisherName()%></option>
						<%
							}
						%>

						<%
							}
						%>

					</select>
				</p>
				<p>

					Authors: <select id="authorIds" name="authorIds"
						class="selectpicker form-control" multiple>
						<%
							boolean flag = false;
							List<Author> bookAuthors = book.getAuthors();
							for (Author author : authors) {
								flag = false;
								for (Author a : bookAuthors) {
									if (author.getAuthorName().equals(a.getAuthorName())) {
										flag = true;
									}
								}
								if (flag) {
						%>
						<option value=<%=author.getAuthorId()%> selected><%=author.getAuthorName()%></option>
						<%
							} else {
						%>
						<option value=<%=author.getAuthorId()%>><%=author.getAuthorName()%></option>

						<%
							}
							}
						%>
					</select>
				</p>
				<p>
					Genre: <select id="genreIds" name="genreIds"
						class="selectpicker show-tick form-control" multiple>
						<%
							 
							List<Genre> bookgenres = book.getGenres();
							for (Genre genre : genres) {
								flag = false;
								for (Genre g : bookgenres) {
									if (genre.getGenreName().equals(g.getGenreName())) {
										flag = true;
									}
								}
								if (flag) {
						%>
						<option value=<%=genre.getGenreId()%> selected><%=genre.getGenreName()%></option>

						<%
							} else {
						%>
						<option value=<%=genre.getGenreId()%>><%=genre.getGenreName()%></option>
						<%
							}
							}
						%>
					</select>
				</p>
				<p>Branches:
				<div class="col-md-6">
					<table class="table table-striped" id="booksTable">
						<thead>
							<tr>
								<th>BranchName</th>
								<th>NoOfCopies</th>
							</tr>
						</thead>
						<tbody>
							<%
								branchs = branchService.readAllBranches();
								for (Branch branch : branchs) {
									int bookCopies = branchService.readBookCopiesByBookIdAndBranchId(book.getBookId(),
											branch.getBranchId());
							%>
							<tr>
								<input type="hidden" name="branchId" id="branchId"	value="<%=branch.getBranchId()%>">
								<td><%=branch.getBranchName()%></td>
								<td contenteditable='true'><input name="noOfCopies"
									id="noOfCopies" type="number" min="0" value=<%=bookCopies%>></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>


				</div>
			</div>
		</div>
		<button type="submit" class="btn btn-primary">Done</button>

	</form>
</div>
<script type="text/javascript">
	$(document).ready(function() { //launch this code after the whole DOM is loaded
		$("updateBooklib").submit(function(event) { // function to process submitted table
			var tableData = []; // we will store rows' data into this array
			$("#booksTable") // select table by id
			.find(".tableRow") // select rows by class
			.each(function() { // for each selected row extract data               
				var tableRow = {};
				var jRow = $(this);
				tableRow.branchId = jRow.find('td.branchId').text();
				tableRow.noOfCopies = jRow.find('td.noOfCopies').text();
				tableData.push(tableRow);
			});

			$.post("http://google.com", /*url of consuming servlet*/
			{
				tableData : tableData
			}, /*data*/
			function() {
				alert("Success!");
			}, /*function to execute in case of success*/
			"json" /* data type */
			);
			event.preventDefault(); //Prevent sending form by browser
		});

	});
</script>


