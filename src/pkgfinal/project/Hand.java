
package pkgfinal.project;

import java.util.ArrayList;

/**
 *
 * @author Zachary
 */
public class Hand{
    
    public ArrayList<Card> hand = new ArrayList<>();
    
    //Adds card to ArrayList
    public void addCard(Card card){
        hand.add(card);
    }
    
    //Gets value of cards in deck
    //If the ace puts a player over 21 when it equals 11 then it will equal 1 instead per the rules
    public int getValue(){
        boolean hasAce = false;
        int value = 0;
        int aceHold = 0;
        for(int i = 0; i < hand.size(); i++){
            if(hand.get(i).getValue() == 11){
                hasAce = true;
            }
        }
        if(hasAce == false){
            for(int i = 0; i < hand.size(); i++){
                value = value + hand.get(i).getValue();
            }
            return value;
        }else{
            for(int i = 0; i < hand.size(); i++){
                if(hand.get(i).getValue() != 11){
                    value = value + hand.get(i).getValue();
                }else{
                    aceHold++;
                }
            }
            for(int i = 0; i < aceHold; i++){
                if(value + 11 > 21){
                    value += 1;
                }else{
                    value += 11;
                }
            }
            return value;                   
        }
    }
    
    //Prints the hand and its numerical value
    public void showHand(){
        System.out.println(hand + "(" + getValue() + ")");
    }
    
    //Mainly for the dealer, shows only the first card in the hard and its value
    public void showPart(){
        int size = 0;
        size = hand.size() - 1;
        System.out.println(hand.get(0) + "(" + hand.get(0).getValue() + ")" + " and " + size + " other card(s).");
    }
}
