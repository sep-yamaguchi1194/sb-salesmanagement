$(function() {
	//#orderStatusIdの全optionを保持する変数
	var $status = $("#orderStatusId");
	var original = $status.html();

	var selectedOrderCustomerIdVal = $("#orderCustomerId").val();

	var changedOrderStatusIdVal;

	if(selectedOrderCustomerIdVal != "") {
		updateOrderStatusId(selectedOrderCustomerIdVal);
	}

	//#orderCustomerIdが変更されたときのトリガーポイント
	$("#orderCustomerId").change(function() {
		var changedOrderCustomerIdVal = $(this).val();
		updateOrderStatusId(changedOrderCustomerIdVal);
	});

	//#orderCustomerIdの値と#orderStatusIdの<option>のdata-val属性が一致しないものを削除するメソッド
	function updateOrderStatusId(val) {
		$status.html(original).find("option").each(function() {
			var statusIdDataVal = $(this).data("val");
			if(val != statusIdDataVal) {
				$(this).not(':first-child').remove();
			}
		});

		if(val == "") {
			$status.attr("disabled", "disabled");
		} else {
			$status.removeAttr("disabled");
		}
	}

});