<html>
<head>
<meta charset="utf-8">
<title>Library Application</title>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/angular.js/1.2.1/angular.min.js"></script>
<script>
	var app = angular.module('libraryApp', []);
	app
			.controller(
					'libraryCtrl',
					function($scope, $http) {
						$scope.varHide = true;
						$scope.authorDetails = "David Chung :: email - dchung2005@gmail.com";
						$scope.displayDetails = function() {
							$scope.varHide = !$scope.varHide;
						}
						$scope.getBookLoanDetails = function() {
							$http.get(
								"http://localhost:8080/library/getLibraryMemberDetails/"
									+ $scope.memSelected.id).success(
								function(response) {
									$scope.bookLoanDetails = response
								});
						}
						$http
							.get(
								"http://localhost:8080/library/getAllLibraryMembers")
							.success(function(response) {
								$scope.libraryMembers = response;
							});
					});
</script>
</head>
<body>
	<div ng-app="libraryApp" ng-controller="libraryCtrl">
		<h2>Library Application</h2>
		<button ng-click="displayDetails()">Author Details</button>
		<br /> 
		<p ng-hide="varHide">
			{{authorDetails}}
		</p>

		<h3>Library Members</h3>
		<select ng-model="memSelected" ng-change="getBookLoanDetails()"
			ng-options="x.name for x in libraryMembers">
		</select>

		<div>
			<h3>Library member name: 
				<br />{{memSelected.name}}
			</h3>
		</div>
		<hr />
		<div>
			<h4>Books on loan:</h4>
			<table ng-repeat="bd in bookLoanDetails" style="border-bottom: 1px solid black">
				<tr>
					<td>Book Title:
					<td>
					<td>{{bd.title}}</td>
				</tr>
				<tr>
					<td>Author:
					<td>
					<td>{{bd.author}}</td>
				</tr>
				<tr>
					<td>ISBN:
					<td>
					<td>{{bd.isbn}}</td>
				</tr>
				<tr>
					<td>Due date
					<td>
					<td>{{bd.endDateAsString}}</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>