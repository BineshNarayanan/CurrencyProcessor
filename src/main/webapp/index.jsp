<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Currency Processor</title>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container page-header well well-sm" style="text-align:center;background-color: rgb(183, 245, 183);">
    	<h3>Currency Processor</h3>
    </div>
	<br>
    <div class="container">
    	<ul class="nav nav-tabs">
		  <li class="active" id="navDashBoard" onclick="clickNavDashboard();"><a href="#">Dashboard</a></li>
		  <li id="navMessages" onclick="clickNavMessage();"><a href="#">Messages</a></li>
		  <li id="navPostMessage" onclick="clickNavPostMessage();"><a href="#">Post Message</a></li>
		</ul>
    </div>
    <div class="container" id="charts" style="display:block">
    	<br>
	    <div id="containerCurrencyFromConversionChart"></div>
		<div id="containerCurrencyToConversionChart"></div>
		<div id="containerOriginatingCountryChart"></div>
    </div>
    <div class="container table-responsive" id="messages" style="display:none">
    	<br>
    	<table class="table table-bordered" >
    		<tr>
    			<th>ID</th>
    			<th>UserID</th>
    			<th>Currency From</th>
    			<th>Currency To</th>
    			<th>Amount Sell</th>
    			<th>Amount Buy</th>
    			<th>Rate</th>
    			<th>Originating Country</th>
    			<th>Date Placed</th>
    		</tr>
    		<tbody id="tblBodyMessages"></tbody>
  		</table>
    </div>
	<div class="container" id="postMessage" style="display:none">
		<br>
		<div class="row">
			<div class="col-lg-12">
				<h5><small>Sample : {"userId": "134256", "currencyFrom": "EUR", "currencyTo": "GBP", "amountSell": 1000, "amountBuy": 747.10, "rate": 0.7471, "timePlaced" : "14-JAN-15 10:27:44", "originatingCountry" : "FR"}</small></h5>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-lg-10">
				<div class="input-group">
					<input type="text" class="form-control" name="message" id="txtMessage" placeholder="Paste the message here">
					<span class="input-group-btn">
						<button class="btn btn-primary" type="button" onclick="postData();">Post Message!</button>
					</span>
				</div>
			</div>
		</div>
		<br>
		<div class="row">
		  <div class="col-lg-8">
			<span id="retMessage" class="label label-success"></span>    
		  </div>
		</div>
	</div>
	
	
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	clickNavDashboard();
});

function clickNavMessage(){
	
	getAllMessages();
	$('#navMessages').removeClass('active').addClass('active');
	$('#navPostMessage').removeClass('active');
	$('#navDashBoard').removeClass('active');
	
	$("#messages").attr('style','display:block');
	$("#postMessage").attr('style','display:none');
	$("#charts").attr('style','display:none');
	$("#retMessage").html('');
	
}

function clickNavPostMessage(){
	
	$('#navMessages').removeClass('active');
	$('#navPostMessage').removeClass('active').addClass('active');
	$('#navDashBoard').removeClass('active');
	
	$("#messages").attr('style','display:none');
	$("#postMessage").attr('style','display:block');
	$("#charts").attr('style','display:none');
	$("#retMessage").html('');
	
}

function clickNavDashboard(){
	$('#navMessages').removeClass('active');
	$('#navPostMessage').removeClass('active');
	$('#navDashBoard').removeClass('active').addClass('active');
	
	$("#messages").attr('style','display:none');
	$("#postMessage").attr('style','display:none');
	$("#charts").attr('style','display:block');
	$("#retMessage").html('');
	renderDashboardCharts();
}


function postData() {
	$.ajax({
		url:'webapi/consumer/message',
		type:'POST',
		data: $("#txtMessage").val(),
		dataType: 'json',
		contentType: "application/json; charset=utf-8",
		success:function(res){
			$('#retMessage').html('The message was posted successfully');
			$('#retMessage').removeClass('label-danger');
		},
		error:function(res){
			$('#retMessage').html('There was problem while posting the message. The error is ' + res.responseText);
			$('#retMessage').addClass('label-danger');
		}
	});
	$("#txtMessage").val('');
	$("#txtMessage").focus();
}

function getAllMessages() {
	$.ajax({
		url:'webapi/consumer/message',
		type:'GET',
		data: '',
		dataType: 'json',
		contentType: "application/json; charset=utf-8",
		success:function(res){
			$("#tblBodyMessages").html('');
			for (var i = 0; i < res.allMessages.length; i++) {
				var row = $("<tr class='success'/>");
				rowData = JSON.parse(res.allMessages[i]);
			    $("#tblBodyMessages").append(row);
				row.append($("<td>" + rowData.id + "</td>"));
			    row.append($("<td>" + rowData.userId + "</td>"));
			    row.append($("<td>" + rowData.currencyFrom + "</td>"));
			    row.append($("<td>" + rowData.currencyTo + "</td>"));
			    row.append($("<td>" + rowData.amountSell + "</td>"));
			    row.append($("<td>" + rowData.amountBuy + "</td>"));
			    row.append($("<td>" + rowData.rate + "</td>"));
			    row.append($("<td>" + rowData.originatingCountry + "</td>"));
			    row.append($("<td>" + rowData.timePlaced + "</td>"));
			}
		},
		error:function(res){
			alert("Bad thing happend! " + res.statusText);
		}
	});
}

