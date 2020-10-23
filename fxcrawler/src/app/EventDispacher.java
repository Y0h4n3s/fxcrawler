package app;

import javafx.event.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import ui.*;
import javafx.scene.control.Button;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.*;
import crawler.SiteGrabber;

public class EventDispacher {
	
	private static final Logger LOGGER =
				Logger.getLogger(EventDispacher.class.getName());
	
	private Button goCrawlButton;
	private MainFrame mainFrame;
	private StringProperty toCrawlUrlStringProperty = new SimpleStringProperty();
	private SiteGrabber siteGrabber;
	
	public EventDispacher() {		
		TargetUrlInputRow targetUrlInputRow = new TargetUrlInputRow();
		
		this.goCrawlButton = targetUrlInputRow.getStartCrawlButton();
		this.addGoCrawlListeners();
		this.toCrawlUrlStringProperty.bind(targetUrlInputRow.getUrlInputFieldTextProperty());
		this.mainFrame = new MainFrame(targetUrlInputRow);
	}
	
	private void addGoCrawlListeners() {
		this.goCrawlButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				siteGrabber = new SiteGrabber(toCrawlUrlStringProperty.get());
				
			}
			
		});
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

}
