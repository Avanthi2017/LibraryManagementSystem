<%@page import="com.gcit.lbms.service.BookService"%>
<%@page import="com.gcit.lbms.entity.Book"%>
<%@page import="com.gcit.lbms.entity.Branch"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
	BookService service = new BookService();
	Book book = new Book();
	Integer bookId = Integer.parseInt(request.getParameter("bookId"));
	book = service.readBookByPk(bookId);
	if (request.getAttribute("message") != null) {
		out.println(request.getAttribute("message"));
	}
	
%>
<div>
	<div class="modal-header">
		<h4 class="modal-title">Delete Book</h4>
	</div>
	<form action="deleteBook">
		<div class="modal-body">
			<p>You have selected to delete the book</p>
			<input type="hidden" name="bookId" value="<%=book.getBookId()%>">
			<p> name:<%=book.getTitle()%></p>
			<table class="table table-striped" id="booksTable">
							<thead>
								<tr>
									<th>BranchName</th>
								</tr>
							</thead>
							<tbody>
								<%
										List<Branch> branchs = book.getBranchs();
										for (Branch branch : branchs) {
								%>
								<tr>
								    <input type="hidden" name="branchId" value="<%=branch.getBranchId() %>">
									<td><%=branch.getBranchName()%></td>
										
								</tr>
								<%
									}

								%>
							</tbody>
						</table>
		</div>
		<div class="modal-footer">
			<button type="submit" class="btn btn-primary">delete</button>
		</div>
	</form>
</div>
<!-- /.modal-content -->
