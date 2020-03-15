function queryform() {
	var aptId=document.getElementById("idNum").value;
	var data = {
		aptid:aptId,
	};
	console.log(data);
	$.ajax({
		"url": "http://127.0.0.1:8081/form",
		"method": "POST",
		"dataType": "text",
		// 要发送给后端的数据参数，post时，数据必须写在data，get可以写在data,也可以跟在地址栏?号后面
		"data": data
	}).then(function(resp) { // ajax请求数据成功时会自动调用then方法的匿名函数
		console.log(resp); // 服务端返回的数据
	}).fail(function(error) { // ajax请求数据失败时会自动调用fail方法的匿名函数
		console.log(error);
	});	
}
