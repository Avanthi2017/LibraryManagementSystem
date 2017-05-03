<%@page import="com.gcit.lbms.service.BranchService"%>
<%@page import="com.gcit.lbms.entity.Branch"%>
<%
	BranchService service = new BranchService();
	Branch branch= new Branch();
	Integer branchId = Integer.parseInt(request.getParameter("branchId"));
	branch = service.readBranchById(branchId);
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
		<h4 class="modal-title">Delete Branch</h4>
	</div>
	<form action="deleteBranch">
		<div class="modal-body">
			<p>You have selected to delete the branch</p>
			<input type="hidden" name="branchId" value="<%=branch.getBranchId() %>">
			<p>name:<%=branch.getBranchName()%></p>
			<p>address:<%=branch.getBranchAddress()%></p>
			
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">delete</button>
		</div>
	</form>
</div>
<!-- /.modal-content -->
