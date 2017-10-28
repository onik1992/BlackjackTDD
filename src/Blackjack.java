import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by King Lo on 3/15/2017.
 */
public class Blackjack {

    private Deck deck;
    private Scanner keyboard;
    private int bet, playerChips, dealerChips, dealCards;
    private ArrayList<String> dealerHand, playerHand;
    private boolean roundStart, playerBust, doubleBet;
    private Random rInt = new Random();

    public Blackjack(){
        deck = new Deck();
    }

    //calculates hand value and ace values
    private int handValue(ArrayList<String> hand) {

        int value = 0;
        int cardValue;
        boolean hasAce = false;
        int cardInHand = hand.size();

        for (int i = 0; i < cardInHand; i++) {

            String aCard = hand.get(i);
            cardValue = deck.getHandValue(aCard);
            if (cardValue == 1) {
                cardValue = 1;
                hasAce = true;
            }

            value += cardValue;
        }

        if (hasAce && value + 10 <= 21) {
            value += 10;
        }
        return value;

    }

    /*Shuffles the deck when a certain number of cards have been deal
     * and when the round ends or the next bet.
     */
    private String dealingCards() {
        if (dealCards > rInt.nextInt(5) + 26
                && !roundStart) {
            deck.setGameDeck(deck.getShuffledDeck());
            System.out.println();
            System.out.println((char) 27 + "[35mDealer shuffles the deck." + (char) 27 + "[0m");
            dealCards = 0;
        }
        roundStart = true;
        String dealCard = deck.getDealCards(dealCards);
        dealCards++;
        return dealCard;
    }

    //checks for blackjack
    private boolean blackjack(ArrayList<String> playerHand,
                              ArrayList<String> dealerHand) {
        boolean playerWin = false;
        if (handValue(playerHand) == 21) {
            deck.getHandCards(playerHand);
            System.out.print("You got Blackjack. ");
            if (handValue(dealerHand) == 21) {
                System.out.println();
                deck.getHandCards(dealerHand);
                System.out.println("Dealer also has Blackjack.");
                System.out.println();
                System.out.println("Dealer wins on tie.");
                playerWin = false;
            } else {
                System.out.println("You win.");
                playerWin = true;
            }
        }
        return playerWin;
    }

    //This only happens on card value over 21
    private boolean busted(ArrayList<String> playerHand,
                           ArrayList<String> dealerHand) {
        boolean playerWin = false;
        if (handValue(playerHand) > 21) {
            System.out.println();
            System.out.println("Your hand value: " + handValue(playerHand));
            System.out.println("You busted by going over 21. \nDealer Wins");
            System.out.print("Dealer's hidden card:");
            deck.getDealerClosedCard(dealerHand);
            playerBust = true;
            playerWin = false;
        }
        if (handValue(dealerHand) > 21) {
            System.out.println("Dealer busted by going over 21. \nYou win.");
            playerWin = true;
        }
        return playerWin;
    }

    //Checks and declare the winner.
    private boolean isPlayerWinning(ArrayList<String> playerHand,
                                    ArrayList<String> dealerHand) {
        boolean playerWin;
        if (handValue(dealerHand) > 21) {
            playerWin = busted(playerHand, dealerHand);
        } else if (handValue(dealerHand) == handValue(playerHand)) {
            System.out.println();
            System.out.println("Dealer wins on a tie.");

            playerWin = false;
        } else {
            if (handValue(dealerHand) > handValue(playerHand)) {
                System.out.println("Dealer wins, " + handValue(dealerHand) + " points to " + handValue(playerHand) + ".");
                playerWin = false;
            } else {
                System.out.println("You win, " + handValue(playerHand) + " points to " + handValue(dealerHand) + ".");
                playerWin = true;
            }
        }
        return playerWin;
    }

    //this contains the actions that the player takes in
    // Blackjack such as bet or stand
    private void playerActions(ArrayList<String> playerHand,
                               ArrayList<String> dealerHand) {
        boolean checkInput;
        playerBust = false;
        doubleBet = false;

        while (!playerBust) {
            // Display player's cards
            System.out.println();
            System.out.println("Your cards are: ");
            deck.getHandCards(playerHand);
            System.out.println("Your hand value: " + handValue(playerHand));
            System.out.println();
            // The player will automatically stand if hand value is equal to 21
            if (handValue(playerHand) == 21) {
                System.out.println("You stand.");
                break;
            }
            //shows the dealer second card. first card is unknown
            System.out.print("Dealer opened card:");
            deck.getDealerOpenedCard(dealerHand);
            System.out.println();
            System.out.println("Will you: ");
            //check if the player has 2 cards, if player has 2 card then
            // he will able to double his bet
            if(playerHand.size() < 3 && bet <= (playerChips/2)) {
                System.out.print((char) 27 + "[34mHit (H), Stand (S), or Double (D)? " + (char) 27 + "[0m");
            }
            else{
                System.out.print((char) 27 + "[34mHit (H) or Stand (S)? " + (char) 27 + "[0m");
            }

            char playerAction;  // player's response, 'H' (checkInput) or 'S' (stand).
            do {
                playerAction = Character.toUpperCase(keyboard.next().charAt(0));
                checkInput = false;
                if(playerHand.size() < 3 && bet <= (playerChips/2)) {
                    if (playerAction != 'H' && playerAction != 'S' && playerAction != 'D') {
                        System.out.print((char)27 + "[31mPlease respond H, S, or D:" + (char)27 + "[0m");
                        checkInput = true;
                    }
                }
                else{
                    if (playerAction != 'H' && playerAction != 'S') {
                        System.out.print((char)27 + "[31mPlease respond H or S:" + (char)27 + "[0m");
                        checkInput = true;
                    }
                }

            } while (checkInput);

            // Exit this method if the player stand.
            if (playerAction == 'S') {
                break;
            }
            // when the player doubles a card the bet will increase
            // by 2 times and ends the player turn
            if (playerHand.size() < 3 && playerAction == 'D'){
                doubleBet = true;
                hitCard(playerHand);
                System.out.println("Your hand value: " + handValue(playerHand));
                busted(playerHand, dealerHand);
                break;
            }
            // Give the player a card. and checks for bust
            if (playerAction == 'H') {
                hitCard(playerHand);
                busted(playerHand, dealerHand);
            }
        }
    }

