<%@include file="include.html" %>
<%@page import="com.gcit.lbms.service.BranchService"%>
<%@page import="com.gcit.lbms.entity.Branch"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@include file="include.html"%>
<%@page import="com.gcit.lbms.service.BookService"%>
<%@page import="com.gcit.lbms.entity.Book"%>
<%
	Integer bookId =(int)(request.getAttribute("bookId"));
	if (request.getAttribute("message") != null) {
		out.println(request.getAttribute("message"));
	}
	BranchService branchService = new BranchService();
	List<Branch> branchs = branchService.readAllBranches();
%>
<form action="addBookCopies" method="post" id="myForm">
<table class="table table-striped" id="addbookCopiesTable">
	<thead>
		<tr>
			<th>Branch</th>
			<th>NofCopies</th>
		</tr>
	</thead>
	<tbody>
		<%
			for (Branch branch : branchs) {
				int bookCopies = branchService.readBookCopiesByBookIdAndBranchId(bookId,branch.getBranchId());
		%>
		<tr>
		
		<input type="hidden" name="branchId" value="<%=branch.getBranchId()%>">
		<td><%=branch.getBranchName()%></td>
		<input type="hidden" name="bookId" value="<%=bookId %>">
		<td contenteditable='true'>  <input name="noOfCopies" id="noOfCopies"type="number" min="0" value=<%=bookCopies%>></td>
		</tr>
		<%
			}
		%>
	
	</tbody>

</table>
	<button type="submit" class="btn btn-primary">Done</button>
		
</form>
<script type="text/javascript">
    $(document).ready(function () { //launch this code after the whole DOM is loaded
        $("addBookCopies").submit(function (event) { // function to process submitted table
                    var tableData = []; // we will store rows' data into this array
                    $("#addbookCopiesTable") // select table by id
                            .find(".tableRow") // select rows by class
                            .each(function () { // for each selected row extract data               
                                var tableRow = {};
                                var jRow = $(this);
                                tableRow.branchId = jRow.find('td.branchId').text();
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