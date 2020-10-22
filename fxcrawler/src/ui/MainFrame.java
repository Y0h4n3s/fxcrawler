package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import ui.TargetUrlInputRow;
import ui.ThreadsAmountRow;

public class MainFrame extends Application{
	
	@Override
	public void start(Stage stage) {
		GridPane root = new GridPane();
		root.setVgap(20);
		HBox targetUrlInputRow = new TargetUrlInputRow();
		HBox threadsAmountRow = new ThreadsAmountRow();
		root.addRow(0, targetUrlInputRow);
		root.addRow(1, threadsAmountRow);
		
		Scene scene = new Scene(root, 700, 600);
		stage.setScene(scene);
		stage.show();
	}
	
	

}
