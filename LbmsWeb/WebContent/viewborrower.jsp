
<%@page import="com.gcit.lbms.service.BranchService"%>
<%@page import="com.gcit.lbms.service.BookService"%>
<%@page import="com.gcit.lbms.dao.BookDAO"%>
<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lbms.entity.Borrower"%>
<%@page import="com.gcit.lbms.service.BorrowerService"%>
<%@page import="com.gcit.lbms.entity.BookLoan"%>
<%
	BorrowerService service = new BorrowerService();
	List<Borrower> borrowers = new ArrayList<>();
	Integer borrowerCount = service.getBorrowerCount();
	Integer noOfPages = 0;
	if (borrowerCount % 10 > 0) {
		noOfPages = borrowerCount / 10 + 1;
	} else {
		noOfPages = borrowerCount / 10;
	}
	if (request.getAttribute("borrowers") != null) {
		borrowers = (List<Borrower>) request.getAttribute("borrowers");
	} else {
		borrowers = service.readAllBorrowers(1);
	}
%>
${message}

<script>
	function searchBorrowers() {
		$.ajax({
			url : "searchBorrowers",
			method : "post",
			data : {
				searchString : $('#searchString').val()
			}
		}).done(function(data) {
			$('#borrowersTable').html(data);
		});
	}
	$(document).ready(function() {
		$('.modal').on('hidden.bs.modal', function(e) {
			$(this).removeData();
		});
	});
</script>

<h1>GCIT Library Management System</h1>
<h2>Welcome to GCIT Library Management System. Have fun shopping
	books!!</h2>
<h3>Hello Admin - Below are the list of Borrowers in LMS.</h3>

<button type="button" class="btn btn-info" data-toggle="modal"
	data-target=".bs-example-modal-lg" href="addborrower.jsp">AddBorrower</button>
	<p><a href="admin.jsp" class="btn btn-info pull-right" role="button">Back</a></p>

<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">...</div>
	</div>
</div>
<script>
	function searchBorrowers() {
		$.ajax({
			url : "searchBorrowers",
			data : {
				searchString : $('#searchString').val()
			}

		}).done(function(data) {
			$('#borrowersTable').html(data);
		})
	}
</script>
<div class="input-group">
	<form action="searchBorrowers">
		<input type="text" name="searchString" id="searchString"
			class="form-control" placeholder="Search for..."
			oninput="searchBorrowers()">
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
		<li><a href="pageBorrowers?pageNo=<%=i%>"><%=i%></a></li>
		<%
			}
		%>

		<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
</nav>
<div class="col-md-6">
	<table class="table table-striped" id="borrowersTable" width= "5000" >
		<thead>
		<col width="50">
		<col width="600">
		<col width="600">
		<col width="5500">
		<col width="1500">
		<col width="50">
		<col width="50">
		<tr>
			<th>#</th>
			<th>Borrower Name</th>
			<th>Borrower Address</th>
			<th>Borrower Phone</th>
			<th>Books</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		</thead>
		<tbody>
			<%
				for (Borrower a : borrowers) {
			%>
			<tr>
				<td><%=borrowers.indexOf(a) + 1%></td>
				<td><%=a.getName()%></td>
				<td><%=a.getAddress()%></td>
				<td><%=a.getPhone()%></td>
				<td>
					<div class=scrollable>
						<%
							if (a.getBookloans() != null && !a.getBookloans().isEmpty()) {
									BookService bookService = new BookService();
									BranchService branchService = new BranchService();
									List<BookLoan> bookLoans = a.getBookloans();
									for (BookLoan b : bookLoans) {

										if (b.getBookId() != 0) {
											out.println("Book:" + bookService.readBookByPk(b.getBookId()).getTitle());
											out.println("Branch:" + branchService.readBranchById(b.getBranchId()).getBranchName());
											out.println("DateOut:" + b.getDateOut());
											out.println("DateIn:" + b.getDateIn());
											out.println("DueDate:" + b.getDueDate());
											out.println(", ");
										}
									}
								}
						%>
					</div>
				</td>
				<td><button type="button" class="btn btn-primary"
						data-toggle="modal" data-target="#editBorrowerModal"
						href="editborrower.jsp?borrowerId=<%=a.getCardNo()%>">update</button></td>
				<td><button type="button" class="btn btn-sm btn-danger"
						data-toggle="modal" data-target="#deleteBorrowerModal"
						href="deleteborrower.jsp?borrowerId=<%=a.getCardNo()%>">delete</button></td>
			</tr>
			<%
				}
			%>

		</tbody>
	</table>
</div>
<div class="modal fade delete-borrower-modal" id="deleteBorrowerModal"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document"></div>
	<div class="modal-content"></div>
</div>

<div class="modal fade edit-borrower-modal" id="editBorrowerModal"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content"></div>
	</div>
</div>




