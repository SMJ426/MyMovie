const searchBtn = document.getElementById('search-btn');
searchBtn.addEventListener('click', function(event) {
  event.preventDefault();

  const searchInput = document.getElementById('search-input');
  const searchTerm = searchInput.value.trim().toLowerCase();

  const searchMessage = document.getElementById('search-message');
  if (searchTerm) {
    searchMessage.textContent = `"${searchTerm}" 작품을 찾으시나요?`;
    searchMessage.classList.remove('hidden');
  } else {
    searchMessage.classList.add('hidden');
  }

});