
package pkgfinal.project;
/**
 *
 * @author Zachary
 */
public class Dealer extends Hand{ //Child of Hand, overrides its functions for its purposes
    
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
        System.out.println("\nDealer hand is:");
        super.showHand();
        System.out.println("\n");
    }
    
    @Override
    public void showPart() {
        System.out.println("\nDealer hand is:");
        super.showPart();
    }
}