    //helper method for the playerAction when hitting a card
    private void hitCard(ArrayList<String> playerHand){
        String newCard = dealingCards();
        playerHand.add(newCard);
        System.out.println();
        System.out.print("You hit: ");
        deck.getDrawCards(newCard);
    }

    //this contains the dealer action in Blackjack
    private boolean dealerActions(ArrayList<String> playerHand,
                                  ArrayList<String> dealerHand) {
        boolean playerWin;
        while (handValue(dealerHand) <= 16) {
            String newCard = dealingCards();
            dealerHand.add(newCard);
            System.out.print("\nDealer hits:");
            deck.getDrawCards(newCard);
        }
        System.out.println();
        System.out.println("Dealer's hand value: " + handValue(dealerHand));
        //dealer decides who wins
        playerWin = isPlayerWinning(playerHand, dealerHand);
        return playerWin;
    }

    //deals the inital cards and runs the rules of the game
    private boolean run() {
        dealerHand = new ArrayList<String>();
        playerHand = new ArrayList<String>();

        dealerHand.add(dealingCards());
        playerHand.add(dealingCards());
        dealerHand.add(dealingCards());
        playerHand.add(dealingCards());

        boolean playerWin;
        keyboard = new Scanner(System.in);

        if (handValue(playerHand) == 21) {
            playerWin = blackjack(playerHand, dealerHand);
            //if player wins with Blackjack multiply the bet by 1.5
            if(playerWin){
                bet *= 1.5;
            }
            roundStart = false;
            return playerWin;
        }

        playerActions(playerHand,dealerHand);
        if (playerBust){
            playerWin = false;
            roundStart = false;
            return playerWin;
        }

        System.out.println();
        System.out.println("Dealer's cards are:");
        deck.getHandCards(dealerHand);

        playerWin = dealerActions(playerHand,dealerHand);
        roundStart = false;
        return playerWin;
    }

    //plays the game
    private void game() {
        char replay;
        boolean playerWin;
        boolean end = false;
        boolean again = true;
        keyboard = new Scanner(System.in);

        System.out.println("Welcome to the game of blackjack.");
        System.out.println();

        dealerChips = 10000;
        playerChips = 1000;
        bet = 0;
        while(again) {
            if (playerChips == 0) {
                playerChips = 1000;
                dealerChips = 10000;
            }
            if (dealerChips == 0) {
                System.out.println("The dealer calls the office for more chips.");
                dealerChips = playerChips * 10;
            }
            if (!end) {
                System.out.println("You start with $" + playerChips + ".");
                System.out.println("Dealer start with $" + dealerChips + ".");
            }
            while (!end) {
                do {
                    System.out.println((char) 27 + "[1mPlace your bet. (Enter 0 to end.)"
                                     + (char) 27 + "[0m");
                    System.out.print((char)27 + "[32m$" + (char)27 + "[0m" );
                    try {
                        bet = keyboard.nextInt();
                        if (0 > bet || bet > playerChips) {
                            System.out.println("Your answer must be between $0 and $"
                                             + playerChips + ".");
                            break;
                        }
                        if (bet == 0) {
                            System.out.println("You stop playing. You left with $"
                                             + playerChips + ".");
                            end = true;
                            break;
                        }
                        playerWin = run();
                        if (doubleBet){
                            bet *= 2;
                        }

                        if (playerWin) {
                            playerChips += bet;
                            dealerChips -= bet;
                        }
                        else {
                            playerChips -= bet;
                            dealerChips += bet;
                        }
                        System.out.println();
                        if (playerChips == 0) {
                            System.out.println((char) 27 + "[31mGameover!");
                            System.out.println("You ran out of chips!" + (char) 27 + "[0m");
                            end = true;
                            break;
                        }
                        if (dealerChips == 0) {
                            System.out.println("Congratulation! You took all of the dealer chips."
                                             + "\nYou left the with $" + playerChips);
                            break;
                        }

                        System.out.println("Your new currency $" + playerChips + ".");
                        System.out.println("Dealer's new currency $" + dealerChips + ".");
                    }
                    catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter an integer.");
                        keyboard.nextLine();
                    }
                } while ((0 > bet || bet > playerChips));
            }
            System.out.println("Would you like to play again?");
            System.out.println("Yes (Y) or No (N).");
            replay = Character.toUpperCase(keyboard.next().charAt(0));
            if (replay == 'Y') {
                again = true;
                end = false;
            } else if (replay == 'N') {
                again = false;
            } else {
                System.out.print("Please respond Y or N:  ");
            }
        }
    }

    public void startGame(){
        game();
    }

    public int getHandValue(ArrayList<String> hand) {
        return handValue(hand);
    }

    public boolean isBlackjack(ArrayList<String> playerHand,
                               ArrayList<String> dealerHand) {
        return blackjack(playerHand, dealerHand);
    }

    public boolean isPlayerBust(ArrayList<String> playerHand,
                                ArrayList<String> dealerHand) {
        return busted(playerHand, dealerHand);
    }

    public boolean isWinner(ArrayList<String> playerHand,
                            ArrayList<String> dealerHand) {
        return isPlayerWinning(playerHand, dealerHand);
    }
}
