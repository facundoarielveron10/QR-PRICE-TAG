let animateButton = function(e) {

    e.preventDefault;
    //reset animation
    e.target.classList.remove('animate');

    e.target.classList.add('animate');
    setTimeout(function(){
        e.target.classList.remove('animate');
    },700);
};

let bubblyButtons = document.getElementsByClassName("bubbly-button");
let bubblyButtons2 = document.getElementsByClassName("bubbly-button2");
let bubblyButtons3 = document.getElementsByClassName("bubbly-button3");

for (let i = 0; i < bubblyButtons.length; i++) {
    bubblyButtons[i].addEventListener('click', animateButton, false);
    bubblyButtons2[i].addEventListener('click', animateButton, false);
    bubblyButtons3[i].addEventListener('click', animateButton, false);
}