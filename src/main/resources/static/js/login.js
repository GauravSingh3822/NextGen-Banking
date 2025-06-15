// login.js
import { resetValidation, showError, validateEmail, setLoadingState } from '../helpers/formUtils.js';
import { loginUser, redirectIfAuthenticated } from './api.js';

redirectIfAuthenticated();

function initLoginForm() {
  const loginForm = document.getElementById('loginForm');
  if (!loginForm) return;

  const loginBtn = loginForm.querySelector('.submit-btn');
  loginBtn.dataset.originalText = loginBtn.textContent;

  loginForm.addEventListener('submit', async function (e) {
    e.preventDefault();

    resetValidation();
    setLoadingState(loginBtn, true);

    const email = document.getElementById('loginEmail').value.trim();
    const password = document.getElementById('loginPassword').value;

    let isValid = true;

    if (!email) {
      showError('loginEmail', 'Email is required');
      isValid = false;
    } else if (!validateEmail(email)) {
      showError('loginEmail', 'Invalid email format');
      isValid = false;
    }

    if (!password) {
      showError('loginPassword', 'Password is required');
      isValid = false;
    }

    if (!isValid) {
      setLoadingState(loginBtn, false);
      return;
    }

    try {
      const response = await loginUser({ email, password });
      const responseData = response.data;

      if (response.ok) {
        localStorage.setItem('token', responseData.data); // Store token
        localStorage.setItem('loggedIn', true); // Set logged in status
        alert('Login successful! Redirecting to dashboard...');
        window.location.href = 'home.html';
      } else {
        if (responseData.errors) {
          Object.entries(responseData.errors).forEach(([field, message]) => {
            const fieldId = `login${field.charAt(0).toUpperCase() + field.slice(1)}`;
            if (document.getElementById(fieldId)) {
              showError(fieldId, message);
            }
          });
        } else {
          alert(responseData.message || 'Login failed. Please try again.');
        }
      }
    } catch (error) {
      console.error('Login error:', error);
      alert('An error occurred during login. Please try again.');
    } finally {
      setLoadingState(loginBtn, false);
    }
  });
}

document.addEventListener('DOMContentLoaded', () => {
  initLoginForm();
});
