const userAjaxUrl = "admin/users/";

const ctx = {
    ajaxUrl: userAjaxUrl,
    updateTable: function () {
        $.get(userAjaxUrl, updateTableData);
    }
}

// $(document).ready(function () {
$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "name"
                },
                {
                    "data": "email"
                },
                {
                    "data": "roles"
                },
                {
                    "data": "enabled"
                },
                {
                    "data": "registered"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        })
    );
});

function enable(element) {
    let row = element.closest("tr");
    let id = row.attr("id");
    let enabled = element.is(":checked");
    $.ajax({
        type: "POST",
        url: userAjaxUrl + id,
        data: "enabled=" + enabled
    }).done(function () {
        row.attr("user-enabled", enabled);
        enabled ? successNoty("Disable") : successNoty("Enable");
    }).fail(function () {
        $(element).prop("checked", !enabled);
    });
}