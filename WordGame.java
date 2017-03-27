//@author:-Apurva Anand  andrew id-apurvaa
package hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public abstract class WordGame {
	StringBuilder fileContent = new StringBuilder(); 	//stores the content of the file
	public String[] dictionaryWords;					//stores words read from the dictionary
	public String gameWord;  							//word picked up from the dictionary for the puzzle 
	public StringBuilder userInputs = new StringBuilder(); //stores all guesses entered by the user
	public String message;								//message to be printed on console after each user interaction
	public static final int MAX_TRIALS = 10; 
	public int trialCount=0;							//incremented everytime user enters a valid guess
	public boolean won = false;							//set to true when user input matches the gameWord
	public String clueWord;								//clue shown to the user on console
	public double score;								//updated by calcScore() 
	public int hit; 									//number of correct guesses made by player
	public int miss; //number of wrong guesses made by player
	
	WordGame() {
		dictionaryWords = readFile();
	}

	//readfile() opens file and reads it into StringBuffer
	//returns an array of String by splitting the words on new line
	public String[] readFile() {
		//enter code here
		Scanner string = null;
		try {
			string = new Scanner (new File ("dictionary.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (string.hasNextLine()) {
			fileContent.append(string.nextLine() + "\n");
		}
		string.close();
		dictionaryWords = fileContent.toString().split("\\n"); 		//an array of words created
		return dictionaryWords;

	}

	//pickWord() picks a word randomly from within the dictionaryWords array
	//It returns the word that has at least 4 or more characters in it.
	public String pickGameWord(){
		//enter code here
		int index = new Random().nextInt(dictionaryWords.length); 	//generating a random index to pick a word from dictionaryWords
		gameWord  = dictionaryWords[index];
		if (gameWord.length()<4) {									//length of the word should be >4
			index = new Random().nextInt(dictionaryWords.length);

		}
		return gameWord;
	}


	public abstract int nextTry(String input);

	public abstract double calcScore();

	public abstract void setupWords();

}
