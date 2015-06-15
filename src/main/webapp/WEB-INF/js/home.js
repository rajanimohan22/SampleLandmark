$(document).ready(function(){
	if($.trim(fileError) != '') {
		alert("There is a problem with the file attached");
		$('#errorMsgs').html();
		$('#errorMsgs').html('<span style="color:red">' + fileError + "</span>");
		$('#errorMsgs').show();
	}
});