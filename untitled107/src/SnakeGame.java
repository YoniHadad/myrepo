import java.util.Scanner;

public class SnakeGame {
    public static void main(String[] args) {

        System.out.println("Welcome to our snake game!");
        System.out.println("Move your snake up,down,right and left and eat the apple");
        System.out.println("Be award do not touch the walls and not yourself with your head");
        System.out.println("To start the game pleas enter: 1 , to Exit enter any other key");
        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();
        if (userInput == 1) {
            GameFrame frame = new GameFrame();
        } else {
            System.out.println("We hope to see you again soon!");
        }
    }
}
