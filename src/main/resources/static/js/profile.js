function populateProfile(user) {
    try {
        console.log("Populating profile with user data:", user);

        const setText = (id, value) => {
            const el = document.getElementById(id);
            if (el) {
                el.textContent = value || "-";
            } else {
                console.warn(`Element with id '${id}' not found.`);
            }
        };

        const setValue = (id, value) => {
            const el = document.getElementById(id);
            if (el) {
                el.value = value || "";
            } else {
                console.warn(`Element with id '${id}' not found.`);
            }
        };

        setText("full-name", user.name);
        setValue("edit-full-name", user.name);
        setValue("modal-full-name", user.name);

        // Defensive check on dob
        const dobFormatted = user.dob ? new Date(user.dob).toLocaleDateString() : "-";
        setText("dob", dobFormatted);
        setValue("edit-dob", user.dob || "");
        setValue("modal-dob", user.dob || "");

        setText("email", user.email);
        setValue("edit-email", user.email);
        setValue("modal-email", user.email);

        setText("phone", user.phone);
        setValue("edit-phone", user.phone);
        setValue("modal-phone", user.phone);

        const addrValue = user.address?.trim() || "";
        setText("address", addrValue);
        setValue("edit-address", addrValue);
        setValue("modal-address", addrValue);

    } catch (err) {
        console.error("Error inside populateProfile():", err);
        throw err; // Allow it to bubble if you're debugging
    }
}

document.addEventListener('DOMContentLoaded', () => {
  const editProfileBtn = document.getElementById('edit-profile-btn');
  const modal = document.getElementById('edit-profile-modal');
  const closeModalBtn = document.getElementById('close-edit-modal');
  const cancelEditBtn = document.getElementById('cancel-edit');

editProfileBtn.addEventListener('click', () => {
    modal.classList.add('active');  // Show modal by adding 'active'
modal.setAttribute('aria-hidden', 'false');
modal.focus();
  });

  closeModalBtn.addEventListener('click', () => {
    modal.classList.remove('active');  // Hide modal by removing 'active'
    modal.setAttribute('aria-hidden', 'true');

  });

  cancelEditBtn.addEventListener('click', () => {
    modal.classList.remove('active');  // Also hide modal on cancel
    modal.setAttribute('aria-hidden', 'true');

  });

  // Close modal if clicking outside modal-content
  window.addEventListener('click', (e) => {
    if (e.target === modal) {
      modal.classList.remove('active');
      modal.setAttribute('aria-hidden', 'true');
    }
  });
});

const personalSection = document.getElementById('personal-info-section');
const editBtn = personalSection.querySelector('.edit-section');
const saveBtn = personalSection.querySelector('.btn-save');
const cancelBtn = personalSection.querySelector('.btn-cancel');

const infoValues = personalSection.querySelectorAll('.info-value');
const infoEdits = personalSection.querySelectorAll('.info-edit');

function toggleEditMode(enable) {
  if (enable) {
    infoValues.forEach(div => (div.style.display = 'none'));
    infoEdits.forEach(input => (input.style.display = 'block'));
    editBtn.style.display = 'none';
    personalSection.querySelector('.edit-actions').style.display = 'flex';
  } else {
    infoValues.forEach(div => (div.style.display = 'block'));
    infoEdits.forEach(input => (input.style.display = 'none'));
    editBtn.style.display = 'flex';
    personalSection.querySelector('.edit-actions').style.display = 'none';
  }
}

editBtn.addEventListener('click', () => {
  toggleEditMode(true);
});

cancelBtn.addEventListener('click', () => {
  // Reset input values to original displayed values
  infoEdits.forEach(input => {
    const id = input.id.replace('edit-', '');
    const displayElem = document.getElementById(id);
    if (displayElem) {
      if (input.tagName.toLowerCase() === 'textarea') {
        input.value = displayElem.textContent.trim();
      } else {
        input.value = displayElem.textContent.trim();
      }
    }
  });
  toggleEditMode(false);
});

saveBtn.addEventListener('click', () => {
  // Copy input values to display divs
  infoEdits.forEach(input => {
    const id = input.id.replace('edit-', '');
    const displayElem = document.getElementById(id);
    if (displayElem) {
      if (input.tagName.toLowerCase() === 'textarea') {
        displayElem.textContent = input.value;
      } else {
        displayElem.textContent = input.value;
      }
    }
  });
  toggleEditMode(false);
});
