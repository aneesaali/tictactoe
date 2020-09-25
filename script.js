var player;
var cpu;
var board = [0,0,0,0,0,0,0,0,0];

function setplayer(a, b){
    player=a;
    cpu=b;
    document.getElementById("roles"). style.display = "none";
    document.getElementById("instructions"). style.display = "none";
    document.getElementById("tictactoe").style.display = "grid";
}

function updateBoard(id){
    board.splice(id, 1, player);
    document.getElementById(id).innerHTML = player;
    document.getElementById(id).style.fontFamily = 'Arial';
    document.getElementById(id).style.fontSize = '1000%';
}