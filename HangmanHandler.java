//@author:-Apurva Anand  andrew id-apurvaa
package hw3;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class HangmanHandler extends GameHandler {

	Button[] alphaButtons=new Button[26];	

	//invokes parent's handle
	public void handle(ActionEvent event) {
		//enter code here
		
		super.handle(event);
		//for relaunching the game
		Platform.runLater( () -> {
			try {
				WordNerd wn=new WordNerd();
				wn.start(new Stage());
			} catch (Exception e) {
				//TODO Auto-generated catch block
				//e.printStackTrace();
			}
		} );
	}

	//sets up the usergrid; adds it to root.Invokes setupInputGrid() & setupScoreGrid();
	void setupUserGrid() {
		//enter code here
		Hangman hm=new Hangman();
		game=(Hangman)hm;
		hm.setupWords();
		ColumnConstraints columnConstraints = new ColumnConstraints(); 
		columnConstraints.setFillWidth(true); 
		columnConstraints.setHgrow(Priority.ALWAYS); 
		columnConstraints.setHgrow(Priority.ALWAYS);
		userGrid.getColumnConstraints().add(columnConstraints);
		userGrid.setPadding(new Insets(30, 20, 20, 30));
		WordNerd.root.setBottom(userGrid);
		setupInputGrid();
		setupScoreGrid();

	}

	//sets up grid containing alphabuttons and attaches it to usergrid
	void setupInputGrid() {
		//enter code here
		GridPane InputGrid= new GridPane();
		String[] myStrings = { "a", "b", "c" ,"d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x"};
		for (int i = 0; i < myStrings.length; i++) {
			String s=myStrings[i];
			alphaButtons[i] = new Button(String.format("%s", s));
			if (game.clueWord.contains(s)) {
				alphaButtons[i].setDisable(true);
			}
			
			alphaButtons[i].setOnAction(new AlphaButtonHandler());

		}

		InputGrid.add(new Label ("Choose next letter"), 0, 0,6,1);
		InputGrid.add(new Label (""), 0, 1,6,1);

		int col = 0, row = 3;	
		for (int i = 0; i < myStrings.length; i++) {
			InputGrid.add(alphaButtons[i], col++, row);		//in each row, add buttons from col 0 to 6
			if (col % 6 ==0) col = 0;						//switch back to column 0 when col reaches 6
			row = (int)((i+1)/6)+3;	
			alphaButtons[i].setPrefSize(30, 20);			//to make row change after every 6 buttons
		}

		alphaButtons[24]=new Button("y");
		alphaButtons[24].setPrefSize(30, 20);
		if (game.clueWord.contains("y")) {
			alphaButtons[24].setDisable(true);
		}

		alphaButtons[24].setOnAction(new AlphaButtonHandler());
		alphaButtons[25]=new Button("z");
		alphaButtons[25].setPrefSize(30, 20);
		if (game.clueWord.contains("z")) {
			alphaButtons[25].setDisable(true);
		}

		alphaButtons[25].setOnAction(new AlphaButtonHandler());
		InputGrid.add(alphaButtons[24], 2, 7);
		InputGrid.add(alphaButtons[25], 3, 7);
		messageLabel.setPrefWidth(250);
		
		userGrid.add(InputGrid,0,0);
	}

	// disables alphabuttons if the user has won or reached maximum trials. Displays the final message
	private void disableAlphaButtons() {
		//enter code here
		if (game.trialCount==10 && (!game.gameWord.equals(game.clueWord))) {   //reached maximum trial and not guessed the word
			scoreTextField.setText(String.valueOf(String.format("%.2f",game.calcScore()))); 
			messageLabel.setText("Sorry! You missed it. It is "+game.gameWord);
			for (int i = 0; i < 26; i++) {
				alphaButtons[i].setDisable(true);
			}				
		} else if (game.clueWord.equals(game.gameWord)) {					//guessed the word 
			scoreTextField.setText(String.valueOf(String.format("%.2f",game.calcScore()))); 
			messageLabel.setText("Congratulations! You got it! :)");
			for (int i = 0; i < 26; i++) {
				alphaButtons[i].setDisable(true);
			}
		}
	}

	//handles alphabuttons and updates trials, scores and messages as per the input
	private class AlphaButtonHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			String i = ((Button)event.getSource()).getText();
			int outcome=game.nextTry(i);
			
			//if incorrect guess buttons turn red and are disabled; trials and misses are updated
			if (outcome==0){
				((Button)event.getSource()).setStyle("-fx-background-color: LIGHTSALMON;");
				((Button)event.getSource()).setDisable(true);
				trialsTextField.setText(String.valueOf(10-game.trialCount));
				missTextField.setText(String.valueOf(game.miss));
				messageLabel.setText(game.message);

			//if correct guess turn green and are disabled; trials and hits are updated
			} else if (outcome==1){
				((Button)event.getSource()).setStyle("-fx-background-color: YELLOWGREEN;");
				((Button)event.getSource()).setDisable(true);
				trialsTextField.setText(String.valueOf(10-game.trialCount));
				hitTextField.setText(String.valueOf(game.hit));
				clueWordLabels.setText(game.clueWord);
				messageLabel.setText(game.message);
			} 
			disableAlphaButtons();
		}
	}
}


