
package pkgfinal.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import static pkgfinal.project.FinalProject.checkIn;

/**
 *
 * @author Zachary
 */
public class Game {
    
    private ArrayList<Card> deck;
    private int buyin;
    
    //Initialize deck and desired buyin
    public Game(ArrayList deck, int buyin){
        this.deck = deck;
        this.buyin = buyin;
    }
    
    //Prints desired string with three dots after to simulate loading time
    //Uses exception handling
    public static void print(String target) throws InterruptedException{
        System.out.print(target);
        try
        {
            Thread.sleep(675);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.print(".");
        try
        {
            Thread.sleep(675);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.print(".");
        try
        {
            Thread.sleep(675);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.print(".");
        try
        {
            Thread.sleep(675);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.print("\n");
    }
    
    //Makes use of the Collections function which randomizes the ArrayList and "shuffles" the deck of cards
    public ArrayList shuffleDeck(){
        try {
            print("\nShuffling deck");
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        Collections.shuffle(deck);
        return deck;
    }
    
    //Pops the top two cards from the deck and adds it to the player's or dealer's hand
    public void dealCards(ArrayList cards, Hand hand){
        for(int i = 0; i < 2; i++){
            hand.addCard((Card)cards.get(i));
            cards.remove((Card)cards.get(i));
        }
    }
    
    //
    public String checkOption(Scanner scanf, Player player, Dealer dealer){
        String option = "";
        double percentBust = 0.0;
        int numCards = (21 - player.getValue()) + 1;
        numCards = 12 - numCards;
        if(dealer.hand.get(0).getValue() >= numCards){
            percentBust = (numCards * 4) - 1;
            percentBust = 100 * (percentBust/deck.size());
        }else{
            percentBust = (numCards * 4);
            percentBust = 100 * (percentBust/deck.size());
        }
        System.out.println("Would you like to hit or stand? (" + Math.round(percentBust) + "% chance to bust at this value)");
        option = scanf.next();
        while(true){
            if("hit".equals(option.toLowerCase())){
                return option;
            }else if("stand".equals(option.toLowerCase())){
                return option;
            }else{
                System.out.println("Invalid input. Try again.");
                option = scanf.next();
            }
        }
    }
    
    public int placeBet(Scanner scanf, Player hand, int buyin){
        String entry;
        int bet = 0;
        System.out.println("How much are you betting? (You have $" + buyin + ") (Remember your betting unit or 3%)");       
        entry = scanf.next();
        while(!checkIn(entry)){
            System.out.println("Only input numbers. Try again.");
            entry = scanf.next();
        }
        while(Integer.parseInt(entry) <= 0 || Integer.parseInt(entry) > buyin){
            System.out.println("Invalid bet. Try again.");
            entry = scanf.next();
        }
        
        bet = Integer.parseInt(entry);
        
        return bet;
    }
    
    public int checkWin(Player hand1, Dealer hand2, int turn){
        if(hand1.getValue() > 21){
            return 2;
        }
        if(hand2.getValue() > 21){
            return 1;
        }
        if(hand1.getValue() == 21 && turn == 1){
            return 3;
        }
        if(hand1.getValue() == 21){
            return 1;
        }
        if(hand2.getValue() == 21){
            return 2;
        }
        if(hand1.getValue() < 21 && hand2.getValue() < 21){
            return 0;
        }
        return 0;
    }
    
    public void resetDeck(){
        Deck cards = new Deck();
        deck = cards.makeDeck();
    }
    
    public void showChart(){       
        System.out.println("////////////////////Cheat Sheet////////////////////");
        System.out.println("S13-S17 = H");
        System.out.println("S18 (Dealer has 2,7, or 8) = S");
        System.out.println("S18 (Dealer has 3-6) = H then S, (Dealer has 9-A = H)");
        System.out.println("S19-S20 = S");
        System.out.println("H5-H8 = H");
        System.out.println("H9 (Dealer has 2,7-A) = H, (Dealer has 3-6 = H then S)");
        System.out.println("H10 (Dealer has 2-9) = H then S, (Dealer has 10-A = H)");
        System.out.println("H11 (Dealer has 2-10) = H then S, (Dealer has A = H)");
        System.out.println("H12 (Dealer has 2-3,7-A) = H, (Dealer has 4-6 = S)");
        System.out.println("H13-H16 (Dealer has 2-6) = S, (Dealer has 7-A = H)");
        System.out.println("H17-H18+ = S");
        System.out.println("///////////////////////////////////////////////////\n");
        System.out.println("Following this chart should give you the best chance at winning no matter your hand.\n");
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
    
    public int playRound(String option, Scanner scanf, Player player, Dealer dealer, int bet, int buyin){      
        System.out.println("///////////////////////////////////////////////////");
        showChart();
        option = checkOption(scanf, player, dealer);
        if("hit".equals(option.toLowerCase())){
            player.addCard(deck.get(0));
            deck.remove(deck.get(0));
            System.out.printf("///Bet: %d///////////////////Balance: %d///", bet, buyin - bet);
            player.showHand();
            if(checkWin(player,dealer,2) == 1){
                System.out.println("You Win!");
                return buyin + bet;
            }else if(checkWin(player,dealer,2) == 2){
                System.out.println("Dealer wins.");
                dealer.showHand();
                return buyin - bet;
            }else{
                dealer.showPart();
            }              
        }else{ //stand
            while(checkWin(player,dealer,2) == 0 && dealer.getValue() < 17){
                dealer.addCard(deck.get(0));
                deck.remove(deck.get(0));
                if(checkWin(player,dealer,2) == 2){
                    System.out.println("Dealer wins.");
                    dealer.showHand();
                    return buyin - bet;
                }else if(checkWin(player,dealer,2) == 1){
                    System.out.println("You Win!");
                    dealer.showHand();
                    return buyin + bet;
                }
            }
            if(player.getValue() > dealer.getValue()){
                System.out.println("You Win!");
                dealer.showHand();
                return buyin + bet;
            }else if(dealer.getValue() > player.getValue() && dealer.getValue() < 21){
                System.out.println("Dealer wins.");
                dealer.showHand();
                return buyin - bet;
            }else{
                System.out.println("You Win!");
                dealer.showHand();
                return buyin + bet;
            }
        }
        System.out.println("///////////////////////////////////////////////////");
        
        return -1;
    }
 
    public int playGame(Scanner scanf, int buyin){
        int roundHold;
        Player player = new Player();
        Dealer dealer = new Dealer();
        int bet = placeBet(scanf, player, buyin);
        resetDeck();
        deck = shuffleDeck();
        
        try {
            print("Dealing cards");
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        dealCards(deck,player);
        dealCards(deck,dealer);
        
        System.out.println("\nDeciding whether to hit or stand is the basic premise of Blackjack");
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("Hitting gives you another card, while standing leaves your hand the same for the rest of the game and lets the dealer draw until someone wins or loses.");
        try
        {
            Thread.sleep(5000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("A basic cheat sheet will be displayed to show you the best move, given your current hand value. (H = Hit, S = Stand) (H in front of value = Ace in hand, S in front = No ace)\n");
        try
        {
            Thread.sleep(5000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\n////////////////Blackjack Trainer///////////////////");
        System.out.printf("///Bet: %d///////////////////Balance: %d///", bet, buyin - bet);
        player.showHand();
        dealer.showPart();
        
        if(checkWin(player,dealer,1) == 1){
            System.out.println("You Win!");
            return buyin + bet;
        }else if(checkWin(player,dealer,1) == 2){
            System.out.println("Dealer wins.");
            dealer.showHand();
            return buyin - bet;
        }else if(checkWin(player,dealer,1) == 3){
            System.out.println("BLACKJACK.");
            return buyin + Math.round(bet + bet/2);
        }
        String option = "";  
        for(int i = 0; i < 5; i++){
            roundHold = playRound(option, scanf, player, dealer, bet, buyin);
            if(roundHold >= 0){
                return roundHold;
            }
        }
        return 0;
    }
}
