package main.java.string.class_problems;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    // Method to determine the winner of one round
    public static String playRound(String playerMove, String computerMove) {

        if (playerMove.equals(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
            (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
            (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {

            return "Player Wins";
        }

        return "Computer Wins";
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Possible moves
        String[] moves = {"Rock", "Paper", "Scissors"};

        // Number of rounds
        int rounds = 5;

        // Arrays to store round details
        String[] playerMoves = new String[rounds];
        String[] computerMoves = new String[rounds];
        String[] results = new String[rounds];

        // Score counters
        int wins = 0;
        int losses = 0;
        int draws = 0;

        System.out.println("===== ROCK-PAPER-SCISSORS GAME =====");

        // Play 5 rounds
        for (int i = 0; i < rounds; i++) {

            System.out.println("\nRound " + (i + 1));

            System.out.print("Enter your move (Rock/Paper/Scissors): ");
            String playerMove = scanner.nextLine();

            // Format player's input
            playerMove = playerMove.substring(0, 1).toUpperCase()
                    + playerMove.substring(1).toLowerCase();

            // Generate computer's move randomly
            String computerMove = moves[random.nextInt(3)];

            // Determine result
            String result = playRound(playerMove, computerMove);

            // Store round details
            playerMoves[i] = playerMove;
            computerMoves[i] = computerMove;
            results[i] = result;

            // Display current round result
            System.out.println("Player Move   : " + playerMove);
            System.out.println("Computer Move : " + computerMove);
            System.out.println("Result        : " + result);

            // Update score
            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
        }

        // Calculate win percentage
        double winPercentage = ((double) wins / rounds) * 100;

        // Final summary table
        System.out.println("\n========== FINAL SUMMARY ==========");

        System.out.printf("%-8s %-15s %-17s %-15s%n",
                "Round", "Player Move", "Computer Move", "Result");

        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < rounds; i++) {

            System.out.printf("%-8d %-15s %-17s %-15s%n",
                    (i + 1),
                    playerMoves[i],
                    computerMoves[i],
                    results[i]);
        }

        // Final scoreboard
        System.out.println("\n========== SCOREBOARD ==========");
        System.out.println("Wins   : " + wins);
        System.out.println("Losses : " + losses);
        System.out.println("Draws  : " + draws);
        System.out.printf("Win %%  : %.1f%%%n", winPercentage);

        scanner.close();
    }
}
