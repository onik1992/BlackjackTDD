import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by King Lo on 3/15/2017.
 */
public class DeckTest {

    private Deck deck;

    @Before
    public void setUp(){
        deck = new Deck();
    }

    //check for the total size of the deck and sout the deck
    @Test
    public void deckTotalSizeTest(){

        int totalCards = deck.getFreshDeck().size();
        int totalShuffledCards = deck.getShuffledDeck().size();
        //test the total cards
        System.out.println();
        System.out.println("fresh deck");
        for(int i = 0; i < totalCards; i++)
        {
            deck.getDrawCards(deck.getFreshDeck().get(i));
            System.out.println();
        }
        System.out.println();
        System.out.println("shuffled deck");
        for(int i = 0; i < totalShuffledCards; i++)
        {
            deck.getDrawCards(deck.getShuffledDeck().get(i));
            System.out.println();
        }
        Assert.assertEquals(52, totalCards);
        Assert.assertEquals(52, totalShuffledCards);
    }
}
