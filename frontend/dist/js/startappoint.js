$(document).ready(function(){
	$("#start").click(function(){
		consolo.log("正在请求开启预约");
		$.post(url,{
			status:"start"
		},
		function(status){//回调函数
			if(status == 0) {
				alert("成功开放预约！");
			}
			else if(status == 1) {
				alert("预约已开放，请不要重复开启！");
			}
			else if(status == 2) {
				alert("预约开放失败！");
			}
		});
	});
});