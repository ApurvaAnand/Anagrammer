//@author:-Apurva Anand;andrew id-apurvaa

package hw3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagrammer extends WordGame{
	public static Map<String, List<String>> anagramMap = new HashMap<String, List<String>>();
	public static List<String> anagrams=new ArrayList<String>();
	public StringBuilder dictionaryTmp=new StringBuilder();
	StringBuilder userInput = new StringBuilder(); 

	Anagrammer(){
		loadAnagramMap();	
	}

	//creates hashmap of words as keys and its anagrams as it values stored in a list
	public void loadAnagramMap() {
		Map<String, List<String>> anagramMapTemp = new HashMap<String, List<String>>();		
		for (String w:super.dictionaryWords) {
			dictionaryTmp.append(w);
			String alpha = sort(w);
			anagrams = anagramMapTemp.get(alpha);
			if (anagrams == null)
				anagramMapTemp.put(alpha, anagrams=new ArrayList<String>());
			anagrams.add(w);
			anagramMap.put(w, anagrams);
		}	
	}

	//creates a new sorted string
	public String sort(String s) {
		char[] a = s.toCharArray();
		java.util.Arrays.sort(a);
		return new String(a);
	}

	@Override
	public int nextTry(String input) {
		// TODO Auto-generated method stub
		int response=0;
		input.toLowerCase();	

		//if user enters correct anagram other than the Clueword itself
		if (anagramMap.get(super.clueWord).contains(input) && !input.equals(super.clueWord) && userInputs.indexOf(input)<0) {
			int i=anagramMap.get(super.clueWord).size()-2;
			super.message="You got that right! "+ (i-super.hit) +" to go!";
			userInput.append(input);
			super.trialCount++;
			super.hit++;
			response=1;

			//when user has entered all the anagrams in the list
			if (super.hit== anagramMap.get(super.clueWord).size() -1) {
				super.won=true;
				super.message="Congratulations! You won";
			}

			//for invalid input checks whether the word is in dictionary or not
		} else if (!anagramMap.get(super.clueWord).contains(input) && userInputs.indexOf(input)<=0) {

			//when invalid input and in dictionary
			if (dictionaryTmp.toString().contains(input)) {
				super.message="Sorry "+ input+ " is not "+ super.clueWord+"'s anagram";
				userInput.append(input);
				super.trialCount++;
				super.miss++;
				response=0;
				//when invalid input and not in dictionary
			} else if (!dictionaryTmp.toString().contains(input)) {
				super.message=("Sorry, "+input+" is not in dictionary");
				userInput.append(input);
				super.trialCount++;
				super.miss++;
				response=0;
			}
			//when user enters clueword itself
		} else if (input.equals(super.clueWord) && userInputs.indexOf(input)<=0) {
			super.message="That's the clue word";
			response=-1;
			//if user enters a word again
		} else if (userInput.toString().contains(input)) {
			super.message="You have already entered that";
			response=-1;
		} 
		return response;
	}

	//calculates score of anagram game
	@Override
	public double calcScore() {
		// TODO Auto-generated method stub
		if (super.miss==0) {
			super.score=super.hit;
		} else {
			super.score=(double) super.hit/ (double) super.miss;
		}
		return super.score;
	}

	//picks up a word randomly from dictionary such that it atleast has 3 anagrams
	@Override
	public void setupWords() {
		// TODO Auto-generated method stub
		super.gameWord=pickGameWord();
		while (anagramMap.get(gameWord).size()<3) {
			super.gameWord=pickGameWord();
		}
		super.clueWord=super.gameWord;
	}
}
