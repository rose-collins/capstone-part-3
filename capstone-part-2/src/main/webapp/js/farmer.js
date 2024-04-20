/**
 * 
 */
function getUserInformation() {
	    	console.log("Fetching user information...");

	        const email = sessionStorage.getItem('userEmail');
	        console.log(email);
	        const contextPath = '/capstone-part-2';
	        //const url = `${contextPath}/user?email=${encodeURIComponent(email)}`; // Encode email
	        const url = `/capstone-part-2/user?email=${encodeURIComponent(email)}`;

	        const requestOptions = {
	            method: "GET",
	            redirect: "follow"
	        };

	        fetch(url, requestOptions)
	            .then((response) => {
	                if (!response.ok) {
	                    throw new Error('Network response was not ok');
	                }
	                return response.text();
	            })
	            .then((data) => {
	                const lines = data.split('\n');
	                document.getElementById('emailDisplay').textContent = lines[0];
	                document.getElementById('passwordDisplay').textContent = lines[1];
	                document.getElementById('nameDisplay').textContent = lines[2];
	                document.getElementById('addressDisplay').textContent = lines[3];
	                document.getElementById('phoneNumberDisplay').textContent = lines[4];
	                document.getElementById('userTypeDisplay').textContent = lines[5];
	                document.getElementById('accountDetails').style.display = 'block';
	            })
	            .catch((error) => {
	                console.error('Error fetching data:', error);
	            });
	    }
	    function editField(field) {
	    	// Hide all input fields
	        const inputFields = document.querySelectorAll("#accountDetails input");
	        inputFields.forEach(input => {
	            input.style.display = "none";
	        });

	        // Show the input field for the selected field
	        const inputField = document.getElementById(`${field}Input`);
	        inputField.style.display = "block";

	        // Set the input field value to the current display value
	        const displayElement = document.getElementById(`${field}Display`);
	        inputField.value = displayElement.textContent.trim();

	        const saveButton = document.createElement("button");
	        saveButton.textContent = "Save Changes";
	        saveButton.onclick = function() {
	            saveChanges(field);
	        };

	        // Append the "Save Changes" button after the input field
	        displayElement.appendChild(saveButton);
        }

        //save changes made to a field
        function saveChanges(field) {
        	console.log("Save changes method called");
        	// Get all constant field values
            const email = encodeURIComponent(document.getElementById("emailDisplay").textContent.trim());
            const name = encodeURIComponent(document.getElementById("nameDisplay").textContent.trim());
            const userType = encodeURIComponent(document.getElementById("userTypeDisplay").textContent.trim());

            let address;
            let phoneNumber;
            let password;

            // Check and get address value
            const addressInput = document.getElementById("addressInput");
            if (addressInput && addressInput.value.trim() !== "") {
                address = encodeURIComponent(addressInput.value.trim());
            } else {
                address = encodeURIComponent(document.getElementById("addressDisplay").textContent.trim());
            }
            console.log(`Address: ${address}`);
            
            // Check and get phoneNumber value
            const phoneNumberInput = document.getElementById("phoneNumberInput");
            if (phoneNumberInput && phoneNumberInput.value.trim() !== "") {
                phoneNumber = encodeURIComponent(phoneNumberInput.value.trim());
            } else {
                phoneNumber = encodeURIComponent(document.getElementById("phoneNumberDisplay").textContent.trim());
            }
            console.log(`Phone: ${phoneNumber}`);
            
            // Check and get password value
            const passwordInput = document.getElementById("passwordInput");
            if (passwordInput && passwordInput.value.trim() !== "") {
                password = encodeURIComponent(passwordInput.value.trim());
            } else {
                password = encodeURIComponent(document.getElementById("passwordDisplay").textContent.trim());
            }
            console.log(`password: ${password}`);

            // Construct the parameter string
            const params = `email=${email}&password=${password}&name=${name}&address=${address}&phoneNumber=${phoneNumber}&userType=${userType}`;
            console.log("Params: ",params);
            
            // Perform the PUT request to update the fields in the server
            const url = `/capstone-part-2/user`;

            fetch(url, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: params
            })
            .then(response => response.json())
            .then(data => {
                if (data && data.message) {
                    console.log(data.message);
                } else {
                    console.error("Unexpected response format:", data);
                }

                // Update the display
                if (addressInput) {
                    document.getElementById("addressDisplay").textContent = addressInput.value.trim();
                }
                if (phoneNumberInput) {
                    document.getElementById("phoneNumberDisplay").textContent = phoneNumberInput.value.trim();
                }
                if (passwordInput) {
                    document.getElementById("passwordDisplay").textContent = passwordInput.value.trim();
                }
            })
            .catch(error => {
                console.error("Error updating field:", error);
            });
        	
        }
	
	    //delete confirmation 
	    function deleteUserWithConfirmation() {
		    const confirmation = confirm("Are you sure you want to delete your account?");
		    if (confirmation) {
		        deleteUser();
    		}
		}

	    //postman generated PUT request JS code, if confirmed
		function deleteUser() {
			const email = sessionStorage.getItem('userEmail'); // Retrieve user's email
		    const myHeaders = new Headers();
		    myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

		    const requestOptions = {
		        method: "DELETE",
		        headers: myHeaders,
		        redirect: "follow"
		    };

		    // Construct the URL with email as a query parameter
		    const url = `/capstone-part-2/user?email=${encodeURIComponent(email)}`;

		    fetch(url, requestOptions)
		        .then((response) => {
		            if (response.status === 200) {
		                // Display message in HTML
		                const messageDiv = document.getElementById("message");
		                messageDiv.textContent = "Account successfully deleted";

		                // Redirect after 5 seconds
		                setTimeout(() => {
		                    window.location.href = "index.jsp";
		                }, 1000);
		            } else {
		                console.error("Failed to delete user");
		            }
		        })
		        .catch((error) => console.error(error));
		}
