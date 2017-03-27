//@author:-Apurva Anand  andrew id-apurvaa
package hw3;

import java.util.Random;

public class Hangman extends WordGame{

	//Invokes pickGameWord and then creates the clueWord by randomly replacing alphabets 
	//in the word with dashes until the dashCount is equal to or 
	//more than half the length of the word
	//
	@Override
	public void setupWords(){
		//enter code here
		super.pickGameWord();
		int n=gameWord.length()/2;		//half or more of the characters of the gameWord need to be replaced 
		String gameWordTmp=gameWord; 	//local copy of gameWord so that gameWord remains unmodified
		clueWord=gameWordTmp;			//initialized clueWord as gameWord and then replacing its characters with '-'
		while (countDashes(gameWordTmp)<=n) {
			int randomIndex=new Random().nextInt(gameWordTmp.length());
			char randomChar=gameWordTmp.charAt(randomIndex);
			clueWord=gameWordTmp.replace(randomChar, '-');	
			if (clueWord.charAt(randomIndex)=='-') {	//the next random index should not return '-'
				randomIndex=new Random().nextInt(gameWordTmp.length());
			}
			gameWordTmp=clueWord;	
		}
	}

	//dashCount() returns the number of dashes in the word 
	public int countDashes(String word) {
		//enter code here
		int dashesNumber=0;				//keeps a count of number of dashes
		for( int i=0; i< word.length(); i++ ) {
			if( word.charAt(i) == '-' ) {
				dashesNumber++;
			} 
		}
		return dashesNumber;
	}

	@Override
	public int nextTry(String input) {
		//enter code here
		clueWord=clueWord.toLowerCase();
		gameWord=gameWord.toLowerCase();
		input=input.toLowerCase();
		int response=0;											//returns 1,-1 and 0 as per the valid and invalid inputs
		if (gameWord.contains(input) && !clueWord.contains(input)) { //valid input
			message="***You got that right***";
			userInputs.append(input);
			int replaceIndex=gameWord.indexOf(input); 			//stores the index of guess in gameWord
			while (replaceIndex>=0) {							//looping for multiple occurences
				char replaceChar=gameWord.charAt(replaceIndex); //stores the letter in the gameWord which user has input
				clueWord=clueWord.substring(0,replaceIndex) + replaceChar + clueWord.substring(replaceIndex+1); //replacing the '-' in clueWord with the correct guesses
				replaceIndex = gameWord.indexOf(input, replaceIndex + 1); //takes care of multiple occurences
			}
			super.trialCount++;
			super.hit++;
			response = 1;
			if (clueWord.equals(gameWord)) {
				super.won=true;				
			}

		} else if (!gameWord.contains(input) && userInputs.indexOf(input)<0) { //valid input
			message="Sorry! Got it wrong!";
			userInputs.append(input);
			super.trialCount++;
			super.miss++;
			response = 0;
		}
		return response;
	}

	@Override
	public double calcScore() {
		//enter code here
		if (super.miss==0) {
			super.score=super.hit;
		} else {
			super.score=(double) super.hit/ (double) super.miss;
		}
		return super.score;
	}
}


