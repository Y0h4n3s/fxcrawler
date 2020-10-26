package app;

import javafx.event.*;

import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ui.*;
import javafx.scene.control.Button;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.SimpleStringProperty;
import crawler.CrawlHandler;

import crawler.SiteGrabber;


public class EventDispacher {
	
	private static final Logger LOGGER =
				Logger.getLogger(EventDispacher.class.getName());
	
	private Button goCrawlButton;
	private MainFrame mainFrame;
	private StringProperty toCrawlUrlStringProperty = new SimpleStringProperty();
	private StringProperty threadsAmountStringProperty = new SimpleStringProperty();
	private StringProperty validUrlStringProperty = new SimpleStringProperty();
	private CrawlHandler crawlHandler;
	ThreadsAmountRow threadsAmountRow;
	DepthInputRow depthInputRow;
	TargetUrlInputRow targetUrlInputRow;
	public EventDispacher() {
		
		this.targetUrlInputRow = new TargetUrlInputRow();
		this.depthInputRow = new DepthInputRow();
		this.threadsAmountRow = new ThreadsAmountRow();
		this.goCrawlButton = targetUrlInputRow.getStartCrawlButton();
		
		this.mainFrame = new MainFrame(this.targetUrlInputRow, this.threadsAmountRow, this.depthInputRow);
		
		//Bindings
		this.doBindings();
		
		
		//Listeners
		this.addGoCrawlListeners();
		
		//Option Validity Checkers
		this.doValidityChecks();
	}
	
	private void addGoCrawlListeners() {
		this.goCrawlButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				crawlHandler = new CrawlHandler(
						toCrawlUrlStringProperty.get(), 
						Objects.requireNonNullElse(
								Integer.parseInt(threadsAmountStringProperty.get()),
								40
						),
						depthInputRow.getCrawlDepth()
				);		
				crawlHandler.start();
								
			}
			
		});
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}
	
	private void doBindings() {
		this.toCrawlUrlStringProperty.bind(targetUrlInputRow.getUrlInputFieldTextProperty());
		this.threadsAmountStringProperty.bind(threadsAmountRow.getThreadsAmountFieldTextProperty());
		this.validUrlStringProperty.bindBidirectional(this.toCrawlUrlStringProperty);
		
	}

	private void doValidityChecks() {
		this.validUrlStringProperty.addListener(urlString -> {
			StringProperty urlStringProperty  = ((SimpleStringProperty) urlString);
			Pattern validUrlPattern = Pattern.compile(""
					+ "/((([A-Za-z]{3,9}:(?:\\/\\/)?)(?:[-;:&=\\+\\$,\\w]+@)?[A-Za-z0-9.-]+"
					+ "|(?:www.|[-;:&=\\+\\$,\\w]+@)[A-Za-z0-9.-]+)((?:\\/[\\+~%\\/.\\w-_]*)"
					+ "?\\??(?:[-\\+=&;%@.\\w_]*)#?(?:[\\w]*))?)/");
			
			Matcher isValidUrlMatcher = validUrlPattern.matcher(urlStringProperty.get());
			if (isValidUrlMatcher.find()) {
				
			}
		});
	}
}
