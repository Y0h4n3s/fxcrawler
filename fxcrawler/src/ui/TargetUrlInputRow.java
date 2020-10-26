package ui;

import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderImage;
import javafx.scene.layout.BorderRepeat;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.logging.Logger;

import app.Main;
import javafx.beans.property.StringProperty;

public class TargetUrlInputRow extends HBox{
	
	private static final Logger LOGGER =
			Logger.getLogger(TargetUrlInputRow.class.getName());
	
	private Text urlInputText = new Text("Target Url:");
	private TextField urlInputField = new TextField("https://jsoup.org");
	private Button startCrawlButton = new Button("Go Crawl");
	
	public TargetUrlInputRow() {
		this.setSpacing(20);
		this.setAlignment(Pos.CENTER);
		this.urlInputField.setPrefWidth(500);
		this.getChildren().addAll(
				this.urlInputText,
				this.urlInputField,
				this.startCrawlButton
				);

		
	}
	
	public StringProperty getUrlInputFieldTextProperty() {
		return this.urlInputField.textProperty();
	}
	public Button getStartCrawlButton() {
		 return this.startCrawlButton;
	}
}	


	