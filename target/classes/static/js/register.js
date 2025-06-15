// register.js

import { showError, resetValidation } from "./ui.js";
import { validateEmail, validatePhone } from "./validations.js";
import { registerUser, redirectIfAuthenticated } from "./api.js";

redirectIfAuthenticated();

document.addEventListener("DOMContentLoaded", () => {
  const registerForm = document.getElementById("registerForm");
  const submitBtn = document.getElementById("submitBtn");

  if (!registerForm) return;

  registerForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    resetValidation();

    submitBtn.classList.add("loading");
    submitBtn.disabled = true;

    const name = document.getElementById("registerName").value.trim();
    const email = document.getElementById("registerEmail").value.trim();
    const phone = document.getElementById("registerPhone").value.trim();
    const address = document.getElementById("registerAddress").value.trim();
    const password = document.getElementById("registerPassword").value;

    let isValid = true;

    if (!name || name.length < 2 || name.length > 100) {
      showError("registerName", "Name must be between 2 and 100 characters");
      isValid = false;
    }

    if (!email || !validateEmail(email)) {
      showError("registerEmail", "Invalid email format");
      isValid = false;
    }

    if (!phone || !validatePhone(phone)) {
      showError("registerPhone", "Please enter a valid phone number");
      isValid = false;
    }

    if (!address) {
      showError("registerAddress", "Address is required");
      isValid = false;
    }

    if (!password || password.length < 6) {
      showError(
        "registerPassword",
        "Password must be at least 6 characters long"
      );
      isValid = false;
    }

    if (!isValid) {
      submitBtn.classList.remove("loading");
      submitBtn.disabled = false;
      return;
    }

    try {
      const response = await registerUser({
        name,
        email,
        phone,
        address,
        password,
      });

      console.log("Registration response:", response);

      if (response.ok) {
        alert("Registration successful! You can now login.");
        localStorage.setItem("token",response.data.token);
        window.location.href = "banking/home.html";
      } else {
          const responseData = response.data;

        if (responseData.errors) {
          Object.entries(responseData.errors).forEach(([field, message]) => {
            const fieldId = `register${
              field.charAt(0).toUpperCase() + field.slice(1)
            }`;
            showError(fieldId, message);
          });
        } else {
          alert(
            responseData.message || "Registration failed. Please try again."
          );
        }
      }
    } catch (error) {
      console.error("Registration error:", error);
      alert("An error occurred during registration. Please try again.");
    } finally {
      submitBtn.classList.remove("loading");
      submitBtn.disabled = false;
    }
  });
});
