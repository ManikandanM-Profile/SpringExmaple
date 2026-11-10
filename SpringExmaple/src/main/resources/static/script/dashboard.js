function toggleDropdown() {
    document.getElementById("dropdownMenu")
        .classList.toggle("show");
}
document.addEventListener("click", function(event) {
    const dropdown = document.querySelector(".profile-dropdown");
    const menu = document.getElementById("dropdownMenu");

    if (!dropdown.contains(event.target)) {
        menu.classList.remove("show");
    }
});
function openRegisterModal() {
    document.getElementById('registerModal').classList.add('show');
	document.getElementById("registerForm").reset();
	removeBorder('regUsername','usernameError');
	removeBorder('regPassword','passwordError');
	removeBorder('regRole','roleError');
	removeBorder('regEmail','emailError');
	removeBorder('regPhoneNo','phoneError');
}
function closeRegisterModal() {
    document.getElementById('registerModal').classList.remove('show');
}
function registerUser() {
    if (!validRegisterUser()) {
        return false;
    }
    const userData = {
        username: document.getElementById("regUsername").value.trim(),
        password: document.getElementById("regPassword").value,
        role: document.getElementById("regRole").value,
        email: document.getElementById("regEmail").value,
        phoneNo: document.getElementById("regPhoneNo").value

    };
    fetch("/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(userData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Registration failed");
        }
        return response;
    })
    .then(() => {
        document.getElementById("registerForm").reset();
        closeRegisterModal();
    })
    .catch(error => {
        console.error(error);
    });
}
function validRegisterUser() {
    var isValid = true;
    var username = $('#regUsername').val();
    var password = $('#regPassword').val();
    var role = $('#regRole').val();
    var email = $('#regEmail').val();
    var phoneNo = $('#regPhoneNo').val();

    if (username.trim() == "") {
		errorBorder('regUsername');
        $('#usernameError').text("Please enter username").show();
        isValid = false;
    }
    if (password.trim() == "") {
		errorBorder('regPassword');
		$('#passwordError').text("Please enter password").show();
        isValid = false;
    }
    if (role == 0) {
		errorBorder('regRole');
		$('#roleError').text("Please select role").show();
        isValid = false;
    }
    if (email == "") {
		errorBorder('regEmail');
		$("#emailError").text("Please enter email").show();
        isValid = false;
    }
    if (phoneNo == "") {
		errorBorder('regPhoneNo');
		$("#phoneError").text("Please enter phone number").show();
        isValid = false;
    }
    return isValid;
}
function openProfileModal() {
    document.getElementById("profileModal").classList.add("show");
}

function closeProfileModal() {
    document.getElementById("profileModal").classList.remove("show");
}

// Close popup with Escape key
/*document.addEventListener('keydown', function (event) {
    if (event.key === 'Escape') {
		alert("111")
        closeRegisterModal();
    }
});*/


/*function removeBorder(id,msg) {
	var errBorder = document.getElementById(id);
	errBorder.classList.remove("error-border");
	document.getElementById(msg).innerText = "";
}*/
function errorBorder(id){
	$("#"+id).css({"border-color": "#d93025"});

}
function removeBorder(id,msg){
	$("#"+id).css({"border-color": ""});
	$("#"+msg).text("").hide();;
}