import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by King Lo on 3/15/2017.
 */
public class BlackjackTest {

    private Blackjack blackjack;
    private Deck deck;
    @Before
    public void setUP(){
        blackjack = new Blackjack();
        deck = new Deck();
    }

    //ArrayList starts at from 0 - 51, using i to subtract 1 (1 - 52)
    public String addCard(int i){
        String aCard = deck.getFreshDeck().get(i - 1);
        return aCard;
    }

    @Test
    public void handValueTest(){
        ArrayList<String> handNoAce = new ArrayList<String>();
        handNoAce.add(addCard(9)); // 9
        handNoAce.add(addCard(24)); // J

        ArrayList<String> handWithAce = new ArrayList<String>();
        handWithAce.add(addCard(1)); // A
        handWithAce.add(addCard(29)); // 3

        ArrayList<String> handWithMultipleAce = new ArrayList<String>();
        handWithMultipleAce.add(addCard(1)); // A
        handWithMultipleAce.add(addCard(14));// A
        handWithMultipleAce.add(addCard(8)); // 8
        handWithMultipleAce.add(addCard(40));// A

        Assert.assertEquals(19,blackjack.getHandValue(handNoAce));
        Assert.assertEquals(14,blackjack.getHandValue(handWithAce));
        Assert.assertEquals(21,blackjack.getHandValue(handWithMultipleAce));

    }

    //Player wins with Blackjack
    @Test
    public void blackjackTest1() {
        ArrayList<String> playerHand = new ArrayList<>();
        ArrayList<String> dealerHand = new ArrayList<>();
        playerHand.add(addCard(52)); // K
        playerHand.add(addCard(14));  // A

        dealerHand.add(addCard(1)); // A
        dealerHand.add(addCard(9));  // 9

        boolean playerWin = blackjack.isBlackjack(playerHand,dealerHand);

        Assert.assertEquals(true, playerWin);
    }

    /* Dealer wins with Blackjack
     * dont need to print anything for the dealer
     * when dealer as Blackjack since dealer will be the
     * last person to look at his cards*/
    @Test
    public void blackjackTest2() {
        ArrayList<String> playerHand = new ArrayList<>();
        ArrayList<String> dealerHand = new ArrayList<>();
        playerHand.add(addCard(8));
        playerHand.add(addCard(1));

        dealerHand.add(addCard(1));
        dealerHand.add(addCard(24));

        boolean playerWin = blackjack.isBlackjack(playerHand,dealerHand);

        Assert.assertEquals(false, playerWin);
    }

    //Blackjack ties
    @Test
    public void blackjackTest3() {
        ArrayList<String> playerHand = new ArrayList<>();
        ArrayList<String> dealerHand = new ArrayList<>();
        playerHand.add(addCard(52));
        playerHand.add(addCard(27));

        dealerHand.add(addCard(14));
        dealerHand.add(addCard(11));

        boolean playerWin = blackjack.isBlackjack(playerHand,dealerHand);

        Assert.assertEquals(false, playerWin);
    }

    //Player bust
    @Test
    public void bustedTest1() {
        ArrayList<String> playerHand = new ArrayList<>();
        ArrayList<String> dealerHand = new ArrayList<>();
        playerHand.add(addCard(13));
        playerHand.add(addCard(3));
        playerHand.add(addCard(9));

        dealerHand.add(addCard(16));
        dealerHand.add(addCard(8));


        boolean playerWin = blackjack.isPlayerBust(playerHand,dealerHand);
        Assert.assertEquals(false, playerWin);
    }

    //Dealer bust, player win
    @Test
    public void bustedTest2() {
        ArrayList<String> playerHand = new ArrayList<>();
        ArrayList<String> dealerHand = new ArrayList<>();
        playerHand.add(addCard(8));
        playerHand.add(addCard(10));

        dealerHand.add(addCard(5));
        dealerHand.add(addCard(9));
        dealerHand.add(addCard(22));


        boolean playerWin = blackjack.isPlayerBust(playerHand,dealerHand);
        Assert.assertEquals(true, playerWin);
    }

    //Player wins with highest value
    @Test
    public void highestValueTest1() {
        ArrayList<String> playerHand = new ArrayList<>();
        ArrayList<String> dealerHand = new ArrayList<>();
        playerHand.add(addCard(13));
        playerHand.add(addCard(9));

        dealerHand.add(addCard(3));
        dealerHand.add(addCard(1));
        dealerHand.add(addCard(16));

        boolean playerWin = blackjack.isWinner(playerHand,dealerHand);
        Assert.assertEquals(true, playerWin);
    }

    //dealer wins with highest value
    @Test
    public void highestValueTest2() {
        ArrayList<String> playerHand = new ArrayList<>();
        ArrayList<String> dealerHand = new ArrayList<>();
        playerHand.add(addCard(13));
        playerHand.add(addCard(9));

        dealerHand.add(addCard(3));
        dealerHand.add(addCard(1));
        dealerHand.add(addCard(42));
        dealerHand.add(addCard(4));


        boolean playerWin = blackjack.isWinner(playerHand,dealerHand);
        Assert.assertEquals(false, playerWin);
    }

    //dealer and player ties
    @Test
    public void highestValueTest3() {
        ArrayList<String> playerHand = new ArrayList<>();
        ArrayList<String> dealerHand = new ArrayList<>();
        playerHand.add(addCard(13));
        playerHand.add(addCard(9));

        dealerHand.add(addCard(3));
        dealerHand.add(addCard(1));
        dealerHand.add(addCard(42));
        dealerHand.add(addCard(2));


        boolean playerWin = blackjack.isWinner(playerHand,dealerHand);
        Assert.assertEquals(false, playerWin);
    }
}
