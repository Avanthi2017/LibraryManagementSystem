<%@include file="include.html"%>
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
		<h4 class="modal-title">Edit Genre</h4>
	</div>
	<form action="updateGenre" method="post" id="myForm">
		<div class="modal-body">
			<p>Enter the details of genre to update</p>
			<div class="form-group">
				<div class="col-lg-12">
			<input type="hidden" name="genreId" value="<%=genre.getGenreId() %>">
			<input class="form-control required" type="text" name="genreName" id="name" value="<%=genre.getGenreName()%>">
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
				var genreName = $('#name');

				// Check if there is an entered value
				if (!genreName.val()) {
					// Add errors highlight
					genreName.closest('.form-group')
							.removeClass('has-success').addClass('has-error');

					// Stop submission of the form
					e.preventDefault();
				} else {
					// Remove the errors highlight
					genreName.closest('.form-group').removeClass('has-error')
							.addClass('has-success');
				}
			});
</script>