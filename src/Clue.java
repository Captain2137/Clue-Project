// @author Michael Q

import java.util.ArrayList;
import java.util.Scanner;

public class Clue {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int menu;

        do {
            System.out.println("1: Start New Game");
            System.out.println("0: Exit");

            menu = scan.nextInt();
            while (menu < 0 || menu > 1) {
                System.out.println("Enter 0 - 1");

                menu = scan.nextInt();
            }
            System.out.println();

            switch (menu) {
                case 1:
                    start();
                    break;
                default:
                    System.out.println("Exiting");
                    break;
            }
        } while (menu != 0);
    }

    public static void start() {
        Scanner scan = new Scanner(System.in);

        int menu;
        int turn = 1;
        int count;
        boolean end = true;
        ArrayList<ArrayList<ArrayList<Integer>>> history = new ArrayList<>(); //player, turn, guess
        int[][] players; // player, cards (first num is num of cards)
        int[] guess;
        int[] found = new int[3];
        String temp;

        String[] cards = {"???????", "Colonel Mustard", "Professor Plum", "Mr. Green",
            "Mrs. Peacock", "Miss Scarlet", "Mrs. White", "Knife", "Candlestick",
            "Revolver", "Rope", "Lead Pipe", "Wrench", "Hall", "Lounge", "Dining Room",
            "Kitchen", "Ballroom", "Conservatory", "Billiard Room", "Library", "Study"};

        System.out.print("Total number of players: ");
        players = new int[scan.nextInt()][22];

        for (int[] player : players) {
            history.add(new ArrayList<>());
        }

        for (int i = 1; i <= players.length; i++) {
            System.out.print("Number of cards player " + i + " has: ");
            players[i - 1][0] = scan.nextInt();
        }
        System.out.println();

        System.out.println("What cards do you have");
        System.out.println("Enter one at a time and then enter 'Done'");

        scan.nextLine();
        temp = scan.nextLine();
        while (!temp.equalsIgnoreCase("done")) {
            if (temp.equalsIgnoreCase("Colonel Mustard")) {
                for (int[] player : players) {
                    player[1] = 1;
                }
            } else if (temp.equalsIgnoreCase("Professor Plum")) {
                for (int[] player : players) {
                    player[2] = 1;
                }
            } else if (temp.equalsIgnoreCase("Mr. Green")) {
                for (int[] player : players) {
                    player[3] = 1;
                }
            } else if (temp.equalsIgnoreCase("Mrs. Peacock")) {
                for (int[] player : players) {
                    player[4] = 1;
                }
            } else if (temp.equalsIgnoreCase("Miss Scarlet")) {
                for (int[] player : players) {
                    player[5] = 1;
                }
            } else if (temp.equalsIgnoreCase("Mrs. White")) {
                for (int[] player : players) {
                    player[6] = 1;
                }
            } else if (temp.equalsIgnoreCase("Knife")) {
                for (int[] player : players) {
                    player[7] = 1;
                }
            } else if (temp.equalsIgnoreCase("Candlestick")) {
                for (int[] player : players) {
                    player[8] = 1;
                }
            } else if (temp.equalsIgnoreCase("Revolver")) {
                for (int[] player : players) {
                    player[9] = 1;
                }
            } else if (temp.equalsIgnoreCase("Rope")) {
                for (int[] player : players) {
                    player[10] = 1;
                }
            } else if (temp.equalsIgnoreCase("Lead Pipe")) {
                for (int[] player : players) {
                    player[11] = 1;
                }
            } else if (temp.equalsIgnoreCase("Wrench")) {
                for (int[] player : players) {
                    player[12] = 1;
                }
            } else if (temp.equalsIgnoreCase("Hall")) {
                for (int[] player : players) {
                    player[13] = 1;
                }
            } else if (temp.equalsIgnoreCase("Lounge")) {
                for (int[] player : players) {
                    player[14] = 1;
                }
            } else if (temp.equalsIgnoreCase("Dining Room")) {
                for (int[] player : players) {
                    player[15] = 1;
                }
            } else if (temp.equalsIgnoreCase("Kitchen")) {
                for (int[] player : players) {
                    player[16] = 1;
                }
            } else if (temp.equalsIgnoreCase("Ballroom")) {
                for (int[] player : players) {
                    player[17] = 1;
                }
            } else if (temp.equalsIgnoreCase("Conservatory")) {
                for (int[] player : players) {
                    player[18] = 1;
                }
            } else if (temp.equalsIgnoreCase("Billiard Room")) {
                for (int[] player : players) {
                    player[19] = 1;
                }
            } else if (temp.equalsIgnoreCase("Library")) {
                for (int[] player : players) {
                    player[20] = 1;
                }
            } else if (temp.equalsIgnoreCase("Study")) {
                for (int[] player : players) {
                    player[21] = 1;
                }
            } else {
                System.out.println("'" + temp + "' Does not match any");
            }

            temp = scan.nextLine();
        }
        System.out.println();

        do {
            System.out.println("Turn " + turn);
            check(players, history);

            for (int i = 1; i <= players.length; i++) {
                if (end == false) {
                    break;
                }

                System.out.println("Player " + i + "'s turn");
                System.out.println("1: Guess");
                System.out.println("2: Skip");
                System.out.println("3: Print Card");
                System.out.println("4: History");

                count = 0;
                System.out.print("5: Show Answer ");
                for (int j = 0; j < found.length; j++) {
                    if (found[j] != 0) {
                        count++;
                    }
                }
                System.out.println("(" + count + "/" + found.length + ")");

                System.out.println("0: Game End");

                menu = scan.nextInt();
                while (menu < 0 || menu > 5) {
                    System.out.println("Enter 0 - 5");

                    menu = scan.nextInt();
                }
                System.out.println();

                switch (menu) {
                    case 1:
                        guess = new int[3];

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
                        System.out.println();

                        System.out.println("weapon: ");
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
                        System.out.println();

                        System.out.println("Room: ");
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
                        System.out.println();

                        count = 0;
                        for (int j = 1; j < players.length; j++) {
                            int num = (i + j > players.length ? i + j - players.length : i + j);

                            System.out.print("Does Player " + num + " show (y/n): ");
                            temp = scan.next();

                            if (temp.equalsIgnoreCase("y") || temp.equalsIgnoreCase("yes")) {
                                if (i == 1) {
                                    System.out.println("What card were you shown?: ");
                                    System.out.println(" 1: Colonel Mustard     13: Hall");
                                    System.out.println(" 2: Professor Plum      14: Lounge");
                                    System.out.println(" 3: Mr. Green           15: Dining Room");
                                    System.out.println(" 4: Mrs. Peacock        16: Kitchen");
                                    System.out.println(" 5: Miss Scarlet        17: Ballroom");
                                    System.out.println(" 6: Mrs. White          18: Conservatory");
                                    System.out.println(" 7: Knife               19: Billiard Room");
                                    System.out.println(" 8: Candlestick         20: Library");
                                    System.out.println(" 9: Revolver            21: Study");
                                    System.out.println("10: Rope");
                                    System.out.println("11: Lead Pipe");
                                    System.out.println("12: Wrench");

                                    menu = scan.nextInt();
                                    while (menu < 1 || menu > 21) {
                                        System.out.println("Enter 1 - 21");

                                        menu = scan.nextInt();
                                    }
                                    players[num - 1][menu] = 3;
                                    System.out.println();
                                } else {
                                    if (num != 1) {
                                        history.get(num - 1).add(new ArrayList<>());
                                        for (int k = 0; k < guess.length; k++) {
                                            if (players[num - 1][guess[k]] != 1 && players[num - 1][guess[k]] != 3 && (num - 1) != 0) {
                                                players[num - 1][guess[k]] = 2;
                                            }
                                            history.get(num - 1).get(history.get(num - 1).size() - 1).add(guess[k]);
                                        }
                                    }
                                }

                                System.out.println();
                                break;
                            } else {
                                count++;

                                for (int k = 0; k < guess.length; k++) {
                                    if (num != 1) {
                                        players[num - 1][guess[k]] = 1;
                                    }
                                }

                                if (i == 1 && count == (players.length - 1)) {
                                    for (int k = 0; k < guess.length; k++) {
                                        if (players[0][guess[k]] != 1 && players[num - 1][guess[k]] != 3) {
                                            players[0][guess[k]] = 3;
                                            if (guess[k] <= 6) {
                                                found[0] = guess[k];
                                            } else if (guess[k] > 6 && guess[k] <= 12) {
                                                found[1] = guess[k];
                                            } else if (guess[k] > 12) {
                                                found[2] = guess[k];
                                            }
                                        }
                                    }
                                }

                                System.out.println();
                            }
                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        print(players);
                        i--;
                        break;
                    case 4:
                        printHistory(history);
                        i--;
                        break;
                    case 5:
                        System.out.println("It was " + cards[found[0]] + " with the " + cards[found[1]] + " in the " + cards[found[2]]);
                        System.out.println();
                        i--;
                        break;
                    default:
                        end = false;
                        break;
                }
            }

            turn++;
        } while (end);
    }

    public static void check(int[][] players, ArrayList<ArrayList<ArrayList<Integer>>> history) {
        int runs = 0;
        int count;
        int[] changes;

        do {
            runs++;
            changes = new int[12];

            //turn passes
            for (int i = 0; i < history.size(); i++) {
                for (int j = 0; j < history.get(i).size(); j++) {
                    count = 0;
                    for (int k = 0; k < history.get(i).get(j).size(); k++) {
                        if (players[i][history.get(i).get(j).get(k)] == 3) {
                            count = 0;
                            break;
                        } else if (players[i][history.get(i).get(j).get(k)] != 1) {
                            count++;
                        }
                    }

                    if (count == 1) {
                        for (int k = 0; k < history.get(i).get(j).size(); k++) {
                            if (players[i][history.get(i).get(j).get(k)] != 1) {
                                players[i][history.get(i).get(j).get(k)] = 3;
                                changes[1]++;
                            }
                        }
                    } else if (count == 0) {
                        history.get(i).remove(j);
                        changes[2]++;
                    }
                }
            }

            //only left horizontal
            for (int i = 1; i < players[0].length; i++) {
                count = 0;
                for (int j = 0; j < players.length; j++) {
                    if (players[j][i] == 3) {
                        count = -1;
                        break;
                    } else if (players[j][i] != 1) {
                        count++;
                    }
                }

                if (count == -1) {
                    for (int j = 0; j < players.length; j++) {
                        if (players[j][i] != 3 && players[j][i] != 1) {
                            players[j][i] = 1;
                            changes[3]++;
                        }
                    }
                } else if (count == 1) {
                    for (int j = 0; j < players.length; j++) {
                        if (players[j][i] != 1 && players[j][i] != 3 && j != 0) {
                            players[j][i] = 3;
                            changes[4]++;
                        }
                    }
                }
            }

            //only left vertical player 1 (1 - 6)
            count = 0;
            for (int i = 1; i < 7; i++) {
                if (players[0][i] == 3) {
                    count = -1;
                    break;
                } else if (players[0][i] != 1) {
                    count++;
                }
            }

            if (count == -1) {
                for (int i = 1; i < 7; i++) {
                    if (players[0][i] != 3 && players[0][i] != 1) {
                        players[0][i] = 1;
                        changes[5]++;
                    }
                }
            } else if (count == 1) {
                for (int i = 1; i < 7; i++) {
                    if (players[0][i] != 1 && players[0][i] != 3) {
                        players[0][i] = 3;
                        changes[6]++;
                    }
                }
            }

            //only left vertical player 1 (7 - 12)
            count = 0;
            for (int i = 7; i < 13; i++) {
                if (players[0][i] == 3) {
                    count = -1;
                    break;
                } else if (players[0][i] != 1) {
                    count++;
                }
            }

            if (count == -1) {
                for (int i = 7; i < 13; i++) {
                    if (players[0][i] != 3 && players[0][i] != 1) {
                        players[0][i] = 1;
                        changes[7]++;
                    }
                }
            } else if (count == 1) {
                for (int i = 7; i < 13; i++) {
                    if (players[0][i] != 1 && players[0][i] != 3) {
                        players[0][i] = 3;
                        changes[8]++;
                    }
                }
            }

            //only left vertical player 1 (13 - 21)
            count = 0;
            for (int i = 13; i < 22; i++) {
                if (players[0][i] == 3) {
                    count = -1;
                    break;
                } else if (players[0][i] != 1) {
                    count++;
                }
            }

            if (count == -1) {
                for (int i = 13; i < 22; i++) {
                    if (players[0][i] != 3 && players[0][i] != 1) {
                        players[0][i] = 1;
                        changes[9]++;
                    }
                }
            } else if (count == 1) {
                for (int i = 13; i < 22; i++) {
                    if (players[0][i] != 1 && players[0][i] != 3) {
                        players[0][i] = 3;
                        changes[10]++;
                    }
                }
            }

            //only left vertical other players
            for (int i = 1; i < players.length; i++) {
                count = players[i][0];
                for (int j = 1; j < players[i].length; j++) {
                    if (players[i][j] == 3) {
                        count--;
                    }
                }

                if (count == 0) {
                    for (int j = 1; j < players[i].length; j++) {
                        if (players[i][j] != 3 && players[i][j] != 1) {
                            players[i][j] = 1;
                            changes[11]++;
                        }
                    }
                }
            }

            for (int i = 1; i < changes.length; i++) {
                changes[0] += changes[i];
            }
            //System.out.println("Changes: " + changes[0] + "  " + Arrays.toString(changes));
        } while (changes[0] != 0);
        System.out.println();
    }

    public static void print(int[][] players) {
        System.out.print("-------------------");
        for (int i = 1; i <= players.length; i++) {
            System.out.print("----");
        }
        System.out.print("\n| Players         |");
        for (int i = 1; i <= players.length; i++) {
            System.out.print(" " + i + " |");
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
        mark(players, 1);
        System.out.print("\n| Professor Plum  |");
        mark(players, 2);
        System.out.print("\n| Mr. Green       |");
        mark(players, 3);
        System.out.print("\n| Mrs. Peacock    |");
        mark(players, 4);
        System.out.print("\n| Miss Scarlet    |");
        mark(players, 5);
        System.out.print("\n| Mrs. White      |");
        mark(players, 6);
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
        mark(players, 7);
        System.out.print("\n| Candlestick     |");
        mark(players, 8);
        System.out.print("\n| Revolver        |");
        mark(players, 9);
        System.out.print("\n| Rope            |");
        mark(players, 10);
        System.out.print("\n| Lead Pipe       |");
        mark(players, 11);
        System.out.print("\n| Wrench          |");
        mark(players, 12);
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
        mark(players, 13);
        System.out.print("\n| Lounge          |");
        mark(players, 14);
        System.out.print("\n| Dining Room     |");
        mark(players, 15);
        System.out.print("\n| Kitchen         |");
        mark(players, 16);
        System.out.print("\n| Ballroom        |");
        mark(players, 17);
        System.out.print("\n| Conservatory    |");
        mark(players, 18);
        System.out.print("\n| Billiard Room   |");
        mark(players, 19);
        System.out.print("\n| Library         |");
        mark(players, 20);
        System.out.print("\n| Study           |");
        mark(players, 21);
        System.out.print("\n-------------------");
        for (int i = 1; i <= players.length; i++) {
            System.out.print("----");
        }
        System.out.println();
        System.out.println();
    }

    public static void printHistory(ArrayList<ArrayList<ArrayList<Integer>>> history) {
        for (int i = 0; i < history.size(); i++) {
            System.out.println("Player " + (i + 1) + " Guesses");
            for (int j = 0; j < history.get(i).size(); j++) {
                System.out.println(history.get(i).get(j));
            }
            System.out.println();
        }
    }

    public static void mark(int[][] players, int num) {
        for (int i = 1; i <= players.length; i++) {
            System.out.print(" ");
            switch (players[i - 1][num]) {
                case 1:
                    System.out.print("X");
                    break;
                case 2:
                    System.out.print("?");
                    break;
                case 3:
                    System.out.print("O");
                    break;
                default:
                    System.out.print(" ");
                    break;
            }
            System.out.print(" |");
        }
    }
}
