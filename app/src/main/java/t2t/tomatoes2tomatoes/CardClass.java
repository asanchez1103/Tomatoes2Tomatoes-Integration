package t2t.tomatoes2tomatoes;

/**
 * Created by Alejandro on 5/3/2015.
 */

/*Guneet Singh Chadha
 * Group 404
 * cardClass
 * Function: cardClass is used to recieve input from text files redApples and GreenApples for the Red Cards and Green Cards respectively.
 *
 * GREEN CARDS contain an adjective, as well as a list of synonyms. Format: "Adjective" + " - " + "(Synonyms)"
 * RED CARDS contain a proper noun, as well as a small description. Format: "Proper Noun" + " - " + "This is a description. May contain more than one sentence."
 * Red Cards and Green Cards are placed into their own respective array of card classes/structs.
 *
 * When players 'draw' cards from the Red/Green card stack, the Program randomly selects a Red/Green card from the draw array.
 * That card is removed from the draw array, and the last card of the array is placed in the new spot, to avoid errors.
 *
 * Any Red cards that are kept in the hand are in the players' Red Hand arrays, until used, or until the game ends.
 * Red Cards that are used by players are kept in a Discard array.  Cards are kept in Discard array in the order they were discarded.
 *
 * Green Cards that are won by players are kept in the players' Green Hand arrays, until the game ends.
 *
 *
 */
/* these imports should be in main class
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//found parsing class, need to implement into cardClass. Site: http://www.javapractices.com/topic/TopicAction.do?Id=87
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
*/
public class CardClass {

    int cardID;//Each Card will be given an ID upon being entered.
    String cardName;//Green Card: Adjective. Red Card: Proper Noun
    String cardInfo;//Green Card: Synonyms of adjective. Red Card: Description of Proper Noun.
    int cardHolder;//Location of Card. 0 = Deck. 1-8 = Player # 1-8. 9 = Discard Pile(Red Cards only). 10 = On table(Green Cards only).
    boolean faceUp;//Is card face up or facedown? True= Face up, visible to players. False = Facedown, not visible to players.

    public CardClass(int startId, String theName, String theInfo, int theHolder, boolean pubYet){

        cardID = startId;
        cardName = theName;
        cardInfo = theInfo;
        cardHolder = theHolder;
        faceUp = pubYet;

    }
    public CardClass() {
        cardID = 0;
        cardName = "";
        cardInfo = "";
        cardHolder = 0;
        faceUp = false;
        // TODO Auto-generated constructor stub
    }
    public void setID(int newID){
        cardID = newID;
    }
    public void setName(String newName){
        cardName = newName;
    }
    public void setInfo(String newInfo){
        cardInfo = newInfo;
    }
    public void setHolder(int newHolder){
        cardHolder = newHolder;
    }
    public void setFlip(boolean flipIt){
        faceUp = flipIt;
    }



}
