package ui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class TargetUrlInputRow extends HBox{
	
	Text urlInputText = new Text("Target Url:");
	TextField urlInputField = new TextField();
	Button startCrawlButton = new Button("Go Crawl");
	public TargetUrlInputRow() {
		this.setSpacing(20);
		this.setAlignment(Pos.CENTER);
		urlInputField.setPrefWidth(500);
		this.getChildren().addAll(this.urlInputText,
								  this.urlInputField,
								  this.startCrawlButton);
	}
}
