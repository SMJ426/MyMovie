const likeButton = document.querySelector('.like');
const icons = likeButton.querySelectorAll('i');
let currentIconIndex = 0;

icons[currentIconIndex].classList.add('active');

likeButton.addEventListener('click', () => {
    //현재 아이콘
    icons[currentIconIndex].classList.remove('active');

    //다음 아이콘
    currentIconIndex = (currentIconIndex + 1) % icons.length;
    icons[currentIconIndex].classList.add('active');
});