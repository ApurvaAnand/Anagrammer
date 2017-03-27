//@author:-Apurva Anand andrew id-apurvaa
package hw3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public abstract class GameHandler implements EventHandler<ActionEvent> {

	WordGame game;	

	Label messageLabel = new Label(" ");			//used to display the message after very user-move
	Label clueWordLabels=new Label("");	 			//displays clueWord 
	GridPane userGrid= new GridPane();				//holds the alphaGrid and the scoreGrid
	TextField trialsTextField = new TextField();	//displays number of trials to go
	TextField hitTextField = new TextField();		//displays hits
	TextField missTextField = new TextField();		//displays miss
	TextField scoreTextField = new TextField();		//displays game score
	abstract void setupUserGrid();	
	abstract void setupInputGrid();

	//event handler for hangman. sets up the game 
	@Override
	public void handle(ActionEvent event) {
		//enter code here
		messageLabel.setText("Let's play Hangman!");
		scoreTextField.setText("0");
		trialsTextField.setText("10");
		hitTextField.setText("0");
		missTextField.setText("0");
		scoreTextField.setAlignment(Pos.CENTER);
		trialsTextField.setAlignment(Pos.CENTER);
		hitTextField.setAlignment(Pos.CENTER);
		missTextField.setAlignment(Pos.CENTER);
		setupUserGrid();
		setupClueWordStack();		
		
	}		

	//sets up the cluewordstack
	void setupClueWordStack() {
		//enter code here
		StackPane clueWordStack = new StackPane(); 
		clueWordStack.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		WordNerd.root.setCenter(clueWordStack);
		//System.out.println(game.clueWord);
		clueWordLabels.setText(game.clueWord.toLowerCase());
		//clueWordLabels.setText("xxxx");
		clueWordLabels.setFont(Font.font(25));
		clueWordStack.getChildren().add(clueWordLabels);
	}

	//sets up the scoregrid and adds it to usergrid
	void setupScoreGrid() {
		//enter code here
		GridPane scoreGrid=new GridPane();
		scoreGrid.add(new Label (""), 1, 1);
		scoreGrid.add(new Label (""), 1, 2);
		scoreGrid.add(new Label ("Trials to go	"), 1, 3);
		scoreGrid.add(new Label ("Hit n Miss	"), 1, 4);
		scoreGrid.add(new Label ("Game score	"), 1, 5);
		trialsTextField.setPrefWidth(60);
		trialsTextField.setStyle("-fx-background-color: WHITE;-fx-text-box-border: transparent;-fx-focus-color: transparent;-fx-background-insets: 0;");
		hitTextField.setPrefWidth(30);
		hitTextField.setStyle("-fx-background-color: YELLOWGREEN;-fx-text-box-border:transparent ;-fx-focus-color: transparent;-fx-background-insets: 0;-fx-border-radius: 0 0 0 0;-fx-background-radius: 0 0 0 0");
		missTextField.setPrefWidth(30);
		missTextField.setStyle("-fx-background-color: LIGHTSALMON;-fx-text-box-border:transparent ;-fx-focus-color: transparent;-fx-background-insets: 0;-fx-border-radius: 0 0 0 0;-fx-background-radius: 0 0 0 0");
		scoreTextField.setPrefWidth(60);
		scoreTextField.setStyle("-fx-background-color: WHITE;-fx-text-box-border: transparent;-fx-focus-color: transparent;-fx-background-insets: 0;");
		scoreGrid.add(trialsTextField, 2, 3,2,1);
		scoreGrid.add(hitTextField, 2, 4,1,1);
		scoreGrid.add(missTextField, 3,4,1,1);
		scoreGrid.add(scoreTextField, 2,5,2,1);
		userGrid.add(scoreGrid, 6, 0,30,30);
		userGrid.add(messageLabel, 6,2,30,1);

	}
}
