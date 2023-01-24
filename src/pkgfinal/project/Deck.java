
package pkgfinal.project;

import java.util.ArrayList;

/**
 *
 * @author Zachary
 */
public class Deck{
    
    public static ArrayList<Card> deck = new ArrayList<>(52);
    
    //initialize full deck of cards minus jokers
    public Deck(){
        deck = makeDeck();
    }
    
    //Adds 13 cards for each suit (4) to ArrayList of cards
    public final ArrayList makeDeck(){
        ArrayList<Card> cards = new ArrayList<>(52);
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 13; j++){
                cards.add(new Card(i,j));
            }
        }
        return cards;
    }  
}
