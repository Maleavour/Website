// Variables to store video element and current playback time
var videoElement = document.getElementById('video-player1'); // Replace 'yourVideoId' with the actual ID of your video element
var currentTime = 0;


// Function to toggle the sidebar
function toggleSidebar() {
    var sidebar = document.getElementById('sidebar');
    sidebar.classList.toggle('active');
  }
  
  // Function to change the video in the iframe
  function changeVideo(videoUrl) {
    var videoPlayer = document.getElementById('videoPlayer');
    videoPlayer.src = videoUrl;
    
  }
  
  function updateTitle(TitleText)
  {
    var Title = document.getElementById('videoTitle');
    Title.textContent = TitleText;
  }

  function updateDescription(descText) {
    // Get the <p> element by its ID
    var paragraph = document.getElementById('description');
    paragraph.style.whiteSpace = 'pre-wrap';
    // Update the text content of the <p> element
    paragraph.textContent = descText;
  }
  
  function triggerFunctions()
  {
    var button1Clicked = false;
    var button2Clicked = false;
    var button3Clicked = false;
    var button4Clicked = false;
    var button5Clicked = false;
    var button6clicked = false;
    var button7clicked = false;
    var buttonchooser1 = document.getElementById('button1');
    var buttonchooser2 = document.getElementById('button2');
    var buttonchooser3 = document.getElementById('button3');
    var buttonchooser4 = document.getElementById('button4');
    buttonchooser1.addEventListener('click', function() {
      button1Clicked = true;
      if(button1Clicked == true) 
    {
      updateTitle('Setting Up a FRC Wpilib Project');
      changeVideo('https://www.youtube.com/embed/5TSDfkR9S-E?si=zozR51DarsTm2bTx');
      updateDescription('testing works for button #1');
    }
    else if(button2Clicked == true)
    {
      updateTitle('Title #2');
      changeVideo('https://www.youtube.com/embed/6Uhgm-A8C3o?si=OfErzHqE8R-VwEqh');
      updateDescription('testing works for button #2');
    }
    else if(button3Clicked == true)
    {
      updateTitle('Title #3');
      changeVideo('https://www.youtube.com/embed/6Uhgm-A8C3o?si=OfErzHqE8R-VwEqh')
      updateDescription('testing works for button #3');
    }
    else if(button4Clicked == true)
    {
      updateTitle('Title #4');
      changeVideo('https://www.youtube.com/embed/6Uhgm-A8C3o?si=OfErzHqE8R-VwEqh')
      updateDescription('testing works for button #4');
    }
    else
    {
      var keepDescSame = document.getElementById('description');
      var originalDescText = keepDescSame.textContent;
      keepDescSame.textContent = originalDescText;
    }
    });
    buttonchooser2.addEventListener('click', function() {
      button2Clicked = true;
      if(button1Clicked == true) 
    {
      updateTitle('Title #1');
      changeVideo('https://www.youtube.com/embed/6Uhgm-A8C3o?si=OfErzHqE8R-VwEqh');
      updateDescription('testing works for button #1');
    }
    else if(button2Clicked == true)
    {
      updateTitle('Title #2');
      changeVideo('https://www.youtube.com/embed/6Uhgm-A8C3o?si=OfErzHqE8R-VwEqh');
      updateDescription('testing works for button #2');
    }
    else if(button3Clicked == true)
    {
      updateTitle('Title #3');
      changeVideo('https://www.youtube.com/embed/6Uhgm-A8C3o?si=OfErzHqE8R-VwEqh')
      updateDescription('testing works for button #3');
    }
    else if(button4Clicked == true)
    {
      updateTitle('Title4');
      changeVideo('https://www.youtube.com/embed/6Uhgm-A8C3o?si=OfErzHqE8R-VwEqh')
      updateDescription('testing works for button #4');
    }
    else
    {
      var keepDescSame = document.getElementById('description');
      var originalDescText = keepDescSame.textContent;
      keepDescSame.textContent = originalDescText;

      var keepVideoSame = document.getElementById('videoPlayer');
      var originalVideo = keepVideoSame.src;
      keepVideoSame.src = originalVideo;

      var keepTitleSame = document.getElementById('videoTitle');
      var originalTitleText = keepTitleSame.textContent;
      keepTitleSame.textContent = originalTitleText;
    }
    });
    buttonchooser3.addEventListener('click', function() {
      button3Clicked = true;
      if(button1Clicked == true) 
    {
      updateTitle('Title #1');
      changeVideo('https://www.youtube.com/embed/6Uhgm-A8C3o?si=OfErzHqE8R-VwEqh');
      updateDescription('testing works for button #1');
    }
    else if(button2Clicked == true)
    {
      updateTitle('Title #2');
      changeVideo('https://www.youtube.com/embed/6Uhgm-A8C3o?si=OfErzHqE8R-VwEqh');
      updateDescription('testing works for button #2');
    }
    else if(button3Clicked == true)
    {
      updateTitle('Title #3');
      changeVideo('https://www.youtube.com/embed/6Uhgm-A8C3o?si=OfErzHqE8R-VwEqh')
      updateDescription('testing works for button #3');
    }
    else if(button4Clicked == true)
    {
      updateTitle('Title #4');
      changeVideo('https://www.youtube.com/embed/6Uhgm-A8C3o?si=OfErzHqE8R-VwEqh')
      updateDescription('testing works for button #4');
    }
    else
    {
      var keepDescSame = document.getElementById('description');
      var originalDescText = keepDescSame.textContent;
      keepDescSame.textContent = originalDescText;
    }
    });
    buttonchooser4.addEventListener('click', function() {
      button4Clicked = true;
      if(button1Clicked == true) 
    {
      updateTitle('Title #1');
      changeVideo('https://www.youtube.com/embed/6Uhgm-A8C3o?si=OfErzHqE8R-VwEqh');
      updateDescription('testing works for button #1');
    }
    else if(button2Clicked == true)
    {
      updateTitle('Title #2');
      changeVideo('https://www.youtube.com/embed/6Uhgm-A8C3o?si=OfErzHqE8R-VwEqh');
      updateDescription('testing works for button #2');
    }
    else if(button3Clicked == true)
    {
      updateTitle('Title #3');
      changeVideo('https://www.youtube.com/embed/6Uhgm-A8C3o?si=OfErzHqE8R-VwEqh')
      updateDescription('testing works for button #3');
    }
    else if(button4Clicked == true)
    {
      updateTitle('Title #4');
      changeVideo('https://www.youtube.com/embed/6Uhgm-A8C3o?si=OfErzHqE8R-VwEqh')
      updateDescription('testing works for button #4');
    }
    else
    {
      var keepDescSame = document.getElementById('description');
      var originalDescText = keepDescSame.textContent;
      keepDescSame.textContent = originalDescText;
    }
    });

    
  }

  
  // Function to open the overlay (you can add your existing function here)
  function openOverlay() {
    // Show the overlay
    var overlay = document.getElementById('overlay');
    overlay.classList.add('active');
    
    // Show the overlay content with animation
    var overlayContent = document.querySelector('.overlay-content');
    overlayContent.classList.add('active');
    
    // Scroll to the top of the overlay
    window.scrollTo(0, 0);
  
    // Update the drop-down button text and options
    var dropDownBtn = document.getElementById('dropDownBtn');
    dropDownBtn.textContent = 'v - Open Overlay'; // Change text when overlay is open
    var dropdownContent = document.getElementById('dropdownContent');
    dropdownContent.style.display = 'block'; // Show dropdown options
  }
  

// Function to open the overlay and scroll to the top


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

  // Update the drop-down button text and options
  var dropDownBtn = document.getElementById('dropDownBtn');
  dropDownBtn.textContent = 'Open Overlay'; // Change text when overlay is closed
  var dropdownContent = document.getElementById('dropdownContent');
  dropdownContent.style.display = 'none'; // Hide dropdown options

  // Pause the video
  videoElement.pause();
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






