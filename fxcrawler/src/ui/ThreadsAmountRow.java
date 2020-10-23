package ui;

import javafx.scene.control.TextField;

import java.util.logging.Logger;


import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.text.*;
import javafx.scene.layout.HBox;

public class ThreadsAmountRow extends HBox{
	
	private static final Logger LOGGER =
			Logger.getLogger(ThreadsAmountRow.class.getName());
	
	Text threadsText = new Text("Threads");
	TextField threadsAmountField = new TextField();
	Text slowerText = new Text("Slower");
	Text fasterText = new Text("Faster");
	Slider threadsSlider = new Slider();
	
	public ThreadsAmountRow() {
		this.setAlignment(Pos.CENTER);
		this.setPrefWidth(500);
		this.setSpacing(10);
		this.getChildren().addAll(this.threadsText, 
								  this.threadsAmountField,
								  this.slowerText,
								  this.threadsSlider,
								  this.fasterText);
	}
}
