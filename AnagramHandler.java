//@author:-Apurva Anand andrew id-apurvaa
package hw3;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class AnagramHandler extends GameHandler {
	TextField inputTextField = new TextField();
	Button submitButton=new Button();
	TextArea anagramsTextArea = new TextArea();
	Anagrammer am=new Anagrammer();
	public void handle(ActionEvent event) {
		//enter code here
		game=(Anagrammer)am;
		am.setupWords();
		super.handle(event);
		game.message="Let's play Anagram!";
		messageLabel.setText(game.message);
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

	@Override
	void setupUserGrid() {
		// TODO Auto-generated method stub
		ColumnConstraints columnConstraints = new ColumnConstraints(); 
		columnConstraints.setFillWidth(true); 
		columnConstraints.setHgrow(Priority.ALWAYS); 
		columnConstraints.setHgrow(Priority.ALWAYS);
		userGrid.getColumnConstraints().add(columnConstraints);
		userGrid.setPadding(new Insets(30, 20, 50, 30));
		WordNerd.root.setBottom(userGrid);
		setupInputGrid();
		setupScoreGrid();

	}

	@Override
	void setupInputGrid() {
		// TODO Auto-generated method stub
		GridPane inputGrid = new GridPane();
		Label anagramsToFind = new Label(); 
		inputTextField.setPrefWidth(200);
		submitButton = new Button("Submit"); 
		submitButton.setPrefWidth(200); 
		submitButton.setOnAction(new SubmitButtonEventHandler()); 
		inputGrid.add(inputTextField, 0, 0); 
		inputGrid.add(submitButton, 0, 1); 
		inputGrid.add(anagramsToFind, 0, 2); 
		anagramsTextArea.setPrefSize(200, 100); 
		anagramsTextArea.setEditable(false);
		int noOfAnagrams=am.anagramMap.get(game.clueWord).size();
		anagramsToFind.setText("Find "+ (noOfAnagrams-1) +" anagrams");	
		inputGrid.add(anagramsTextArea, 0, 4);
		userGrid.add(new Label ("Type your anagram"),0, 0,6,1);
		userGrid.add(inputGrid, 0, 1);
	}

	private class SubmitButtonEventHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			String guess=inputTextField.getText();
			int x=am.nextTry(guess);
			messageLabel.setText(game.message);
			game.score=am.calcScore();
			inputTextField.clear();
			
			//when user has won
			if (game.score > am.anagramMap.get(game.clueWord).size() -2) {
				game.won=true;
				game.message="Congratulations! You won";			
			}
			
			//when user enters a valid input
			if (x==1) {
				anagramsTextArea.setWrapText(true);
				anagramsTextArea.setText(anagramsTextArea.getText()+"\n"+guess);
				trialsTextField.setText(String.valueOf(10-game.trialCount));
				hitTextField.setText(String.valueOf(game.hit));
				missTextField.setText(String.valueOf(game.miss));
			}
			
			//when user enters invalid input
			if (x==0) {
				trialsTextField.setText(String.valueOf(10-game.trialCount));
				hitTextField.setText(String.valueOf(game.hit));
				missTextField.setText(String.valueOf(game.miss));
			}
			
			//when either user has reached maximum trials or won the game
			if  (am.trialCount==10 || game.won==true) {  
				inputTextField.setDisable(true);
				anagramsTextArea.setDisable(true);
				submitButton.setDisable(true);
				scoreTextField.setText(String.valueOf(String.format("%.2f",game.calcScore()))); 
			}
		}

	}

}


