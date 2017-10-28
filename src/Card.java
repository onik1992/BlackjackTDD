/**
 * Created by King Lo on 3/15/2017.
 */
public class Card {

    public Card(){}

    //contains the letter of the card
    private String cardString(int cardNum) {
        String numStr = "Unknown Card";
        switch (cardNum) {
            case 1:
                numStr = "A";
                break;
            case 2:
                numStr = "2";
                break;
            case 3:
                numStr = "3";
                break;
            case 4:
                numStr = "4";
                break;
            case 5:
                numStr = "5";
                break;
            case 6:
                numStr = "6";
                break;
            case 7:
                numStr = "7";
                break;
            case 8:
                numStr = "8";
                break;
            case 9:
                numStr = "9";
                break;
            case 10:
                numStr = "10";
                break;
            case 11:
                numStr = "J";
                break;
            case 12:
                numStr = "Q";
                break;
            case 13:
                numStr = "K";
                break;
        }
        return numStr;
    }

    //contains the value of the cards
    private int cardNumValue(String numString) {
        int cardValue = 0;
        switch (numString) {
            case "A": //Ace
                cardValue = 1;
                break;
            case "2":
                cardValue = 2;
                break;
            case "3":
                cardValue = 3;
                break;
            case "4":
                cardValue = 4;
                break;
            case "5":
                cardValue = 5;
                break;
            case "6":
                cardValue = 6;
                break;
            case "7":
                cardValue = 7;
                break;
            case "8":
                cardValue = 8;
                break;
            case "9":
                cardValue = 9;
                break;
            case "10":
            case "J": //Jack
            case "Q": //Queen
            case "K": //King
                cardValue = 10;
                break;
        }
        return cardValue;
    }

    //contains the suit of the card
    private String suitString(int suitNum) {
        //"♥", "♠", "♣", "♦"
        String numSuit = "";
        switch (suitNum) {
            case 1:
                numSuit = "♦";
                break;
            case 2:
                numSuit = "♣";
                break;
            case 3:
                numSuit = "♥";
                break;
            case 4:
                numSuit = "♠";
                break;
        }
        return numSuit;
    }


    public String getCardString(int cardNum){
        return cardString(cardNum);
    }

    public String getSuitString(int suitNum){
        return suitString(suitNum);
    }

    public int getCardNumValue(String numString) {
        return cardNumValue(numString);
    }

}
