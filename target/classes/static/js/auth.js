async function checkAuthStatus() {
    const token = localStorage.getItem("token");
    if (!token) {        
        window.location.href = "login.html";
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/api/auth/me", {
            method: "GET",
            credentials: "include",
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });

        if (!response.ok) {
            throw new Error("Unauthorized");
        }

        const user = await response.json(); // Assumes a user object is returned
        const name = user.name || "User";
        const initials = name
            .split(" ")
            .map(n => n.charAt(0).toUpperCase())
            .join("");

        document.getElementById("user-initials").textContent = initials;

        // If needed, store user data globally
        window.loggedInUser = user;
        console.log("User authenticated:", user);
        fetchAndDisplayAccounts(user.id, token);
                if (window.location.pathname.includes("profile.html")) {

         try {
            console.log("Populating profile with user data:", user);
        populateProfile(user);
    } catch (populateErr) {
        console.error("Error populating profile:", populateErr);
    }
}
        


    } catch (err) {
        console.error("Auth check failed:", err);
        localStorage.removeItem("token");
        localStorage.setItem("loggedIn",false);
        window.location.href = "login.html";
    }
}

async function fetchAndDisplayAccounts(userId, token) {
    try {
        const response = await fetch(`http://localhost:8080/api/users/${userId}`, {
            method: "GET",
            credentials: "include",
           
        });

       
        const responseData = await response.json();
        console.log("Response Data:", responseData);
        

        console.log("Fetching accounts for user ID:", userId);
        if (!response.ok) {
            throw new Error("Failed to fetch account details");
        }
         // This should be an array

        if (responseData && responseData.data && responseData.data.accounts.length > 0) {
            const accounts = responseData.data.accounts;
            console.log("Accounts fetched:", accounts);
            // Assuming displayAccount is a function that takes an account object and displays it

            displayAccount(accounts[0]); 
// Show first account for now
        } else {
            console.log("No accounts found for user.");
        }
    } catch (error) {
        console.error("Error fetching accounts:", error);
    }
}


// function logout() {
//     // Remove token from local storage
//     localStorage.removeItem("token");

//     // (Optional) You could also clear any stored user data
//     window.loggedInUser = null;

//     // Redirect to login page
//     window.location.href = "login.html";
// }



