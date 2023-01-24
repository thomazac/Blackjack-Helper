
package pkgfinal.project;
/**
 *
 * @author Zach Thomas ZRTFCF
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class FinalProject {
    
    //Holds user input and returns it
    public static String checkStart(Scanner scanf){
        String startHold;
        startHold = scanf.next();
        
        return startHold;
    }
    
    //Holds user input of buyin, makes sure user enters a number
    public static int getBuyin(Scanner scanf){
        String entry;
        int buyin = 0;
        
        entry = scanf.next();
        while(!checkIn(entry)){
            System.out.println("No symbols. Try again.");
            entry = scanf.next();
        }
        
        buyin = Integer.parseInt(entry);
        
        return buyin;
    }
    
    //Checks if inputted string is valid (only contains numbers)
    public static boolean checkIn(String input){
        boolean isValid = true;
        
        for(int i = 0; i < input.length(); i++){
            if(!(input.charAt(i) >= '0' && input.charAt(i) <= '9')){
                isValid = false;
            }
        }
        
        return isValid;
    }

    //Prints intro tips on program start
    public static void playIntro(){
        System.out.println("\nThis is a training program to help understand the fundamentals of Blackjack.");
        try
        {
            Thread.sleep(4000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("The player will be given the odds a certain move will be successful. This will help make more calculated decisions.");
        try
        {
            Thread.sleep(5000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("Lets begin.\n");
        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("A general rule of thumb is to buy in for 25 betting units for each hour of planned play time.");
        try
        {
            Thread.sleep(5000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("A betting unit is how much you plan to bet each hand you play.");
        try
        {
            Thread.sleep(3500);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("So if your betting unit is $100 and you plan to play for an hour, you should buy in for $2,500.");
        try
        {
            Thread.sleep(5000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("(Alternatively, you can bet 3% of your current balance)\n");
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
    
    public static void main(String[] args) {
        String start = "";
        int buyin = 0;
        int status = -1;
        
        System.out.println("Welcome to Blackjack Trainer! Type 'start' when you want to start.");
        Scanner scanf = new Scanner(System.in);
        start = checkStart(scanf); //check if user wishes to start
        while(!"start".equals(start.toLowerCase())){
            System.out.println("Invalid input. Try again.");
            start = checkStart(scanf);
        }
        
        playIntro(); //tips

        //This segment gets a user's desired buy in and makes sure it is valid
        System.out.println("How much money would you like to start out with? Do not use a dollar sign or comma. (Maximum buy-in is $1,000,000)");
        buyin = getBuyin(scanf);
        if(buyin <= 0 || buyin > 1000000){
            System.out.println("Invalid input. Try again.");
            buyin = getBuyin(scanf);
        }
        
        //Creates a playable deck
        Deck cards = new Deck();
        ArrayList<Card> deck = new ArrayList<>(52);
        deck = cards.makeDeck();
        
        //Makes a new game and begins it
        Game game = new Game(deck,buyin);
        System.out.println("\n///////////////////////////\nBeginning Game.\n///////////////////////////\n");
        status = game.playGame(scanf, buyin);
        boolean play = true;
        while(status != 0 && play == true){ //While a user says yes to play again and has enough money, the game loops back and continues 
            while(!"yes".equals(start.toLowerCase()) && !"no".equals(start.toLowerCase())){
                System.out.println("Play Again? (yes/no)");
                start = scanf.next();
            }
            if("yes".equals(start.toLowerCase())){
                start = "";
                status = game.playGame(scanf, status);
            }else if("no".equals(start.toLowerCase())){
                play = false;
            }                           
        }
        System.out.println("You left the table with $" + status + ".\nThanks for playing!"); //Prints ending balance
        
        scanf.close(); //Closes scanner at end since you can only open one
    }
    
}
