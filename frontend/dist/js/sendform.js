
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

function appointmentSubmit() {
    var iN = $("#idNum");
    var tN = $("#telNum");

    var idNum = iN.val();
    var telNum = tN.val();

    if ((idNum == '' || idNum == undefined || idNum == null)||(telNum == '' || telNum == undefined || telNum == null)) {
        iN.css('background-color', 'rgba(255,0,0,0.1)');
        tN.css('background-color', 'rgba(255,0,0,0.1)');
        warning("未输入身份证号码或手机号码！");
        return false;
    }
    else if (idNum.length != 18){
        warning("身份证号码输入错误!");
        return false;
    }
    else if (telNum.length != 11) {
        warning("手机号码输入错误!");
        return false;
    }
    else {
        return true;
    }
}

function querySubmit() {
    var sN = $("#stuNum");

    var stuNum = sN.val();

    if(stuNum == '' || stuNum == undefined || stuNum == null) {
        sN.css('background-color', 'rgba(255,0,0,0.1)');
        warning("未输入预约编号！");
        return false;
    }
    else {
        return true;
    }
}


