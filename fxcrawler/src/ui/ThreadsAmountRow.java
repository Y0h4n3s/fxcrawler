package ui;

import javafx.scene.control.TextField;

import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.text.*;
import javafx.util.converter.NumberStringConverter;
import javafx.scene.layout.HBox;

/**
 * This class contains a section of the view to be added to the 
 * MainFrame class.
 * @author y0h4n3s
 *
 */
public class ThreadsAmountRow extends HBox{
	
	private static final Logger LOGGER =
			Logger.getLogger(ThreadsAmountRow.class.getName());
	
	Text threadsText = new Text("Threads");
	TextField threadsAmountField = new TextField();
	Text slowerText = new Text("Slower");
	Text fasterText = new Text("Faster");
	Slider threadsSlider = new Slider(1, 1000, 40);
	
	public ThreadsAmountRow() {
		this.setAlignment(Pos.CENTER);
		this.setPrefWidth(500);
		this.setSpacing(10);
		this.getChildren().addAll(this.threadsText, 
								  this.threadsAmountField,
								  this.slowerText,
								  this.threadsSlider,
								  this.fasterText);
		this.doBindings();
	}
	
	/**
	 * Do bindings to connect different parts of this section
	 * of the ui
	 */
	private void doBindings() {
		Bindings.bindBidirectional(
									this.getThreadsAmountFieldTextProperty(),
									this.getThreadsSliderValueProperty(),
									new NumberStringConverter() {
										@Override
										public String toString(Number value) {
											return super.toString(Math.round((double) value));
										}
									});
		
	}
	
	/**
	 * 
	 * @return the stringProperty of the TextField that holds the amount
	 * of threads to be used.
	 */
	public StringProperty getThreadsAmountFieldTextProperty() {
		System.out.println(this.threadsAmountField.textProperty());
		return this.threadsAmountField.textProperty();
	}
	
	/**
	 * 
	 * @return a DoubleProperty representing the valueProperty of the 
	 * threadsSlider.
	 */
	public DoubleProperty getThreadsSliderValueProperty() {
		return this.threadsSlider.valueProperty();
	}
	
	public String getThreadsFieldValue() {
		return this.threadsAmountField.getText();
	}
}
