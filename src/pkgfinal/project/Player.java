
package pkgfinal.project;
/**
 *
 * @author Zachary
 */
public class Player extends Hand{ //Child of Hand, overrides its functions for its purposes
    @Override
    public void addCard(Card card) {
        super.addCard(card);
    }

    @Override
    public int getValue() {
        return super.getValue();
    }

    @Override
    public void showHand() {
        System.out.println("\nPlayer hand is:");
        super.showHand();
    }
}
