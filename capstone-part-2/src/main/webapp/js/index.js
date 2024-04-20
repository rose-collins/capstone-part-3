/**
 * 
 */
function storeEmail() {
        	console.log("storeEmail function called");
        	// Get the email from input field
            const userEmail = document.getElementById('email').value;
            console.log(userEmail); // Check the email value

            // Store email in sessionStorage
            sessionStorage.setItem('userEmail', userEmail);

            // Manually submit the form
            document.getElementById('signInForm').submit();

            return true; // Prevent default form submission
        }