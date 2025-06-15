// document.addEventListener('DOMContentLoaded', function() {
//         // Create floating elements
//         const floatingContainer = document.getElementById('floating-elements');
//         for (let i = 0; i < 15; i++) {
//             const element = document.createElement('div');
//             element.classList.add('floating-element');

//             // Random size between 50px and 200px
//             const size = Math.random() * 150 + 50;
//             element.style.width = `${size}px`;
//             element.style.height = `${size}px`;

//             // Random position
//             element.style.left = `${Math.random() * 100}%`;
//             element.style.top = `${Math.random() * 100}%`;

//             // Random animation duration
//             element.style.animationDuration = `${Math.random() * 10 + 10}s`;

//             floatingContainer.appendChild(element);
//         }
//     });

// function initWelcomeAnimation() {
//     const welcomeAnimation = document.querySelector('.welcome-animation');
//     const progressBar = document.getElementById('loading-progress');

//     let progress = 0;
//     const interval = setInterval(() => {
//         progress += Math.random() * 10;
//         if (progress > 100) progress = 100;
//         progressBar.style.width = `${progress}%`;

//         if (progress >= 100) {
//             clearInterval(interval);
//             setTimeout(() => {
//                 welcomeAnimation.classList.add('fade-out');
//             }, 500);
//         }
//     }, 200);
// }
// document.addEventListener('DOMContentLoaded', initWelcomeAnimation);