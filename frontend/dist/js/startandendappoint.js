
var status="mid";

function startappoint() {
	if(status!='start')
	{
		status="start";
		
		
		var start = document.getElementById("startDate").value;
		var end = document.getElementById("endDate").value;
		var max = document.getElementById("maxNum").value;
		var data = {
			startDate:start,
			endDate:end,
			maxNum:max
		};
		
		console.log(data);
		$.ajax({
			"url": "http://127.0.0.1:3306/StarAppointServlet",
			"method": "POST",
			"dataType": "text",
			// 要发送给后端的数据参数，post时，数据必须写在data，get可以写在data,也可以跟在地址栏?号后面
			"data": data
		}).then(function(resp) { // ajax请求数据成功时会自动调用then方法的匿名函数
			alert(resp);
			console.log(resp); // 服务端返回的数据
		}).fail(function(error) { // ajax请求数据失败时会自动调用fail方法的匿名函数
			console.log(error);
		});	
		
	}
	else if(status=='start')
	{
		alert("本轮预约尚未结束，不能开始");
	}
	
}

function endappoint() {
	if(status=='start'){
		status='end';
		var data = {
				status:"end"
			};
			console.log(data);
			$.ajax({
				"url": "LotteryServlet",
				"method": "POST",
				"dataType": "text",
				// 要发送给后端的数据参数，post时，数据必须写在data，get可以写在data,也可以跟在地址栏?号后面
				"data": data
			}).then(function(resp) { // ajax请求数据成功时会自动调用then方法的匿名函数
				alert(resp);
				console.log(resp); // 服务端返回的数据
			}).fail(function(error) { // ajax请求数据失败时会自动调用fail方法的匿名函数
				console.log(error);
			});	
		
	}
	else if(status=='end'){
		alert("预约已经结束，不用再次结束");
	}
	else{
		alert("预约尚未开，始结束失败");
	}
}

function queryform() {
	var aptId=document.getElementById("aptid").value;
	var sN = $("#aptid");
	if(aptId == '' || aptId == undefined || aptId == null) {
		sN.css('background-color', 'rgba(255,0,0,0.1)');
		warning("未输入预约编号！");
		return false;
	}
	
	if(status=='end')
	{
		var data = {
				aptid:aptId,
			};
			console.log(data);
			$.ajax({
				"url": "query",
				"method": "POST",
				"dataType": "text",
				// 要发送给后端的数据参数，post时，数据必须写在data，get可以写在data,也可以跟在地址栏?号后面
				"data": data
			}).then(function(resp) { // ajax请求数据成功时会自动调用then方法的匿名函数
				alert(resp);
				console.log(resp); // 服务端返回的数据
			}).fail(function(error) { // ajax请求数据失败时会自动调用fail方法的匿名函数
				console.log(error);
			});	
	}
	else{
		alert("本轮预约尚未结束，不可查询");
	}
	
}
