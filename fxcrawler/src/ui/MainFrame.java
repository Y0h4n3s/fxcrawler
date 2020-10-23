package ui;

import java.util.logging.Logger;


import javafx.scene.layout.*;

public class MainFrame extends GridPane{
	
	private static final Logger LOGGER =
			Logger.getLogger(MainFrame.class.getName());
	
	private TargetUrlInputRow targetUrlInputRow;
	public MainFrame(TargetUrlInputRow targetUrlInputRow) {
		this.targetUrlInputRow = targetUrlInputRow;		
		this.setVgap(20);
		HBox threadsAmountRow = new ThreadsAmountRow();
		this.addRow(0, targetUrlInputRow);
		this.addRow(1, threadsAmountRow);
		
	}
	
	
	

}
