package ui;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class DepthInputRow extends HBox{

	private Text crawlDepthText = new Text("Crawl Depth");
	private TextField crawlDepthInputField = new TextField("2");
	
	public DepthInputRow() {
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		this.getChildren().addAll(
				this.crawlDepthText, 
				this.crawlDepthInputField
				);
}
	
	public Integer getCrawlDepth() {
		return Integer.parseInt(this.crawlDepthInputField.getText());
	}
}
