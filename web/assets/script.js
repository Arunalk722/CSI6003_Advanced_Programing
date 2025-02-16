function validateForm() {
    let bookingId = document.getElementById("bookingId").value;
    let phone = document.getElementById("customerPhone").value;

    if (bookingId.trim() === "" || phone.trim() === "") {
        document.getElementById("errorMessage").innerText = "Please fill all required fields.";
        return false;
    }

    if (!/^\d{10}$/.test(phone)) {
        document.getElementById("errorMessage").innerText = "Invalid phone number. Must be 10 digits.";
        return false;
    }

    return true;
}
