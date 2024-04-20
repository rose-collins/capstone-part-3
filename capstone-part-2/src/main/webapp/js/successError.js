/**
 * 
 */
document.addEventListener("DOMContentLoaded", function() {
            //get the url param passed
            const urlParams = new URLSearchParams(window.location.search);
            const success = urlParams.get('success');

            //display success messaged based on if user was created or not in db
            if (success === "true") {
                document.getElementById("message").innerText = "User successfully created!";
            } else if (success === "false") {
                document.getElementById("message").innerText = "Failed to create user. An account with the email provided already exists.";
            }
        });
