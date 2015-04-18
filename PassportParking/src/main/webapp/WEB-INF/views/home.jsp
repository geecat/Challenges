<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home</title>

<script src="<c:url value="/resources/js/jquery-1.11.0.min.js" />"></script>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/cover.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

<script type="text/javascript">
	$(document).ready(function() {

		$("#addResult").click(function() {
			if ($("#numAdd1").val() == "" || $("#numAdd2").val() == "") {
				$("#message").html('Please enter a valid number');
				$("#message").css({
					'color' : 'red'
				});
			} else {
				callMe("${pageContext.request.contextPath}/calculateAdd", $("#numAdd1").val(), $("#numAdd2").val());
			}

		});

		$("#subResult").click(function() {
			if ($("#numSub1").val() == "" || $("#numSub2").val() == "") {
				$("#message").html('Please enter a valid number');
				$("#message").css({
					'color' : 'red'
				});
			} else {
				callMe("${pageContext.request.contextPath}/calculateSub", $("#numSub1").val(), $("#numSub2").val());
			}

		});

		$("#mulResult").click(function() {
			if ($("#numMul1").val() == "" || $("#numMul2").val() == "") {
				$("#message").html('Please enter a valid number');
				$("#message").css({
					'color' : 'red'
				});
			} else {
				callMe("${pageContext.request.contextPath}/calculateMul", $("#numMul1").val(), $("#numMul2").val());
			}

		});

		$("#devideResult").click(function() {
			if ($("#numDivide1").val() == "" || $("#numDivide2").val() == "") {
				$("#message").html('Please enter a valid number');
				$("#message").css({
					'color' : 'red'
				});
			} else {
				callMe("${pageContext.request.contextPath}/calculateDivide", $("#numDivide1").val(), $("#numDivide2").val());
			}

		});

		function callMe(url, var1, var2) {
			$.ajax({
				url : url,
				type : "post",
				contentType : "application/json",
				data : JSON.stringify({
					num1 : var1,
					num2 : var2
				}),
				success : function(response) {
					$("#message").html('');
					$("#result").html(response.data.result);

					$("#liveFeed").html('(Live Calc feed from topdown)');
					$("#liveFeed").css({
						'height' : '350px',
						'width' : '500px',
						'overflow' : 'scroll',
						'overflow-x':'hidden'

					});
					var list = response.data.history;
					
					for (var i = 0, l = list.length; i < l; i++) {
						$("<p>" + list[i] + "</p>").appendTo("#liveFeed");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					var msg;
					try {
						msg = jqXHR.responseJSON.message;
					} catch (err) {
						msg = 'Internal Server Error occured.';
					}

					$("#message").html(msg);
					$("#message").css({
						'color' : 'red'
					});
				}
			});
		}
		;
		window.setInterval(function() {
			Refresh();
		}, 5000);

		function Refresh() {
			$.ajax({
				url : "${pageContext.request.contextPath}/calculateRefresh",
				type : "post",
				contentType : "application/json",
				success : function(response) {
					$("#message").html('');
					$("#liveFeed").html('(Live Calc feed from topdown)');
					$("#liveFeed").css({
						'height' : '350px',
						'width' : '500px',
						'overflow' : 'scroll',
						'overflow-x':'hidden'

					});
					var list = response.data.history;
					
					for (var i = 0, l = list.length; i < l; i++) {
						$("<p>" + list[i] + "</p>").appendTo("#liveFeed");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					var msg;
					try {
						msg = jqXHR.responseJSON.message;
					} catch (err) {
						msg = 'Internal Server Error occured.';
					}

					$("#message").html(msg);
					$("#message").css({
						'color' : 'red'
					});
				}
			});
		}
		;
	});
</script>
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<div class="site-wrapper">

	<div class="site-wrapper-inner">

		<div class="cover-container">

			<div class="masthead clearfix">
				<div class="inner">
					<h3 class="masthead-brand">Passport Parking</h3>
					<ul class="nav masthead-nav">
						<li class="active"><a href="#">Home</a></li>
					</ul>
				</div>
			</div>

			<div class="inner cover">
				<h1 class="cover-heading">The Social Calculator</h1>
				<div id="message"></div>

				<table style="width: 500px">
					<tr>
						<th>Number 1</th>
						<th>Number 2</th>
						<th>Operation</th>
						<th>Result</th>
					</tr>
					<tr>
					<tr>
						<td><input type="number" id="numAdd1" name="numAdd1" placeholder="Number 1"></td>
						<td><input type="number" id="numAdd2" name="numAdd2" placeholder="Number 2"></td>
						<td><input type="submit" id="addResult" value="+"> =</td>
						<td rowspan="4"><div id="result"></div></td>
					</tr>
					<tr>
						<td><input type="number" id="numSub1" name="numSub1" placeholder="Number 1"></td>
						<td><input type="number" id="numSub2" name="numSub2" placeholder="Number 2"></td>
						<td><input type="submit" id="subResult" value="-"> =</td>
					</tr>
					<tr>
						<td><input type="number" id="numMul1" name="numMul1" placeholder="Number 1"></td>
						<td><input type="number" id="numMul2" name="numMul2" placeholder="Number 2"></td>
						<td><input type="submit" id="mulResult" value="*"> =</td>
					</tr>
					<tr>
						<td><input type="number" id="numDivide1" name="numDivide1" placeholder="Number 1"></td>
						<td><input type="number" id="numDivide2" name="numDivide2" placeholder="Number 2"></td>
						<td><input type="submit" id="devideResult" value="/"> =</td>
					</tr>
					<tr>
						<td colspan="4"><div id="liveFeed" style="margin-top: 10px">(Live Calc feed from topdown)</div></td>
						
					</tr>
				</table>

			</div>



		</div>

	</div>

</div>
</html>
