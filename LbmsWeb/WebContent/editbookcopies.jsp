<%@page import="com.gcit.lbms.service.BranchService"%>
<%@page import="com.gcit.lbms.service.GenreService"%>
<%@page import="com.gcit.lbms.entity.Branch"%>
<%
BranchService service = new BranchService();
Branch branch= new Branch();
Integer bookId = Integer.parseInt(request.getParameter("bookId").trim());
Integer branchId = Integer.parseInt(request.getParameter("branchId").trim());
branch = service.readBranchById(branchId);
int bookCopies = service.readBookCopiesByBookIdAndBranchId(bookId,branchId);
if (request.getAttribute("message") != null) {
	out.println(request.getAttribute("message"));
}
%>
<div>
	
	<form action="updateBookCopies" method="post" id="myForm">
		<div class="modal-body">
			<p>Enter Copies</p>
			<div class="form-group">
				<div class="col-lg-12">
			<input type="hidden" name="branchId" value="<%=branchId %>">
				<input type="hidden" name="bookId" value="<%=bookId %>">
			<input class="form-control required" type="text" name="branchName" id="name" value="<%=branch.getBranchName()%>">
				<input class="form-control required" type="text" name="noOfCopies" id="noOfCopies" value="<%=bookCopies%>">
		</div>
		</div>
		</div>
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">submit</button>

	</form>
</div>