function renderDashboardCharts() {
	$.ajax({
		url:'webapi/consumer/messageDashboard/currencyConversionChart',
		type:'GET',
		data: '',
		dataType: 'json',
		contentType: "application/json; charset=utf-8",
		success:function(res){
			var objSeriesFrom = new Array();
			var objSeriesTo = new Array();
			
			var objKeysFrom = new Array();
			var objKeysTo = new Array();
			var objCountryKeys = new Array();
			for(var i = 0 ; i < res.currencyFromCodeWiseCount.length ; i++){
				obj = res.currencyFromCodeWiseCount[i];
				if(typeof(objSeriesFrom[obj.currencyCode]) != "undefined") {
					var seriesObj = objSeriesFrom[obj.currencyCode];
					var list = seriesObj.data;
					list.push(obj.value);
					seriesObj.data = list;
				} else {
					var list = new Array();
					list.push(obj.value);
					var seriesObj = new Object();
					seriesObj.name = obj.currencyCode;
					seriesObj.data = list;
					objSeriesFrom[obj.currencyCode] = seriesObj;
					objKeysFrom[objKeysFrom.length] = obj.currencyCode; 
				}
			}
			
			for(var i = 0 ; i < res.currencyToCodeWiseCount.length ; i++){
				obj = res.currencyToCodeWiseCount[i];
				if(typeof(objSeriesTo[obj.currencyCode]) != "undefined") {
					var seriesObj = objSeriesTo[obj.currencyCode];
					var list = seriesObj.data;
					list.push(obj.value);
					seriesObj.data = list;
				} else {
					var list = new Array();
					list.push(obj.value);
					var seriesObj = new Object();
					seriesObj.name = obj.currencyCode;
					seriesObj.data = list;
					objSeriesTo[obj.currencyCode] = seriesObj;
					objKeysTo[objKeysTo.length] = obj.currencyCode; 
				}
			}
			
			var objCountrySeries = new Array();
			for(var i = 0 ; i < res.countryWiseCount.length ; i++){
				obj = res.countryWiseCount[i];
				if(typeof(objCountrySeries[obj.currencyCode]) != "undefined") {
					var seriesObj = objCountrySeries[obj.currencyCode];
					var list = seriesObj.data;
					list.push(obj.value);
					seriesObj.data = list;
				} else {
					var list = new Array();
					list.push(obj.value);
					var seriesObj = new Object();
					seriesObj.name = obj.currencyCode;
					seriesObj.data = list;
					objCountrySeries[obj.currencyCode] = seriesObj;
					objCountryKeys[objCountryKeys.length] = obj.currencyCode;
				}
			}
			 
			var seriesFrom = new Array();
			for(var i = 0 ; i < objKeysFrom.length ; i++){
				seriesFrom.push(objSeriesFrom[objKeysFrom[i]]);
			}
			
			var seriesTo = new Array();
			for(var i = 0 ; i < objKeysTo.length ; i++){
				seriesTo.push(objSeriesTo[objKeysTo[i]]);
			}
			
			
			var seriesOriginatingCountry = new Array();
			for(var i = 0 ; i < objCountryKeys.length ; i++){
				seriesOriginatingCountry.push(objCountrySeries[objCountryKeys[i]]);
			}
			
			$('#containerCurrencyFromConversionChart').highcharts({
		        chart: {type: 'bar'},
		        title: {text: 'Currency From chart'},
		        xAxis: {categories: ['CurrencyFrom']},
		        yAxis: {min: 0,title: {text: 'Total Messages'}},
		        legend: {reversed: true},
		        plotOptions: {series: {stacking: 'normal'}},
 				series:seriesFrom
 			});
			
			$('#containerCurrencyToConversionChart').highcharts({
		        chart: {type: 'bar'},
		        title: {text: 'Currency To Chart'},
		        xAxis: {categories: ['CurrencyTo']},
		        yAxis: {min: 0,title: {text: 'Total Messages'}},
		        legend: {reversed: true},
		        plotOptions: {series: {stacking: 'normal'}},
 				series:seriesTo
 			});
			
			$('#containerOriginatingCountryChart').highcharts({
		        chart: {type: 'bar'},
		        title: {text: 'Order Origination Country chart'},
		        xAxis: {categories: ['OriginatingCountry']},
		        yAxis: {min: 0,title: {text: 'Total Messages'}},
		        legend: {reversed: true},
		        plotOptions: {series: {stacking: 'normal'}},
		        series:seriesOriginatingCountry});
		},
		error:function(res){
			alert("Bad thing happend! " + res.statusText);
		}
	});
}

</script>
</html>
