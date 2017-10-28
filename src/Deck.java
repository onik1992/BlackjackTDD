import java.util.ArrayList;
import java.util.Random;

/**
 * Created by King Lo on 3/15/2017.
 */
public class Deck {

    private Card card;
    private Random random;
    private ArrayList<String> gameDeck;


    public Deck(){
        card = new Card();
        random = new Random();
        gameDeck = shuffle();
    }

    //stores a fresh deck
    private ArrayList<String> deck() {
        String aSuit = "";
        String aCard = "";
        ArrayList<String> deck = new ArrayList<String>();

        //for each suit
        for (int s = 1; s <= 4; s++) {
            aSuit = card.getSuitString(s);

            //for each card
            for (int c = 1; c <= 13; c++) {
                aCard = card.getCardString(c);
                deck.add(aCard + " " + aSuit);
            }
        }
        return deck;
    }

    //randomize a deck
    private ArrayList<String> shuffle() {
        ArrayList<String> deck = deck();
        ArrayList<String> shuffledDeck = new ArrayList<String>();
        int deckSize = deck.size();
        for (int i = 0; i < deckSize; i++) {
            int index = random.nextInt(deck.size());
            shuffledDeck.add(deck.get(index));
            deck.remove(index);
        }
        return shuffledDeck;
    }

    //return the value of the card
    private int handValue(String aCard){
        int cardValue = card.getCardNumValue(aCard.substring(0, aCard.length() - 2));
        return cardValue;
    }

    //deals the card to the dealer and the player.
    private String dealingCards(int dealCards) {

        return gameDeck.get(dealCards);
    }

    private void handCards(ArrayList<String> hand){
        for (int i = 0; i < hand.size(); i++) {
            String handString = hand.get(i);
            //get the suit in the string
            String colorSuit = handString.substring(handString.length()-1, handString.length());
            //Checks for the suit and change the color using ANSI ESC code
            if(colorSuit.equalsIgnoreCase("♦") || colorSuit.equalsIgnoreCase("♥") ) {
                System.out.print((char) 27 + "[31m[" + handString + "]" + (char) 27 + "[0m   ");
            }
            else{
                System.out.print("[" + handString + "]   ");
            }
        }
        System.out.println();
    }

    private void dealerOpenedCard(ArrayList<String> hand){
        String handString = hand.get(1);
        //get the suit in the string
        String colorSuit = handString.substring(handString.length()-1, handString.length());
        //Checks for the suit and change the color using ANSI ESC code
        if(colorSuit.equalsIgnoreCase("♦") || colorSuit.equalsIgnoreCase("♥") ) {
            System.out.print((char) 27 + "[31m[" + handString + "]" + (char) 27 + "[0m   ");
        }
        else{
            System.out.print("[" + handString + "]   ");

        }
        System.out.println();
    }

    private void dealerClosedCard(ArrayList<String> hand){
        String handString = hand.get(0);
        //get the suit in the string
        String colorSuit = handString.substring(handString.length()-1, handString.length());
        //Checks for the suit and change the color using ANSI ESC code
        if(colorSuit.equalsIgnoreCase("♦") || colorSuit.equalsIgnoreCase("♥") ) {
            System.out.print((char) 27 + "[31m[" + handString + "]" + (char) 27 + "[0m   ");
        }
        else{
            System.out.print("[" + handString + "]   ");
        }
        System.out.println();
    }

    private void drawsCard(String aCard){
        //get the suit in the string
        String colorSuit = aCard.substring(aCard.length()-1, aCard.length());
        //Checks for the suit and change the color using ANSI ESC code
        if(colorSuit.equalsIgnoreCase("♦") || colorSuit.equalsIgnoreCase("♥") ) {
            System.out.print((char) 27 + "[31m[" + aCard + "]" + (char) 27 + "[0m   ");
        }
        else{
            System.out.print("[" + aCard + "]   ");
        }
    }

    public ArrayList<String> getFreshDeck(){
        return deck();
    }

    public ArrayList<String> getShuffledDeck(){

        return shuffle();
    }

    public int getHandValue(String aCard){
        return handValue(aCard);
    }

    public String getDealCards(int dealCards){
        return dealingCards(dealCards);
    }

    public void getHandCards(ArrayList<String> hand) {
        handCards(hand);
    }

    public void getDealerOpenedCard(ArrayList<String> hand){
        dealerOpenedCard(hand);
    }

    public void getDealerClosedCard(ArrayList<String> hand){
        dealerClosedCard(hand);
    }


    public void getDrawCards(String aCard){
        drawsCard(aCard);
    }

    //set a new shuffled deck
    public void setGameDeck(ArrayList<String> shuffledDeck) {
        this.gameDeck = shuffledDeck;
    }
}
