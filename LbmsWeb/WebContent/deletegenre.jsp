<%@page import="com.gcit.lbms.service.GenreService"%>
<%@page import="com.gcit.lbms.entity.Genre"%>
<%
	GenreService service = new GenreService();
	Genre genre= new Genre();
	Integer genreId = Integer.parseInt(request.getParameter("genreId"));
	genre = service.readGenreById(genreId);
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
		<h4 class="modal-title">Delete Genre</h4>
	</div>
	<form action="deleteGenre">
		<div class="modal-body">
			<p>You have selected to delete the genre</p>
			<input type="hidden" name="genreId" value="<%=genre.getGenreId() %>">
			<p><%=genre.getGenreName()%></p>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">delete</button>
		</div>
	</form>
</div>
<!-- /.modal-content -->
