(function () {
  console.log("Initializing navbar");

  function displayError(message, err) {
    console.error(message, err);
    // Optional: show error on page for quick visual feedback
    const navbarContainer = document.getElementById('navbar-container');
    if (navbarContainer) {
      navbarContainer.innerHTML = `<div style="color:red; padding:10px; border:1px solid red;">
        <strong>Navbar Error:</strong> ${message} <br> ${err ? err.message || err : ''}
      </div>`;
    }
  }

  let navbarContainer;
  try {
    navbarContainer = document.getElementById('navbar-container');
    if (!navbarContainer) throw new Error('Navbar container missing from DOM');
    console.log("Navbar container found");
  } catch (err) {
    displayError('Could not find navbar container', err);
    return; // Stop execution if no container
  }

  let token, loggedInStorage;
  let isLoggedIn = false;
  try {
    token = localStorage.getItem('token');
    loggedInStorage = localStorage.getItem('loggedIn');
    console.log(`LocalStorage token: ${token}`);
    console.log(`LocalStorage loggedIn: ${loggedInStorage}`);

    isLoggedIn = token && token !== '' && loggedInStorage && loggedInStorage !== 'false';
  } catch (err) {
    displayError('Error reading from localStorage', err);
    isLoggedIn = false; // Default to logged out
  }

  const loggedInNavbar = `
    <header>
      <div class="logo">
        <div class="logo-icon"></div>
        <span id="logo-text">NextGen</span>
      </div>
      <nav class="nav-links">
        <a href="dashboard.html">Dashboard</a>
        <a href="#">Accounts</a>
        <a href="#">Transfers</a>
        <a href="#">Payments</a>
        <a href="#">Cards</a>
      </nav>
      <div class="user-menu">
        <div class="user-avatar">
          <span id="user-initials">JD</span>
        </div>
        <div class="dropdown-menu">
          <a href="profile.html" class="active">Profile</a>
          <a href="#">Settings</a>
          <a href="#" id="logout-btn">Logout</a>
        </div>
      </div>
    </header>
  `;

  const loggedOutNavbar = `
    <header>
      <div class="logo">
        <div class="logo-icon"></div>
        <span id="logo-text">NextGen</span>
      </div>
      <nav class="nav-links">
        <a href="index.html">Home</a>
        <a href="about.html">About</a>
        <a href="login.html">Features</a>
        <a href="register.html">Contact</a>
      </nav>
      <div class="auth-buttons">
        <a href="Login.html">
          <button class="btn btn-outline" id="login-btn">Login</button>
        </a>
        <a href="Register.html">
          <button class="btn btn-primary" id="register-btn">Register</button>
        </a>
      </div>
    </header>
  `;


  try {
    navbarContainer.innerHTML = isLoggedIn ? loggedInNavbar : loggedOutNavbar;
    console.log("Navbar rendered for " + (isLoggedIn ? "logged-in" : "logged-out") + " user");
      document.getElementById("logo-text").textContent = isLoggedIn ? "NextGen" : "NextGen Bank";
document.getElementById("logo-text").addEventListener("click", function() {
        location.href = isLoggedIn ? "home.html" : "index.html";
      }
    );
  } catch (err) {
    displayError('Failed to render navbar', err);
  }

  if (isLoggedIn) {
    try {
      const logoutBtn = document.getElementById('logout-btn');
      if (logoutBtn) {
        logoutBtn.addEventListener('click', e => {
          e.preventDefault();
          try {
            localStorage.removeItem('token');
            localStorage.setItem('loggedIn', 'false');
            console.log("User logged out, redirecting to index.html");
          } catch (storageErr) {
            displayError('Error clearing localStorage during logout', storageErr);
          }
          location.href = 'index.html';
        });
        console.log("Logout event attached");
      } else {
        console.warn("Logout button not found to attach event");
      }
    } catch (err) {
      displayError('Error attaching logout event listener', err);
    }
  }
})();
