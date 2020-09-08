function jump_page() {
    var action_value = $("#jump_page").attr("action");
    var page_index = document.getElementById("page_index").value;
    action_value = action_value + page_index;
    $("#jump_page").attr("action", action_value);
}
function del(id) {
    if (confirm("确认删除嘛?")) {
        $.ajax({
            url: "http://localhost:8080/del_pic",
            data: {"id": id},
            type: "POST",
            dataType: "JSON",
            success: function (res) {
                if (res.ret === "success") {
                    var element = document.getElementById(id);
                    element.remove();
                }
                alert(res.msg)
            }
        });
    }
}