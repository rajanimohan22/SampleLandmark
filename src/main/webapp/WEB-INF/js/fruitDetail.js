$(document).ready(function(){
	if($.trim(fileError) != '') {
		alert("There is a problem with the file attached");
		$('#errorMsgs').html(fileError);
	}
});

function updatePrice(thisObj) {
	var itemId = $(thisObj).parent().parent().attr('id');
	var itemPrice = $('#' + itemId + '-input').val();
	var splitArray = itemPrice.split(".");
	var mainItemPrice = splitArray[0];
	var decimalItemPrice = splitArray[1];
	
	$.get(contextPath + '/priceAndFilter/item/' + mainItemPrice + "/" + decimalItemPrice + "/" + itemId, function(isSucces) {
		if(isSucces == true) {
			alert("Price Update Successful");
			$('#' + itemId + '-button').attr('disabled', 'disabled');
		}
		else {
			alert("Price Update NOT Successful. Please specify price in x.xx format");
			$('#' + itemId + '-button').attr('disabled', 'disabled');
		}
	});

}

function enableDisbaleButton(thisObj) {
	var itemId = $(thisObj).parent().parent().attr('id');
	var itemPrice = $(thisObj).parent().attr('id');
	var presentValue = $('#' + itemId + '-input').val();
	if(itemPrice != $.trim(presentValue)) {
		$('#' + itemId + '-button').removeAttr('disabled');
	}
	else {
		$('#' + itemId + '-button').attr('disabled', 'disabled');
	}
}

function enableButton(thisObj) {
	var itemId = $(thisObj).parent().parent().attr('id');
	$('#' + itemId + '-button').removeAttr('disabled');
}

function doFilter() {
	var filterText = $('#filterBox').val();
	if($.trim(filterText) == '') {
		alert("Please enter some text to filter");
	}
	else {
		filterText = $.trim(filterText);
		var updatedTable = '<table border=1 width="50%"><tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Stock</b></td><td><b>Updated Date</b></td></tr>';
		var iterRow = "";
		$.get(contextPath + '/priceAndFilter/filterFruits/' + filterText, function(matchedIdList) {
			if($(matchedIdList).length > 0) {
				var initTable = '<table border=1 width="50%"><tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Stock</b></td><td><b>Updated Date</b></td></tr>';
				$.each(matchedIdList, function(idx, item) {
					iterRow = iterRow + '<tr id=' + $(item).attr('id')  + '>'
					iterRow = iterRow + '<td>' + $(item).attr('name') + '</td>'
					iterRow = iterRow + '<td id=' + $(item).attr('price')  + '><input type="text" id=' + $(item).attr('id') + '-input onfocus="enableButton(this);" onblur="enableDisbaleButton(this)"'
					iterRow = iterRow + ' value=' + $(item).attr('price') + ' maxlength="4" size="4" />'
					iterRow = iterRow + ' <input id=' + $(item).attr('id') + '-button type="button" value="Update Price" disabled="disabled" onclick="updatePrice(this)">'
					iterRow = iterRow +  '</td>'
					iterRow = iterRow +  '<td>' + $(item).attr('stock') + '</td>'
					iterRow = iterRow +  '<td>' + convertUTCToDateTime($(item).attr('updated')) + '</td>'
					iterRow = iterRow + '</tr>'
				});
				updatedTable = updatedTable + iterRow + '</table>'
				$('#fruitTableContents').html();
				$('#fruitTableContents').html(updatedTable);
			}
			else {
				alert("No matches found.");
			}
		});
	}
}

function clearFilterDisplayAll() {
	$('#fruitDetailForm').submit();
}

function convertUTCToDateTime(UTCTimestamp) {
    var convertdLocalTime = new Date(UTCTimestamp).toISOString();
    convertdLocalTime = convertdLocalTime.replace("T", " ")
    convertdLocalTime = convertdLocalTime.replace("Z", "")
    return convertdLocalTime;
}




