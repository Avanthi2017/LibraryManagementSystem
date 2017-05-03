<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lbms.entity.Branch"%>
<%@page import="com.gcit.lbms.service.BranchService"%>
<%@page import="com.gcit.lbms.entity.Book"%>
<%@page import="com.gcit.lbms.entity.Borrower"%>
<%
	BranchService service = new BranchService();
	List<Branch> branchs = new ArrayList<>();
	Integer branchCount = service.getBranchCount();
	Integer noOfPages = 0;
	if (branchCount % 10 > 0) {
		noOfPages = branchCount / 10 + 1;
	} else {
		noOfPages = branchCount / 10;
	}
	if (request.getAttribute("branchs") != null) {
		branchs = (List<Branch>) request.getAttribute("branchs");
	} else {
		branchs = service.readAllBranches(1);
	}
%>
${message}

<script>
	function searchBranchs() {
		$.ajax({
			url : "searchBranchs",
			method : "post",
			data : {
				searchString : $('#searchString').val()
			}
		}).done(function(data) {
			$('#branchsTable').html(data);
		});
	}
	$(document).ready(function() {
		$('.modal').on('hidden.bs.modal', function(e) {
			$(this).removeData();
		});
	});
</script>

<button type="button" class="btn btn-info" data-toggle="modal"
	data-target=".bs-example-modal-lg" href="addbranch.jsp">AddBranch</button>
	<p><a href="admin.jsp" class="btn btn-info pull-right" role="button">Back</a></p>

<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">...</div>
	</div>
</div>
<script>
	function searchBranchs() {
		$.ajax({
			url : "searchBranchs",
			data : {
				searchString : $('#searchString').val()
			}
		
		}).done(function(data) {
			$('#branchsTable').html(data);
		})
	}
</script>
<div class="input-group">
	<form action="searchBranchs">
		<input type="text" name="searchString" id="searchString"
			class="form-control" placeholder="Search for..."
			oninput="searchBranchs()">
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
		<li><a href="pageBranchs?pageNo=<%=i%>"><%=i%></a></li>
		<%
			}
		%>

		<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
</nav>
<div class="col-md-6">
	<table class="table table-striped" id="branchsTable">
		<thead>
			<tr>
				<th>#</th>
				<th>Branch Name</th>
				<th>Branch Address</th>
				<th>Books</th>
				<th>Borrower</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Branch a : branchs) {
			%>
			<tr>
				<td><%=branchs.indexOf(a) + 1%></td>
				<td><%=a.getBranchName()%></td>
				<td><%=a.getBranchAddress()%></td>
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
				<td>
					<%
						if (a.getBorrowers() != null && !a.getBorrowers().isEmpty()) {
								List<Borrower> borrowers = a.getBorrowers();
								for (Borrower b : borrowers) {
									out.println(b.getName());
									out.println(", ");

								}
							}
					%>
				</td>
				<td><button type="button" class="btn btn-primary"
						data-toggle="modal" data-target="#editBranchModal"
						href="editbranch.jsp?branchId=<%=a.getBranchId()%>">update</button></td>
				<td><button type="button" class="btn btn-sm btn-danger"
						data-toggle="modal" data-target="#deleteBranchModal"
						href="deletebranch.jsp?branchId=<%=a.getBranchId()%>">delete</button></td>
			</tr>
			<%
				}
			%>

		</tbody>
	</table>
</div>
<div class="modal fade delete-branch-modal" id="deleteBranchModal"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document"></div>
			<div class="modal-content"></div>
</div>

<div class="modal fade edit-branch-modal" id="editBranchModal"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content"></div>
	</div>
</div>




