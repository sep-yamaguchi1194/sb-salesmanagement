/**
 *
 */

$(function() {
	//#statusidの全optionを保持する変数
	var $status = $("#statusid");
	var original = $status.html();

	var selectedCustomerIdVal = $("#customerid").val();

	if(selectedCustomerIdVal != "") {
		updateStatusId(selectedCustomerIdVal);
	}

	//#customerid が変更されたときのトリガーポイント
	$("#customerid").change(function() {
		var changedCustomerIdVal = $(this).val();
		updateStatusId(changedCustomerIdVal);
	});

	//#customeridの値と#statusidの<option>のdata-val属性が一致しないものを削除するメソッド
	function updateStatusId(val) {
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