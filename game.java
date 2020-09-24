import java.util.Scanner;

public class game {

  static int score;
  static char player;
  static char cpu;

  public static void main (String[] args) {
    char[] plays = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    String turn;
    Scanner scan = new Scanner(System.in);
    System.out.print("Who do you want to be? (X/O) ");
    String response = scan.next();
    response = response.toUpperCase();

    while (!response.equals("X") && !response.equals("O")) {
      System.out.print("Please enter a valid response. ");
      response = scan.next();
    }

    player = response.charAt(0);
    if (player == 'O') {
      cpu = 'X';
      turn = "cpu";
    } else {
      cpu = 'O';
      turn = "player";
    }

    while (emptyspaces(plays) > 0) {
      if (turn == "cpu") {
        cpuplay(plays);
        System.out.println("Winner:" + check(plays, cpu));        
        board(plays);
        if (emptyspaces(plays) == 0){
          System.out.println("Tie game!");
          break;
        }
        turn = "player";
      }
      if (turn == "player") {
        System.out.print("Your turn!\nEnter which square you want to play in (1-9). ");
        int move = scan.nextInt();
        while (move < 0 || move > 9) {
          System.out.print("Please enter a valid square number. ");
          move = scan.nextInt();
        }
        while (Character.isLetter(plays[move - 1])) {
          System.out.print("Sorry that square is used. Please enter an available square. ");
          move = scan.nextInt();
        }
        plays[move-1] = player;
        System.out.println("Winner:" + check(plays, player));
        turn = "cpu";
      }
    }
  }

  public static void board(char[] array) {
    System.out.println("  " + array[0] + "  |  " + array[1] + "  |  " + array[2]);
    System.out.println("_____|_____|_____");
    System.out.println("  " + array[3] + "  |  " + array[4] + "  |  " + array[5]);
    System.out.println("_____|_____|_____");
    System.out.println("  " + array[6] + "  |  " + array[7] + "  |  " + array[8]);
    System.out.println("     |     |     ");
  }

  public static char check(char[] array, char user) {
    char winner = 'n';
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

  public static char [] cpuplay(char[] array) {
    int optimal = 0;
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < 9; i++) {
      if (Character.isDigit(array[i])) {
        array[i] = cpu;
        score = minimax(array, 0, false);
        array[i] = (char)(i + '0' + 1);
        if (score > max){
          max = score;
          optimal = i;
        }
      }
    }
    array[optimal] = cpu;
    return array;
  }

  public static int minimax(char[] array, int depth, boolean isMaximizing){
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    if (check(array, cpu) == cpu){
      return score = 1;
    }
    else if (check(array, player) == cpu){
      return score = -1;
    }
    else if (emptyspaces(array) == 0) {
      return score = 0;
    }

    if (isMaximizing){
    for (int i = 0; i < 9; i++) {
      if (Character.isDigit(array[i])) {
        array[i] = cpu;
        score = minimax(array, depth + 1, false);
        array[i] = (char)(i + '0' + 1);
        max = Math.max(score, max);
      }
    }
    return max;
    }
    else {
      for (int i = 0; i < 9; i++) {
        if (Character.isDigit(array[i])) {
          array[i] = player;
          score = minimax(array, depth + 1, true);
          array[i] = (char)(i + '0' + 1);
          min = Math.min(score, min);
        }
      }
      return min;
    }
  }

  public static int emptyspaces(char[] array) {
    int count = 0;
    for (int i = 0; i < array.length; i++){
      if (Character.isDigit(array[i])){
        count++;
      }
    }
    return count;
  }
}