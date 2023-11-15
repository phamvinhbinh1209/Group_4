/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


//=============================Chọn Số Lượng Hàng Muốn Hiện Thị Cùng Một Lúc======================
document.addEventListener("DOMContentLoaded", function () {
    var quantitySelect = document.getElementById("quantity");
    var productTable = document.getElementById("product-table");

    quantitySelect.addEventListener("change", function () {
        var selectedQuantity = parseInt(quantitySelect.value);
        var tableRows = productTable.getElementsByTagName("tr");

        // Ẩn tất cả các hàng trừ hàng đầu tiên(hàng tiêu đề) nên chạy từ 1
        for (var i = 1; i < tableRows.length; i++) {
            tableRows[i].style.display = "none";
        }

        // Hiển thị số lượng hàng tương ứng
        for (var i = 1; i <= selectedQuantity; i++) {
            tableRows[i].style.display = "";
        }
    });
});

//=============================Xóa Sản Phẩm=======================================================

//=============================Xóa Sản Phẩm=======================================================
var selectedIds = []; // Biến lưu trữ danh sách các ID đã chọn

// Xử lý sự kiện khi người dùng nhấp vào nút có class "xoaSapXep"
document.addEventListener("click", function (event) {
    if (event.target.classList.contains("xoaSapXep")) {
        var checkboxes = document.querySelectorAll("input[type=checkbox]:checked");
        var idsToDelete = [];

        checkboxes.forEach(function (checkbox) {
            var id = checkbox.closest("tr").querySelector("td:nth-child(2)").textContent;
            idsToDelete.push(id);
        });

        if (idsToDelete.length > 0) {
            var isConfirmed = confirm("Bạn có chắc chắn muốn xóa các sản phẩm đã chọn?");
            if (isConfirmed) {
                checkboxes.forEach(function (checkbox) {
                    var row = checkbox.closest("tr");
                    row.remove();
                });

                // Cập nhật danh sách ID đã chọn
                selectedIds = idsToDelete;
            }
        } else {
            alert("Vui lòng chọn ít nhất một sản phẩm để xóa.");
        }
    }
});



//=============================Chức Năng Tìm Kiếm==============================================
function myFunction() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("tableSearch");
    filter = input.value.toUpperCase();
    table = document.getElementById("product-table"); // Sửa ID này để phù hợp với bảng
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td");
        var found = false;
        for (var j = 0; j < td.length; j++) {
            if (td[j]) {
                txtValue = td[j].textContent || td[j].innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            tr[i].style.display = "";
        } else {
            tr[i].style.display = "none";
        }
    }
}

// Sử dụng sự kiện "input" cho ô tìm kiếm để tự động tìm kiếm khi nhập
document.getElementById("tableSearch").addEventListener("input", myFunction);


