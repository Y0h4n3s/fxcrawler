package ui;

import java.util.logging.Logger;


import javafx.scene.layout.*;


/**
 * 
 * @author y0h4n3s
 * This class is the base container of all the ui elements.
 */
public class MainFrame extends GridPane{
	
	private static final Logger LOGGER =
			Logger.getLogger(MainFrame.class.getName());
	
	private TargetUrlInputRow targetUrlInputRow;
	private ThreadsAmountRow threadsAmountRow;
	private DepthInputRow depthInputRow;
	
	public MainFrame(TargetUrlInputRow targetUrlInputRow, ThreadsAmountRow threadsAmountRow, DepthInputRow depthInputRow) {
		this.targetUrlInputRow = targetUrlInputRow;		
		this.threadsAmountRow = threadsAmountRow;
		this.depthInputRow = depthInputRow;
		this.setVgap(20);
		this.addRow(0, targetUrlInputRow);
		this.addRow(1, threadsAmountRow);
		this.addRow(2, depthInputRow);
		
	}
	
	
	

}
