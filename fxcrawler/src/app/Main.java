
package app;
	
import java.util.logging.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;



import ui.MainFrame;

public class Main extends Application{
	
	private static final Logger LOGGER =
			Logger.getLogger(Main.class.getName());
	
	private EventDispacher eventDispacher;
	public void start(Stage stage) {
		this.eventDispacher = new EventDispacher();
		MainFrame mainFrame = this.eventDispacher.getMainFrame();
		Scene scene = new Scene(mainFrame, 700, 600);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String args[]) {
		Application.launch(args);
	}
}
