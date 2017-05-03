<%@include file="include.html" %>
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

	if (request.getAttribute("message") != null) {
		out.println(request.getAttribute("message"));
	}
%>
<div>

	<form action="addBook" method="post" id="myForm">

		<p>Enter New Book:</p>
		<div class="form-group">
			<div class="col-lg-12">
				<p>
					Title:<input class="form-control " type="text" required="required" name="bookName"
						id="bookName" value="">
				</p>
				<p>
					Publisher: <select id="publisherId" name="publisherId" required
						class="selectpicker form-control">
						<%
							for (Publisher publisher : publishers) {
						%>


						<option value=<%=publisher.getPublisherId()%>><%=publisher.getPublisherName()%></option>
						<%
							}
						%>

					</select>
				</p>
				<p>
					Authors: <select id="authorIds" name="authorIds" required
						class="selectpicker form-control" multiple>
						<%
							for (Author author : authors) {
						%>
						<option value=<%=author.getAuthorId()%>><%=author.getAuthorName()%></option>
						<%
							}
						%>
					</select>
				</p>
				<p>
					Genre: <select id="genreIds" name="genreIds" required
						class="selectpicker show-tick form-control" multiple>
						<%
							for (Genre genre : genres) {
						%>
						<option value=<%=genre.getGenreId()%>><%=genre.getGenreName()%></option>
						<%
							}
						%>
					</select>
				</p>
			</div>
		</div>
		<p> <button type="submit" class="btn btn-primary">Save</button></p>

	</form>
</div>


