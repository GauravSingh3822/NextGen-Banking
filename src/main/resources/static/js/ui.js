// ui.js

export function showError(fieldId, message) {
    const field = document.getElementById(fieldId);
    const errorElement = document.getElementById(`${fieldId}Error`);
    if (field && errorElement) {
        field.classList.add('is-invalid');
        errorElement.textContent = message;
        errorElement.style.display = 'block';
    }
}

export function resetValidation() {
    document.querySelectorAll('.form-control').forEach(field => {
        field.classList.remove('is-invalid');
    });

    document.querySelectorAll('.invalid-feedback').forEach(error => {
        error.style.display = 'none';
    });
}

export function setLoadingState(button, isLoading) {
    if (!button) return;

    if (isLoading) {
        button.classList.add('loading');
        button.disabled = true;
        button.dataset.originalText = button.textContent;
        button.innerHTML = 'Processing...';
    } else {
        button.classList.remove('loading');
        button.disabled = false;
        button.innerHTML = button.dataset.originalText || 'Submit';
    }
}