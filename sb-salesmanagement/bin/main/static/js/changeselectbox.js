/**
 * セレクトボックスを動的に書き換えるためのjs
 */

$(function() {
  $("#customerid").change(function() {
    event.preventDefault();

    $.ajax({
      type: "POST",
      url: "getstatuses",
      dataType: "html",
      data: {
        customerid : $(this).val()
      },
      success: function(data, status, xhr) {
        $(".div_for_embeded").html(data);
      },
      error: function(XMLHttpRequest, textStatus, errorThrown) {
        alert("error");
      }
    });
  });
});