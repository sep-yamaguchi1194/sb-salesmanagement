/**
 * セレクトボックスを動的に書き換えるためのjs [※念のためコメントアウトして保持する]
 */

$(function() {
	$("#customerid").change(function() {
		event.preventDefault();

		$.ajax({
			type : "POST",
			url : "getstatuses",
			dataType : "html",
			data : {
				customerid : $(this).val()
			},
			success : function(data, status, xhr) {
				$(".div_for_embeded").html(data);
			}
		});
	});
});


$(document).on('unload',function(){
	var tmpCustomerId = $("#customerId").val();
	if(tmpCustomerId !== 0) {
		$.ajax({
			type : "POST",
			url : "getstatuses_add",
			dataType : "html",
			data : {
				customerid : tmpCustomerId
			},
			success : function(data, status, xhr) {
				$(".div_for_embeded").html(data);
			}
		});
	}
});

$(function() {
	$("#customerId").change(function() {
		event.preventDefault();

		$.ajax({
			type : "POST",
			url : "getstatuses_add",
			dataType : "html",
			data : {
				customerid : $(this).val()
			},
			success : function(data, status, xhr) {
				$(".div_for_embeded").html(data);
			}
		});
	});
});