package TicTacToe;

import java.util.Scanner;

public class TicTacToe {
    public static int xCounter = 0;
    public static int oCounter = 0;
    public static char[][] board = new char[3][3];
    public static int turn = 0;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        resetGame();
        
        while (true) {    
            System.out.println("Enter a set of coordinates to place your an 'x'");
            System.out.println("An example coordiante could be '1 1'");
            try {
                int firsrtCoordinate = scan.nextInt()-1;
                int secondCooridnate = scan.nextInt()-1;

                if (firsrtCoordinate > 2 || firsrtCoordinate < 0 || secondCooridnate > 2 || secondCooridnate < 0) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (board[firsrtCoordinate][secondCooridnate] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    updateBoard(board, firsrtCoordinate, secondCooridnate, turn % 2);
                    printBoard(board);
                    String gameState = checkGameState(board);
                   
                    if (gameState.contains("wins") || gameState.contains("Draw")) {
                        System.out.println(gameState);
                        System.out.println("To play again type 'x' else type 'o'");
                        if (scan.next().equals("x")) {
                            resetGame();
                        } else {    
                            System.out.println("Thanks for playing");
                            break;
                        }

                    }
                    turn++;
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }
        }
    
    
        
        scan.close();
    }

    private static void updateBoard(char[][] board, int firstCoordinate, int secondCoordinate, int player) {
        if (player == 0) {
            board[firstCoordinate][secondCoordinate] = 'X';
            xCounter++;
            return;
        }
        board[firstCoordinate][secondCoordinate] = 'O';
        oCounter++;
    }
    
    private static String checkGameState(char[][] board) {
        boolean xWins = false;
        boolean oWins = false;
        for (int i = 0; i < 3; i++) {
            int row = 0;
            int column = 0;
            int leftToRightDiagonal = 0;
            int rightToLeftDiagonal = 0;

            for(int j = 0; j < 3; j++) {
                row += board[i][j];
                column += board[j][i];
                leftToRightDiagonal += board[j][j];
                rightToLeftDiagonal += board[j][2-j];
            }

            xWins = xWins || row == 264 || column == 264 || leftToRightDiagonal == 264 || rightToLeftDiagonal == 264;
            oWins = oWins || row == 237 || column == 237 || leftToRightDiagonal == 237 || rightToLeftDiagonal == 237;
        }
        
        if (xWins) {
            return "X wins";
        } else if (oWins) {
            return "O wins";
        } else if (xCounter + oCounter == 9) {
            return "Draw";
        }  
        return "";
        
    }

    private static void printBoard(char[][] board) {
        System.out.print("\033[H\033[2J");  // <-- Clear screen
        String topBorder = "---------";
        System.out.println(topBorder);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (j % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
                if (j % 3 == 2) {
                    System.out.println("|");
                }
            }
        }
        System.out.println(topBorder);
    }

    private static void resetGame() {
        xCounter = 0;
        oCounter = 0;
        turn = 0;
        for (int i = 0; i < 9; i++) {
            board[i/3][i%3] = ' ';
        }
        printBoard(board);
    }
}
