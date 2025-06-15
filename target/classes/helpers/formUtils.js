// formUtils.js

export function resetValidation() {
  const errorElements = document.querySelectorAll('.error-message');
  errorElements.forEach(el => el.textContent = '');

  const inputElements = document.querySelectorAll('.input-error');
  inputElements.forEach(el => el.classList.remove('input-error'));
}

export function showError(fieldId, message) {
  const input = document.getElementById(fieldId);
  if (input) {
    input.classList.add('input-error');
    const errorEl = input.parentElement.querySelector('.error-message');
    if (errorEl) {
      errorEl.textContent = message;
    }
  }
}

export function validateEmail(email) {
  const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return re.test(email.toLowerCase());
}

export function setLoadingState(button, isLoading) {
  if (isLoading) {
    button.disabled = true;
    button.textContent = 'Loading...';
  } else {
    button.disabled = false;
    button.textContent = button.dataset.originalText || 'Submit';
  }
}
