
package pkgfinal.project;
/**
 *
 * @author Zachary
 */
public class Card{
    
    public static String[] suits = new String[]{"Hearts","Diamonds","Spades","Clubs"};
    public static String[] values = new String[]{"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
    public int suit;
    public int value;
    
    //initialize card
    public Card(int suit, int value){
        this.suit = suit;
        this.value = value;
    }
    
    //Assigns correct value to cards since array indexing is incorrect for blackjack
    public int getValue(){
        if(value <= 8){
            int numVal;
            numVal = Integer.parseInt(values[value]);
            return numVal;
        }else if(value >= 9 && value < 12){
            return 10;           
        }else if(value == 12){
            return 11;
        }
        
        return 0;
    }
    
    //Formats cards cleanly and makes easier to read
    @Override
    public String toString(){
        return values[value] + " of " + suits[suit];
    }
}
