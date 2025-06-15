// api.js

export async function registerUser(data) {
  const response = await fetch('http://localhost:8080/api/users/register', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
    credentials: 'include',
  });

  const responseData = await response.json();
  return {
    ok: response.ok,
    status: response.status,
    data: responseData,
  };
}

// api.js

export async function loginUser(data) {
  const response = await fetch('http://localhost:8080/api/users/login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
    credentials: 'include',
  });

  const responseData = await response.json();
  return {
    ok: response.ok,
    status: response.status,
    data: responseData,
  };
}




// authGuard.js

export function redirectIfAuthenticated() {
  const token = localStorage.getItem('token');

  // If token exists, redirect away from login/register pages
  if (token) {
    // Redirect to home/dashboard — update the path as needed
    window.location.href = 'home.html';
  }
}
