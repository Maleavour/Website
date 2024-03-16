// Variables to store video element and current playback time
var videoElement = document.getElementById('video-player1'); // Replace 'yourVideoId' with the actual ID of your video element
var currentTime = 0;

// Function to open the overlay and scroll to the top
function openOverlay() {
  // Show the overlay
  var overlay = document.getElementById('overlay');
  overlay.classList.add('active');
  
  // Show the overlay content with animation
  var overlayContent = document.querySelector('.overlay-content');
  overlayContent.classList.add('active');
  
  // Scroll to the top of the overlay
  window.scrollTo(0, 0);
}

// Function to close the overlay and pause the video
function closeOverlay() {
  // Hide the overlay content with animation
  var overlayContent = document.querySelector('.overlay-content');
  overlayContent.classList.remove('active');
  
  // Hide the overlay after animation
  setTimeout(function() {
      var overlay = document.getElementById('overlay');
      overlay.classList.remove('active');
  }, 300); // Wait for the animation to finish (300ms)
}

// Function to toggle the description visibility and expand dynamically
function toggleDescription() {
    var description = document.getElementById('description');
    var showMoreBtn = document.getElementById('showMoreBtn');

    if (description.classList.contains('expanded')) {
        description.classList.remove('expanded');
        description.style.height = '100px'; // Set back to initial height
        showMoreBtn.textContent = 'Read more'; // Update button text
    } else {
        description.classList.add('expanded');
        description.style.height = description.scrollHeight + 'px'; // Expand the description
        showMoreBtn.textContent = 'Read less'; // Update button text
    }
}

// Function to resume video playback when overlay is reopened
function resumePlayback() {
    // Set the playback time of the video to the stored value
    videoElement.currentTime = currentTime;
    
    // Play the video
    videoElement.play();
}
