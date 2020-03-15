$(document).ready(function(){
	$("#end").click(function(){
		consolo.log("正在请求结束预约");
		$.post(url,{
			status:"end"
		},
	});
});