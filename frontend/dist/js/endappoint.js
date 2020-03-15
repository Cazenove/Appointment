$(document).ready(function(){
	$("#end").click(function(){
		consolo.log("正在请求结束预约");
		$.post(url,{
			status:"end"
		},
		function(status){//回调函数
			if(status == 0) {
				alert("成功结束预约！");
			}
			else if(status == 1) {
				alert("预约已结束，请不要重复操作！");
			}
			else if(status == 2) {
				alert("结束预约失败！");
			}
		});
	});
});