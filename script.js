var player, cpu, score;
var board = [0,0,0,0,0,0,0,0,0];

function setplayer(a, b){
    player=a;
    cpu=b;
    document.getElementById("roles").style.display = "none";
    document.getElementById("instructions").style.display = "none";
    document.getElementById("tictactoe").style.display = "grid";
    if (cpu == 'X'){
        cpuplay(board);
    }
}

function updateBoard(id){
    var errormsg = "";
exit: if (board[id] == player){
        errormsg = "You have already played there! Select an empty square";
    }
    else if (board[id] == cpu){
        errormsg = "That spot has been used. Select an empty square.";
    }
    else {
        board.splice(id, 1, player);
        document.getElementById(id).innerHTML = player;
        document.getElementById(id).style.fontFamily = 'Arial';
        document.getElementById(id).style.fontSize = '750%';
        if (emptyspaces(board) == 0){
            disable();
            errormsg = "Tied game!<br> <br>Do you want to play again?";
            break exit;
        }
        cpuplay(board);
        if(check(board, cpu) == cpu){
            disable();
            errormsg = "Sorry! You lost the game.<br> <br>Do you want to play again?";
            break exit;
        }
    }
    document.getElementById("instructions").innerHTML = errormsg
    document.getElementById("instructions").style.display = "block";
}

function cpuplay(array){
    var optimal = 0;
    var min = Number.MAX_SAFE_INTEGER
    var max = Number.MIN_SAFE_INTEGER
    for (var i = 0; i < 9; i++) {
        if (array[i] == '0'){
            array[i] = cpu;
            if (cpu == 'X') {
                score = minimax(array, 0, false);
                alert(score);
                array[i] = '0';
                if (score > max){
                    max = score;
                    optimal = i;
                }
            }
            else {
                score = minimax(array, 0, true);
                array[i] = '0';
                if (score < min){
                    min = score;
                    optimal = i;
                }
            }
        }
    }
    array[optimal] = cpu;
    document.getElementById(optimal).innerHTML = cpu;
    document.getElementById(optimal).style.fontFamily = 'Arial';
    document.getElementById(optimal).style.fontSize = '750%';
    return array;
}

function minimax(array, depth, isMaximizing){
    var min = Number.MAX_SAFE_INTEGER
    var max = Number.MIN_SAFE_INTEGER
    var maximizing = player;
    var minimizing = cpu;

    if (cpu == 'X'){
      maximizing = cpu;
      minimizing = player;
    }

    if (check(array, maximizing) == maximizing){
      return score = 0 - depth;
    }
    else if (check(array, minimizing) == minimizing){
      return score = -19 + depth;
    }
    else if (emptyspaces(array) == 0) {
      return score = -10;
    }

    if (isMaximizing){
    for (var i = 0; i < 9; i++) {
        if (array[i] == '0'){
            array[i] = maximizing;
            score = minimax(array, depth + 1, false);
            array[i] = '0';
            max = Math.max(score, max);
        }
    }
    return max;
    }
    else {
      for (var i = 0; i < 9; i++) {
        if (array[i] == '0'){
            array[i] = minimizing;
            score = minimax(array, depth + 1, true);
            array[i] = '0';
            min = Math.min(score, min);
        }
      }
      return min;
    }
}

function check(array, user) {
    var winner;
    if (array[0] == array[1] && array[1] == array[2] && array[2] == user
        || array[3] == array[4] && array[4] == array[5] && array[5] == user
        || array[6] == array[7] && array[7] == array[8] && array[8] == user
        || array[0] == array[3] && array[3] == array[6] && array[6] == user
        || array[1] == array[4] && array[4] == array[7] && array[7] == user
        || array[2] == array[5] && array[5] == array[8] && array[8] == user
        || array[0] == array[4] && array[4] == array[8] && array[8] == user
        || array[2] == array[4] && array[4] == array[6] && array[6] == user) {
        winner = user;
    } 
    else {
        winner = ' ';
    }
    return winner;
}

function emptyspaces(array) {
    var count = 0;
    for (var i = 0; i < array.length; i++){
        if (array[i] == '0'){
            count++;
        }
    }
    return count;
}

function disable(){
    for(var i = 0; i < 9; i++){
        document.getElementById(i).onclick = null;
    }
    document.getElementById("playagain").style.display = "grid";
}