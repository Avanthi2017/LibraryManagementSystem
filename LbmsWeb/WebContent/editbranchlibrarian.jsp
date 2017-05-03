<%@include file="include.html"%>
<%@page import="com.gcit.lbms.service.BranchService"%>
<%@page import="com.gcit.lbms.entity.Branch"%>
<%@page import="com.gcit.lbms.entity.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lbms.service.BookService"%>
<%
Integer branchId = Integer.parseInt(request.getParameter("branchId").trim());
BranchService service = new BranchService();
Branch branch= new Branch();
branch = service.readBranchById(branchId);
if (request.getAttribute("message") != null) {
	out.println(request.getAttribute("message"));
}
%>
<div>
	<div class="modal-header">
		<h4 class="modal-title">Edit Branch</h4>
	</div>
	<form action="updateBranchAndCopies" method="post" id="myForm">
		<div class="modal-body">
			<p>Enter the details of branch to update</p>
			<div class="form-group">
				<div class="col-lg-12">
			<input type="hidden" name="branchId" value="<%=branch.getBranchId() %>">
		BranchName:<input class="form-control required" type="text" name="branchName" id="name" value="<%=branch.getBranchName()%>">
		BranchAddress:<input  type="text" name="branchAddress" class="form-control"  value="<%=branch.getBranchAddress()%>">
					<div class="col-md-6">
						<table class="table table-striped" id="bookCopiesTable">
							<thead>
								<tr>
									<th>Book</th>
									<th>NoOfCopies</th>
								</tr>
							</thead>
							<tbody>
								<%
										 List<Book>books = branch.getBooks();
										for (Book b : books) {
											int bookCopies = service.readBookCopiesByBookIdAndBranchId(b.getBookId(),
													branch.getBranchId());
								%>
								<tr>
								    <input type="hidden" name="bookId" id="bookId" value="<%=b.getBookId() %>">
									<td><%=b.getTitle()%></td>
									<td contenteditable='true'>  <input name="noOfCopies" id="noOfCopies"type="number" min="1" value=<%=bookCopies%>></td>
								</tr>
								<%
									}

								%>
							</tbody>
						</table>
					</div>
		</div>
		</div>
		</div>
		<div class="modal-footer">
			<button type="submit" class="btn btn-primary">Done</button>
		</div>
	</form>
</div>
<!-- /.modal-content -->
<script type="text/javascript">
    $(document).ready(function () { //launch this code after the whole DOM is loaded
        $("updateBranchlib").submit(function (event) { // function to process submitted table
                    var tableData = []; // we will store rows' data into this array
                    $("#bookCopiesTable") // select table by id
                            .find(".tableRow") // select rows by class
                            .each(function () { // for each selected row extract data               
                                var tableRow = {};
                                var jRow = $(this);
                                tableRow.bookId = jRow.find('td.bookId').text();
                                tableRow.noOfCopies = jRow.find('td.noOfCopies').text();
                                tableData.push(tableRow);
                            });

                    $.post(
                            "http://google.com", /*url of consuming servlet*/
                            {tableData: tableData}, /*data*/
                            function () {
                                alert("Success!");
                            }, /*function to execute in case of success*/
                            "json" /* data type */
                    );
                    event.preventDefault(); //Prevent sending form by browser
                }
        );


    });
</script>
