/**
 * 
 */
const form = document.getElementById("passwordRecoveryForm");
const passwordResponseDiv = document.getElementById("passwordResponse");

form.addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent default form submission

    const email = document.getElementById("email").value;
    const phoneNumber = document.getElementById("phoneNumber").value;

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

    const urlencoded = new URLSearchParams();
    urlencoded.append("email", email);
    urlencoded.append("phoneNumber", phoneNumber);

    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: urlencoded,
        redirect: "follow"
    };

    fetch("/capstone-part-2/passwordRecovery", requestOptions)
        .then(response => response.text())
        .then(data => {
            //update the passwordResponse div with the server response
            passwordResponseDiv.textContent = data;
        })
        .catch(error => {
            console.error("Error:", error); 
            passwordResponseDiv.textContent = "An error occurred";
        });
});