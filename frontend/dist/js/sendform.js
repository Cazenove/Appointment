
function isEmpty() {

    var iN = $("#idNum");
    var tN = $("#telNum");

    var idNum = iN.val();
    var telNum = tN.val();

    if (idNum == '' || idNum == undefined || idNum == null) {
        iN.css('background-color', 'rgba(255,0,0,0.1)');
    }
    else {
        iN.css('background-color', '')
    }

    if (telNum == '' || telNum == undefined || telNum == null) {
        tN.css('background-color', 'rgba(255,0,0,0.1)');
    }
    else {
        tN.css('background-color', '')
    }


}