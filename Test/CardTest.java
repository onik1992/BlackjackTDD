import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by King Lo on 3/15/2017.
 */
public class CardTest {
    private Card card;

    @Before
    public void setUP(){
        card = new Card();
    }
    @Test
    public void cardStringTest(){
        String nine = card.getCardString(card.getCardNumValue("9"));
        String jack = card.getCardString(11);
        String queen = card.getCardString(12);
        String ace = card.getCardString(1);

        Assert.assertEquals("9",nine);
        Assert.assertEquals("J", jack);
        Assert.assertEquals("Q", queen);
        Assert.assertEquals("A", ace);
    }

    @Test
    public void cardNumValueTest(){

        int three = card.getCardNumValue("3");
        int ten = card.getCardNumValue(card.getCardString(10));
        int jack = card.getCardNumValue(card.getCardString(11));
        int queen = card.getCardNumValue(card.getCardString(12));
        int king = card.getCardNumValue("K");

        Assert.assertEquals(3, three);
        Assert.assertEquals(10, ten);
        Assert.assertEquals(10, jack);
        Assert.assertEquals(10, queen);
        Assert.assertEquals(10, king);

    }

    @Test
    public void suitStringTest(){
        //"♥", "♠", "♣", "♦"
        String clubs = card.getSuitString(1);
        String dimonds = card.getSuitString(2);
        String hearts = card.getSuitString(3);
        String spades = card.getSuitString(4);

        Assert.assertEquals("♣",clubs);
        Assert.assertEquals("♦",dimonds);
        Assert.assertEquals("♥",hearts);
        Assert.assertEquals("♠",spades);
    }


}
