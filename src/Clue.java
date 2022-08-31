// @author Michael Q

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Clue {

    static final boolean DEBUG = false;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Total number of players: ");
        int menu = scan.nextInt();
        if (menu < 3 || menu > 6) {
            System.out.println("ERROR: Number of players must be between 3 - 6");
            System.exit(0);
        }

        // Players and their cards: player, cards (first is num of cards held)
        int[][] players = new int[menu][22];

        System.out.print("Your turn order: ");
        int yourTurn = scan.nextInt();
        if (yourTurn < 1 || yourTurn > players.length) {
            System.out.println("ERROR: Your turn order must be between 1 - " + players.length);
            System.exit(0);
        }

        int count = 21;
        for (int i = 1; i <= players.length; i++) {
            if (yourTurn == i) {
                System.out.print("Number of cards you have: ");
            } else {
                System.out.print("Number of cards player " + i + " has: ");
            }

            menu = scan.nextInt();
            players[i - 1][0] = menu;
            count -= menu;
        }

        if (count != 3) {
            System.out.println("ERROR: Too many or too little cards");
            System.exit(0);
        }

        System.out.println("\nWhat cards do you have?");
        System.out.println("1: Colonel Mustard      8: Candlestick      15: Dining Room");
        System.out.println("2: Professor Plum       9: Revolver         16: Kitchen");
        System.out.println("3: Mr. Green            10: Rope            17: Ballroom");
        System.out.println("4: Mrs. Peacock         11: Lead Pipe       18: Conservatory");
        System.out.println("5: Miss Scarlet         12: Wrench          19: Billiard Room");
        System.out.println("6: Mrs. White           13: Hall            20: Library");
        System.out.println("7: Knife                14: Lounge          21: Study");

        for (int i = 0; i < players[yourTurn - 1][0]; i++) {
            menu = scan.nextInt();

            if (menu > 0 && menu < 22 && players[yourTurn - 1][menu] == 0) {
                for (int[] player : players) {
                    player[menu] = 1;   // If not duplicate
                }
            } else {
                i--;    // If duplicate, ignore
            }
        }
        System.out.println();

        if (DEBUG) {
            System.out.println("DEBUG: Starting card");
            printCard(players, yourTurn);
        }

        int turn = 1;
        boolean end = false;
        int[] solution = new int[3];
        String[] cards = {"???????", "Colonel Mustard", "Professor Plum", "Mr. Green",
            "Mrs. Peacock", "Miss Scarlet", "Mrs. White", "Knife", "Candlestick",
            "Revolver", "Rope", "Lead Pipe", "Wrench", "Hall", "Lounge", "Dining Room",
            "Kitchen", "Ballroom", "Conservatory", "Billiard Room", "Library", "Study"};

        // History of guesses: first num is player and then guess made
        ArrayList<List<Integer>> guessHistory = new ArrayList<>();

        // Guesses that contain at least one solution part: first num is player and then guess made
        ArrayList<List<Integer>> possibleSolution = new ArrayList<>();

        do {
            System.out.println("Turn " + turn);

            turn:
            for (int i = 1; i <= players.length; i++) {
                if (i == yourTurn) {
                    printCard(players, yourTurn);
                    System.out.println("Your turn");
                } else {
                    System.out.println("Player " + i + "'s turn");
                }

                System.out.println("1: Guess");
                System.out.println("2: Skip");
                System.out.println("3: Print card");
                System.out.print("4: Show answer (");
                count = 0;
                for (int j : solution) {
                    if (j != 0) {
                        count++;
                    }
                }
                System.out.println(count + "/3)");
                System.out.println("0: End game");

                menu = scan.nextInt();
                while (menu < 0 || menu > 4) {
                    System.out.println("Enter 0 - 4");
                    menu = scan.nextInt();
                }
                System.out.println();

                switch (menu) {
                    case 1 -> {
                        int[] guess = new int[3];

                        System.out.println("Suspect: ");
                        System.out.println("1: Colonel Mustard");
                        System.out.println("2: Professor Plum");
                        System.out.println("3: Mr. Green");
                        System.out.println("4: Mrs. Peacock");
                        System.out.println("5: Miss Scarlet");
                        System.out.println("6: Mrs. White");

                        menu = scan.nextInt();
                        while (menu < 1 || menu > 6) {
                            System.out.println("Enter 1 - 6");
                            menu = scan.nextInt();
                        }
                        guess[0] = menu;

                        System.out.println("\nWeapon: ");
                        System.out.println("1: Knife");
                        System.out.println("2: Candlestick");
                        System.out.println("3: Revolver");
                        System.out.println("4: Rope");
                        System.out.println("5: Lead Pipe");
                        System.out.println("6: Wrench");

                        menu = scan.nextInt();
                        while (menu < 1 || menu > 6) {
                            System.out.println("Enter 1 - 6");
                            menu = scan.nextInt();
                        }
                        guess[1] = menu + 6;

                        System.out.println("\nRoom: ");
                        System.out.println("1: Hall");
                        System.out.println("2: Lounge");
                        System.out.println("3: Dining Room");
                        System.out.println("4: Kitchen");
                        System.out.println("5: Ballroom");
                        System.out.println("6: Conservatory");
                        System.out.println("7: Billiard Room");
                        System.out.println("8: Library");
                        System.out.println("9: Study");

                        menu = scan.nextInt();
                        while (menu < 1 || menu > 9) {
                            System.out.println("Enter 1 - 9");
                            menu = scan.nextInt();
                        }
                        guess[2] = menu + 12;

                        System.out.println("\nGuess: " + cards[guess[0]] + " with the " + cards[guess[1]] + " in the " + cards[guess[2]]);

                        count = 0;
                        for (int j = 1; j < players.length; j++) {
                            int player = i + j > players.length ? i + j - players.length : i + j;
                            String temp;

                            if (i == yourTurn) {
                                scan.nextLine();
                                System.out.print("Does player " + player + " show you a card (y/n)? ");
                                temp = scan.next();

                                if (temp.equalsIgnoreCase("y") || temp.equalsIgnoreCase("yes")) {
                                    System.out.println("What card were you shown?: ");
                                    System.out.println(guess[0] + ": " + cards[guess[0]]);
                                    System.out.println(guess[1] + ": " + cards[guess[1]]);
                                    System.out.println(guess[2] + ": " + cards[guess[2]]);

                                    menu = scan.nextInt();
                                    while (menu != guess[0] && menu != guess[1] && menu != guess[2]) {
                                        System.out.println("Enter " + guess[0] + ", " + guess[1] + ", or " + guess[2]);
                                        menu = scan.nextInt();
                                    }

                                    players[player - 1][menu] = 3;  // Marks that they have that card
                                    for (int k = 0; k < players.length; k++) {
                                        if (k != player - 1) {
                                            players[k][menu] = 1;   // Marks that no one else has that card
                                        }
                                    }

                                    if (DEBUG) {
                                        System.out.println("\nDEBUG: Card after shown " + cards[menu] + " by player " + player);
                                        printCard(players, yourTurn);
                                    }

                                    break;
                                } else {    // If didn't show
                                    for (int k = 0; k < guess.length; k++) {
                                        players[player - 1][guess[k]] = 1;  // Marks that they don't have those cards
                                    }

                                    if (DEBUG) {
                                        System.out.println("\nDEBUG: Card after player " + player + " didn't show either " + cards[guess[0]] + ", " + cards[guess[1]] + ", or " + cards[guess[2]]);
                                        printCard(players, yourTurn);
                                    }

                                    count++;    // Increases count every time no one shows
                                    if (count == players.length - 1) {  // If no one showed, at least one has to be part of the solution
                                        for (int k = 0; k < guess.length; k++) {
                                            if (players[yourTurn - 1][guess[k]] == 0 || players[yourTurn - 1][guess[k]] == 2) { // If not marked/not a card you have
                                                players[yourTurn - 1][guess[k]] = 3;    // Mark as found solution

                                                if (guess[k] <= 6) {        // Add to correct solution index
                                                    solution[0] = guess[k];
                                                } else if (guess[k] > 6 && guess[k] <= 12) {
                                                    solution[1] = guess[k];
                                                } else {
                                                    solution[2] = guess[k];
                                                }
                                            }
                                        }

                                        if (DEBUG) {
                                            System.out.println("\nDEBUG: Card after no one showed you a card");
                                            printCard(players, yourTurn);
                                        }
                                    }
                                }
                            } else {    // If not your turn
                                if (player == yourTurn) {
                                    scan.nextLine();
                                    System.out.print("Do you show them a card (y/n)? ");
                                    temp = scan.next();

                                    if (temp.equalsIgnoreCase("y") || temp.equalsIgnoreCase("yes")) {
                                        if (DEBUG) {
                                            System.out.println();
                                        }
                                        break;
                                    } else {
                                        count++;    // Increment if didn't show
                                    }
                                } else {    // If not your turn
                                    scan.nextLine();
                                    System.out.print("Does player " + player + " show them a card (y/n)? ");
                                    temp = scan.next();

                                    if (temp.equalsIgnoreCase("y") || temp.equalsIgnoreCase("yes")) {
                                        guessHistory.add(Arrays.asList(player - 1, guess[0], guess[1], guess[2]));    // They have at least one of those cards

                                        for (int k = 0; k < guess.length; k++) {
                                            if (players[player - 1][guess[k]] == 0) {
                                                players[player - 1][guess[k]] = 2;  // Mark as possibly have if no other mark
                                            }
                                        }

                                        if (DEBUG) {
                                            System.out.println("\nDEBUG: Card after player " + player + " showed player " + i + " a card");
                                            printCard(players, yourTurn);
                                        }

                                        break;
                                    } else {    // If didn't show
                                        for (int k = 0; k < guess.length; k++) {
                                            players[player - 1][guess[k]] = 1;  // Marks that they don't have those cards
                                        }

                                        if (DEBUG) {
                                            System.out.println("\nDEBUG: Card after player " + player + " didn't show player " + i + " a card");
                                            printCard(players, yourTurn);
                                        }

                                        count++;    // Increases count every time no one shows
                                    }
                                }

                                if (count == players.length - 1) {  // If no one showed, at least one has to be part of the solution
                                    possibleSolution.add(Arrays.asList(i - 1, guess[0], guess[1], guess[2]));  // Contains at least one solution part

                                    for (int k = 0; k < guess.length; k++) {
                                        if (players[yourTurn - 1][guess[k]] == 0) // If not marked/not a card you have
                                        {
                                            players[yourTurn - 1][guess[k]] = 2;    // Mark as potential solution
                                        }
                                    }

                                    if (DEBUG) {
                                        System.out.println("\nDEBUG: Card after no one showed player " + i + " a card");
                                        printCard(players, yourTurn);
                                    }
                                }
                            }
                        }

                        if (!DEBUG) {
                            System.out.println();
                        }

                        checkHistory(guessHistory, possibleSolution, players, solution, yourTurn);  // Check guess history after each players turn if guess made
                    }
                    case 3 -> {
                        printCard(players, yourTurn);
                        i--;    // Go back to current players turn
                    }
                    case 4 -> {
                        System.out.println("It was " + cards[solution[0]] + " with the " + cards[solution[1]] + " in the " + cards[solution[2]] + "\n");
                        i--;    // Go back to current players turn
                    }
                    case 0 -> {
                        end = true;
                        break turn;
                    }
                }
            }

            turn++;
        } while (!end);
    }

    public static void checkHistory(ArrayList<List<Integer>> guessHistory, ArrayList<List<Integer>> possibleSolution, int[][] players, int[] solution, int yourTurn) {
        if (DEBUG) {
            System.out.println("DEBUG: History before check:");
            System.out.println("Guess history:\n" + Arrays.deepToString(guessHistory.toArray()).replace("], ", "]\n").replace("[[", "[").replace("]]", "]") + "\n");
            System.out.println("Possible solution history:\n" + Arrays.deepToString(possibleSolution.toArray()).replace("], ", "]\n").replace("[[", "[").replace("]]", "]") + "\n");
        }

        int changes;
        do {
            int count;
            changes = 0;

            for (int i = 0; i < players.length; i++) {  // Column check/cleaning
                count = 0;
                if (i == yourTurn - 1) {
                    for (int j = 1; j <= 6; j++) {  // Suspects cleaning
                        if (players[i][j] == 3) {
                            count++;    // Count the cards you know are part of the solution in the suspects
                        }
                    }

                    if (count == 1) {   // If one is found, no others can be part of the solution
                        for (int j = 1; j <= 6; j++) {
                            if (players[i][j] != 1 && players[i][j] != 3) {
                                players[i][j] = 1;
                                changes++;
                            }
                        }
                    }

                    count = 0;
                    for (int j = 1; j <= 6; j++) {  // Suspects check
                        if (players[i][j] == 1) {
                            count++;    // Count the cards you know aren't part of the solution in the suspects
                        }
                    }

                    if (count == 5) {   // If all but one is not it, last must be part of the solution
                        for (int j = 1; j <= 6; j++) {
                            if (players[i][j] != 1 && players[i][j] != 3) {
                                players[i][j] = 3;

                                solution[0] = j;    // Add to correct solution index

                                for (int k = 0; k < players.length; k++) {
                                    if (k != yourTurn - 1) {
                                        players[k][j] = 1;    // Marks that no one else has that card
                                    }
                                }

                                changes++;
                            }
                        }
                    }

                    count = 0;
                    for (int j = 7; j <= 12; j++) {  // Weapons cleaning
                        if (players[i][j] == 3) {
                            count++;    // Count the cards you know are part of the solution in the weapons
                        }
                    }

                    if (count == 1) {   // If one is found, no others can be part of the solution
                        for (int j = 7; j <= 12; j++) {
                            if (players[i][j] != 1 && players[i][j] != 3) {
                                players[i][j] = 1;
                                changes++;
                            }
                        }
                    }

                    count = 0;
                    for (int j = 7; j <= 12; j++) {  // Weapons check
                        if (players[i][j] == 1) {
                            count++;    // Count the cards you know aren't part of the solution in the Weapons
                        }
                    }

                    if (count == 5) {   // If all but one is not it, last must be part of the solution
                        for (int j = 7; j <= 12; j++) {
                            if (players[i][j] != 1 && players[i][j] != 3) {
                                players[i][j] = 3;

                                solution[1] = j;    // Add to correct solution index

                                for (int k = 0; k < players.length; k++) {
                                    if (k != yourTurn - 1) {
                                        players[k][j] = 1;    // Marks that no one else has that card
                                    }
                                }

                                changes++;
                            }
                        }
                    }

                    count = 0;
                    for (int j = 13; j <= 21; j++) {  // Rooms cleaning
                        if (players[i][j] == 3) {
                            count++;    // Count the cards you know are part of the solution in the rooms
                        }
                    }

                    if (count == 1) {   // If one is found, no others can be part of the solution
                        for (int j = 13; j <= 21; j++) {
                            if (players[i][j] != 1 && players[i][j] != 3) {
                                players[i][j] = 1;
                                changes++;
                            }
                        }
                    }

                    count = 0;
                    for (int j = 13; j <= 21; j++) {  // Rooms check
                        if (players[i][j] == 1) {
                            count++;    // Count the cards you know aren't part of the solution in the Weapons
                        }
                    }

                    if (count == 8) {   // If all but one is not it, last must be part of the solution
                        for (int j = 13; j <= 21; j++) {
                            if (players[i][j] != 1 && players[i][j] != 3) {
                                players[i][j] = 3;

                                solution[2] = j;    // Add to correct solution index

                                for (int k = 0; k < players.length; k++) {
                                    if (k != yourTurn - 1) {
                                        players[k][j] = 1;    // Marks that no one else has that card
                                    }
                                }

                                changes++;
                            }
                        }
                    }
                } else {    // If not you
                    for (int j = 1; j < players[i].length; j++) {
                        if (players[i][j] == 3) {
                            count++;    // Count the cards you know they have
                        }
                    }

                    if (count == players[i][0]) {   // If they have their max, then they have no other cards
                        for (int j = 1; j < players[i].length; j++) {
                            if (players[i][j] != 1 && players[i][j] != 3) {
                                players[i][j] = 1;
                                changes++;
                            }
                        }
                    }
                }
            }

            for (int i = 1; i < players[0].length; i++) {   // Row check
                count = 0;
                for (int[] player : players) {
                    if (player[i] == 1) {
                        count++;    // Count the cards you know
                    }
                }

                if (count == players.length - 1) {   // If all others in row are found, they must have the last or is part of solution
                    for (int[] player : players) {
                        if (player[i] != 1 && player[i] != 3) {
                            player[i] = 3;

                            if (players[yourTurn - 1][i] == 3) {
                                if (i <= 6) {       // Add to correct solution index
                                    solution[0] = i;
                                } else if (i > 6 && i <= 12) {
                                    solution[1] = i;
                                } else {
                                    solution[2] = i;
                                }
                            }

                            changes++;
                        }
                    }
                }
            }

            for (Iterator<List<Integer>> it = guessHistory.iterator(); it.hasNext();) {
                List<Integer> guess = it.next();
                count = 0;
                for (int i = 1; i < guess.size(); i++) {
                    if (players[guess.get(0)][guess.get(i)] == 1) {
                        count++;
                    } else if (players[guess.get(0)][guess.get(i)] == 3) {
                        if (DEBUG) {
                            System.out.println("DEBUG: Guess history " + Arrays.toString(guess.toArray()) + " already found, removed");
                        }

                        it.remove();    // If already found, delete
                        count = 0;

                        break;
                    }
                }

                if (count == 2) {   // If two are not it, then they must have the third
                    for (int i = 1; i < guess.size(); i++) {
                        if (players[guess.get(0)][guess.get(i)] != 1) {
                            players[guess.get(0)][guess.get(i)] = 3;

                            for (int j = 0; j < players.length; j++) {
                                if (j != guess.get(0)) {
                                    players[j][guess.get(i)] = 1;    // Marks that no one else has that card
                                }
                            }

                            if (DEBUG) {
                                System.out.println("DEBUG: Guess history " + Arrays.toString(guess.toArray()) + " removed");
                                printCard(players, yourTurn);
                            }

                            it.remove();
                            changes++;

                            break;
                        }
                    }
                }
            }

            count = 0;
            for (Iterator<List<Integer>> it = possibleSolution.iterator(); it.hasNext();) {
                List<Integer> possible = it.next();
                for (int i = 1; i < possible.size(); i++) {
                    if (players[possible.get(0)][possible.get(i)] == 3) {
                        count++;
                    } else if (players[yourTurn - 1][possible.get(i)] == 3) {
                        if (DEBUG) {
                            System.out.println("DEBUG: Possible solution history " + Arrays.toString(possible.toArray()) + " already found, removed");
                        }

                        it.remove();    // If already found, delete
                        count = 0;

                        break;
                    }
                }

                if (count == 2) {   // If they hold two, then the third must be part of solution (most of the time)
                    for (int i = 1; i < possible.size(); i++) {
                        if (players[possible.get(0)][possible.get(i)] != 3) {
                            players[yourTurn - 1][possible.get(i)] = 3;

                            if (possible.get(i) <= 6) {        // Add to correct solution index
                                solution[0] = possible.get(i);
                            } else if (possible.get(i) > 6 && possible.get(i) <= 12) {
                                solution[1] = possible.get(i);
                            } else {
                                solution[2] = possible.get(i);
                            }

                            for (int j = 0; j < players.length; j++) {
                                if (j != possible.get(0)) {
                                    players[possible.get(j)][possible.get(i)] = 1;  // Marks that no one else has that card
                                }
                            }

                            if (DEBUG) {
                                System.out.println("DEBUG: Possible solution history " + Arrays.toString(possible.toArray()) + " removed");
                                printCard(players, yourTurn);
                            }

                            it.remove();
                            changes++;

                            break;
                        }
                    }
                }
            }

            if (DEBUG) {
                if (changes != 0) {
                    System.out.println("DEBUG: Card after " + changes + " changes made");
                    printCard(players, yourTurn);
                    System.out.println("DEBUG: History after check (" + changes + " changes):");
                    System.out.println("Guess history:\n" + Arrays.deepToString(guessHistory.toArray()).replace("], ", "]\n").replace("[[", "[").replace("]]", "]") + "\n");
                    System.out.println("Possible solution history:\n" + Arrays.deepToString(possibleSolution.toArray()).replace("], ", "]\n").replace("[[", "[").replace("]]", "]") + "\n");
                } else {
                    System.out.println("DEBUG: No changes made\n");
                }
            }
        } while (changes != 0);
    }

    public static void printCard(int[][] players, int yourTurn) {
        System.out.print("-------------------");
        for (int i = 1; i <= players.length; i++) {
            System.out.print("----");
        }

        System.out.print("\n| Players         | U |");
        for (int i = 1; i <= players.length; i++) {
            if (i != yourTurn) {
                System.out.print(" " + i + " |");
            }
        }

        System.out.print("\n-------------------");
        for (int i = 1; i <= players.length; i++) {
            System.out.print("----");
        }

        System.out.print("\n| Suspects         ");
        for (int i = 1; i < players.length; i++) {
            System.out.print("    ");
        }
        System.out.print("   |");

        System.out.print("\n-------------------");
        for (int i = 1; i <= players.length; i++) {
            System.out.print("----");
        }

        System.out.print("\n| Colonel Mustard |");
        printMarks(players, 1, yourTurn);
        System.out.print("\n| Professor Plum  |");
        printMarks(players, 2, yourTurn);
        System.out.print("\n| Mr. Green       |");
        printMarks(players, 3, yourTurn);
        System.out.print("\n| Mrs. Peacock    |");
        printMarks(players, 4, yourTurn);
        System.out.print("\n| Miss Scarlet    |");
        printMarks(players, 5, yourTurn);
        System.out.print("\n| Mrs. White      |");
        printMarks(players, 6, yourTurn);

        System.out.print("\n-------------------");
        for (int i = 1; i <= players.length; i++) {
            System.out.print("----");
        }

        System.out.print("\n| Weapons          ");
        for (int i = 1; i < players.length; i++) {
            System.out.print("    ");
        }
        System.out.print("   |");

        System.out.print("\n-------------------");
        for (int i = 1; i <= players.length; i++) {
            System.out.print("----");
        }

        System.out.print("\n| Knife           |");
        printMarks(players, 7, yourTurn);
        System.out.print("\n| Candlestick     |");
        printMarks(players, 8, yourTurn);
        System.out.print("\n| Revolver        |");
        printMarks(players, 9, yourTurn);
        System.out.print("\n| Rope            |");
        printMarks(players, 10, yourTurn);
        System.out.print("\n| Lead Pipe       |");
        printMarks(players, 11, yourTurn);
        System.out.print("\n| Wrench          |");
        printMarks(players, 12, yourTurn);

        System.out.print("\n-------------------");
        for (int i = 1; i <= players.length; i++) {
            System.out.print("----");
        }

        System.out.print("\n| Rooms            ");
        for (int i = 1; i < players.length; i++) {
            System.out.print("    ");
        }
        System.out.print("   |");

        System.out.print("\n-------------------");
        for (int i = 1; i <= players.length; i++) {
            System.out.print("----");
        }

        System.out.print("\n| Hall            |");
        printMarks(players, 13, yourTurn);
        System.out.print("\n| Lounge          |");
        printMarks(players, 14, yourTurn);
        System.out.print("\n| Dining Room     |");
        printMarks(players, 15, yourTurn);
        System.out.print("\n| Kitchen         |");
        printMarks(players, 16, yourTurn);
        System.out.print("\n| Ballroom        |");
        printMarks(players, 17, yourTurn);
        System.out.print("\n| Conservatory    |");
        printMarks(players, 18, yourTurn);
        System.out.print("\n| Billiard Room   |");
        printMarks(players, 19, yourTurn);
        System.out.print("\n| Library         |");
        printMarks(players, 20, yourTurn);
        System.out.print("\n| Study           |");
        printMarks(players, 21, yourTurn);

        System.out.print("\n-------------------");
        for (int i = 1; i <= players.length; i++) {
            System.out.print("----");
        }
        System.out.println("\n");
    }

    public static void printMarks(int[][] players, int num, int yourTurn) {
        System.out.print(" ");
        switch (players[yourTurn - 1][num]) {
            case 1 ->
                System.out.print("X");
            case 2 ->
                System.out.print("?");
            case 3 ->
                System.out.print("O");
            default ->
                System.out.print(" ");
        }
        System.out.print(" |");

        for (int i = 1; i <= players.length; i++) {
            if (i != yourTurn) {
                System.out.print(" ");
                switch (players[i - 1][num]) {
                    case 1 ->
                        System.out.print("X");
                    case 2 ->
                        System.out.print("?");
                    case 3 ->
                        System.out.print("O");
                    default ->
                        System.out.print(" ");
                }
                System.out.print(" |");
            }
        }
    }
}
