<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!doctype html>
<html lang="en">

<head>
<title>Table</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

<body>
	<div class="col-10 m-auto">
		<div class="row">
			<c:if test="${not empty error}">
				<div class="alert alert-success">${error}</div>
			</c:if>
		</div>

		<div>
			<button class="btn btn-primary btn-lg">
				<a href="/home" style="color: white;">Table views</a>
			</button>

			<button class="btn btn-secondary btn-lg">
				<a href="/home/workingtime" style="color: white;">Working time</a>
			</button>
		</div>
		<div class="card ">

			<div class="card-header text-uppercase font-weight-bold ">
				<i class="fas fa-list"></i> Table views
			</div>

			<form action="/home/find" method="POST">
				<div class="input-group mb-3 col-10">

					<input type="text" class="form-control" placeholder="Name?"
						placeholder="Search" name="keywords" value="${keywords}">
					<div class="input-group-append">
						<button class="btn btn-outline-secondary" formaction="/home/find">Search</button>
					</div>
				</div>



				<div class="card-body p-0">
					<table class="table table-bordered table table-hover">
						<thead class="text-center bg-warning">
							<tr>
								<th scope="col" class="align-middle" rowspan="2">Name</th>
								<th scope="col" class="align-middle" rowspan="2">ID</th>
								<th scope="col" class="align-middle" rowspan="2">IsdBar</th>
								<th scope="col" class="align-middle" rowspan="2">DcName</th>
								<th scope="col" class="align-middle" colspan="3">Working
									time</th>

							</tr>
							<tr>
								<th scope="col">Star Date</th>
								<th scope="col">End Date</th>
								<th scope="col">#</th>
							</tr>
						</thead>
						<tbody style="text-align: center;">

							<c:forEach var="item" items="${data}">

								<tr>
									<td>${item.name}</td>
									<td>${item.id}</td>

									<td>${item.isdbar}</td>
									<td>${item.dcname}</td>

									<td>${item.startdate}</td>
									<td>${item.enddate}</td>
									<td><a href="#"> ## </a></td>
								</tr>
							</c:forEach>
							
						</tbody>
					</table>
				</div>
			</form>
			<!-- Copyright -->
			<div class="text-center p-4"
				style="background-color: rgba(0, 0, 0, 0.05);">
				© 2021 Copyright: <a class="text-reset fw-bold"
					href="https://mdbootstrap.com/">MDBootstrap.com</a>
			</div>
			<!-- Copyright -->
		</div>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>

</html>