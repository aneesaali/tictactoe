import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class game {

  public static void main (String[] args){
    char [] plays = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    Scanner scan = new Scanner (System.in);
    System.out.print("Who do you want to be? (X/O) ");
    String response = scan.next();
    response = response.toUpperCase();

    while(!response.equals("X") && !response.equals("O")) {
        System.out.print("Please enter a valid response. ");
        response = scan.next();
      }

    char player = response.charAt(0);
    
    int cpu = ThreadLocalRandom.current().nextInt(0, 8 + 1);
    while (Character.isLetter(plays[cpu])) {
      cpu = ThreadLocalRandom.current().nextInt(0, 8 + 1);
    }
    plays[cpu] = 'O';
    board(plays);
    check(plays);
    System.out.print("Your turn!\nEnter which square you want to play in (1-9). ");
    int move = scan.nextInt();
    while (move < 0 || move > 9){
      System.out.print("Please enter a valid square number. ");
      move = scan.nextInt();
    }
    while (Character.isLetter(plays[move - 1])) {
      System.out.print("Sorry that square is used. Please enter an available square. ");
      move = scan.nextInt();
    }
    plays[move - 1] = player;
    board(plays);
  }

  public static void board (char [] array){
    System.out.println("  " + array[0] + "  |  " + array[1] + "  |  " + array[2]);
    System.out.println("_____|_____|_____");
    System.out.println("  " + array[3] + "  |  " + array[4] + "  |  " + array[5]);
    System.out.println("_____|_____|_____");
    System.out.println("  " + array[6] + "  |  " + array[7] + "  |  " + array[8]);
    System.out.println("     |     |     ");
  }

  public static String check (char [] array){
    boolean gameOver = true;
    for (int i = 0; i < array.length; i++){
      if (Character.isDigit(array[i])){
        gameOver = false;
      }
    }

    if (gameOver = false){
      return "Game Over.";
    }
    else if (array[0] == array[1] && array[1] == array[2] ||
             array[3] == array[4] && array[4] == array[5] ||
             array[6] == array[7] && array[7] == array[8] ||
             array[0] == array[3] && array[3] == array[6] ||
             array[1] == array[4] && array[4] == array[7] ||
             array[2] == array[5] && array[5] == array[8] ||
             array[0] == array[4] && array[4] == array[8] ||
             array[2] == array[4] && array[4] == array[6]) {
        return "Congratulations!";
    }
    else {
      return "";
    }
  }
}