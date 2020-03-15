function sendform() {
	var idnum=document.getElementById("idNum").value;
	var telnum=document.getElementById("telNum").value;
	var aptnum=document.getElementById("aptNum").value;
	var iN = $("#idNum");
	var tN = $("#telNum");

	if ((idnum == '' || idnum == undefined || idnum == null)||(telnum == '' || telnum == undefined || telnum == null)) {
		iN.css('background-color', 'rgba(255,0,0,0.1)');
		tN.css('background-color', 'rgba(255,0,0,0.1)');
		warning("未输入身份证号码或手机号码！");
		return false;
	}
	else if (idnum.length != 18){
		warning("身份证号码输入错误!");
		return false;
	}
	else if (telnum.length != 11) {
		warning("手机号码输入错误!");
		return false;
	}
	
	var data = {
		idnum:idnum,
		telnum:telnum,
		aptnum:aptnum,
	};
	console.log(data);
	$.ajax({
		"url": "http://127.0.0.1:8082/form",
		"method": "POST",
		"dataType": "text",
		// 要发送给后端的数据参数，post时，数据必须写在data，get可以写在data,也可以跟在地址栏?号后面
		"data": data
	}).then(function(resp) { // ajax请求数据成功时会自动调用then方法的匿名函数
		console.log(resp); // 服务端返回的数据
	}).fail(function(error) { // ajax请求数据失败时会自动调用fail方法的匿名函数
		console.log(error);
	});

	return true;
}

function isEmpty(obj) {
	var val = obj.value;
	if (val == ''){
		obj.setAttribute('style', 'background-color: rgba(255,0,0,0.1);')
	}else {
		obj.setAttribute('style', 'background-color: rgba(100,100,100,0.1);')
	}
}

function warning(str) {
	fyAlert.msg(str,{icon:2,animateType:2} )
}