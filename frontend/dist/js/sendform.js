
function isEmpty() {
    var idNum = $("#idNum").val();

    if (idNum == '' || idNum == undefined || idNum == null) {
        $("#idNum").css('background-color', 'rgba(255,0,0,0.1)');
    }
    else {
        $("#idNum").css('background-color', '')
    }


}