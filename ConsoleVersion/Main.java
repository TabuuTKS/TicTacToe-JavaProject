import java.util.Scanner;

public class Main {

    public static Board gameBoard = new Board();
    public static Player player1, player2;
    public static Scanner input = new Scanner(System.in);

    //initializes the game 
    public static void gameInit() {
        String tempName;
        char tempCell;
        System.out.print("Enter Name of Player1: ");
        tempName = input.next();
        System.out.print("Enter Peice of Player1: ");
        tempCell = input.next().charAt(0);
        player1 = new Player(tempName, Cell.getCellFromStr(tempCell));

        System.out.print("Enter Name of Player2: ");
        tempName = input.next();
        System.out.print("Enter Peice of Player2: ");
        tempCell = input.next().charAt(0);
        player2 = new Player(tempName, Cell.getCellFromStr(tempCell));
    }

    //the game loop
    public static void gameLoop() {
        int row, collumn;
        while(!(gameBoard.isBoardFull())) {
            System.out.println(player1.name+"'s Turn:");
            System.out.print("Enter Row Number: ");
            row = input.nextInt();
            System.out.print("Enter Collumn Number: ");
            collumn = input.nextInt();

            gameBoard.changeSymboll(row, collumn, player1.symboll);

            if(gameBoard.didSomeoneWon()) {
                break;
            }

            System.out.println(player2.name+"'s Turn:");
            System.out.print("Enter Row Number: ");
            row = input.nextInt();
            System.out.print("Enter Collumn Number: ");
            collumn = input.nextInt();

            gameBoard.changeSymboll(row, collumn, player2.symboll);
            
            if(gameBoard.didSomeoneWon()) {
                break;
            }
            else {
                continue;
            }
        }
    }

    //calculate results
    public static void results() {
        if (gameBoard.whoWon == Cell.X && player1.symboll == Cell.X) {
            System.out.println(player1.name+" Won");   
        }
        else if (gameBoard.whoWon == Cell.O && player1.symboll == Cell.O) {
            System.out.println(player1.name+" Won");   
        }
        else if (gameBoard.whoWon == Cell.X && player2.symboll == Cell.X) {
            System.out.println(player2.name+" Won");   
        }
        else if (gameBoard.whoWon == Cell.O && player2.symboll == Cell.O) {
            System.out.println(player2.name+" Won");   
        }
        else {
            System.out.println("Draw!!!, No One Won!!!");
        }
    }

    public static void main(String[] args) {
        gameInit();
        gameLoop();
        results();
    }
}