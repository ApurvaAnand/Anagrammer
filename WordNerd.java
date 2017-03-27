//@author:-Apurva Anand andrew id-apurvaa
package hw3;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WordNerd extends Application{
	static BorderPane root = new BorderPane();
	Stage primaryStage;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//enter code here
		Scene scene;
		primaryStage.setTitle("The Word Nerd"); //initializes primary stage and title
		setupMenus();							//invokes setupMenus
		scene = new Scene(root, 600, 300); 		//attaches root to the scene
		primaryStage.setScene(scene);			//attaches attaches scene to the root
		primaryStage.show();				
	}

	//sets up the menubar and attaches menuitems to their handlers
	public void setupMenus() {
		//enter code here
		MenuBar menuBar = new MenuBar();
		root.setTop(menuBar);
		Menu gameMenu = new Menu("Game");
		Menu playMenu = new Menu("Play");
		MenuItem Hangman=new MenuItem("Hangman");
		MenuItem Anagram=new MenuItem("Anagram");
		playMenu.getItems().add(Hangman);
		playMenu.getItems().add(Anagram);
		Hangman.setOnAction(new HangmanHandler());
		Anagram.setOnAction(new AnagramHandler());
		MenuItem closeGameItem = new MenuItem("Close Game");

		//event handler for close menuitem
		closeGameItem.setOnAction(new EventHandler<ActionEvent> () {
			@Override
			public void handle(ActionEvent event) {
				root.setBottom(null);
				root.setCenter(null);		
			}
		});

		MenuItem exitMenuItem = new MenuItem("Exit");

		//event handler for exit
		exitMenuItem.setOnAction(actionEvent -> Platform.exit());
		gameMenu.getItems().addAll(playMenu, closeGameItem,
				new SeparatorMenuItem(), exitMenuItem);
		Menu helpMenu = new Menu("Help");
		MenuItem aboutMenuItem=new MenuItem("About");
		helpMenu.getItems().add(aboutMenuItem);
		aboutMenuItem.setOnAction(new AboutEventHandler());
		menuBar.getMenus().addAll(gameMenu, helpMenu);
	}

	//handles About menu
	private class AboutEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			//enter code here
			Alert alert=new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("About");
			alert.setHeaderText("The Word Nerd\n");
			alert.setContentText("Version 1.0\n"
					+ "Release 1.0\n"
					+ "Copyright CMU\n"
					+"Developed by Apurva\n");
			Image image = new Image(this.getClass().getResource("minions-06-anotando.png").toString()); ImageView imageView = new ImageView();
			imageView.setImage(image);
			imageView.setFitWidth(100);
			imageView.setPreserveRatio(true); imageView.setSmooth(true); alert.setGraphic(imageView); alert.setGraphic(imageView); alert.showAndWait();
		}
	}

}
