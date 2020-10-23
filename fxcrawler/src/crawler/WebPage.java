package crawler;

import crawler.WebSite;

import org.jsoup.nodes.*;
import org.jsoup.select.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.jsoup.*;
public class WebPage implements Callable<WebPages> {
	
	private String myUrl;
	private static final Logger LOGGER =
			Logger.getLogger(WebPage.class.getName());
	
	public WebPage(String myUrl) {
		
		this.myUrl = myUrl;
	}

	@Override
	public WebPages call() throws Exception {
		LOGGER.log(Level.INFO, "Entered Thread: " + Thread.currentThread().toString() + " for url [" + myUrl + "].");
		WebPages myPages;
		
			Document myDocument = Jsoup.connect(myUrl).get();
			Elements elements = myDocument.select("a[href]");
			ArrayList<String> webPagesArrayList = (ArrayList<String>) elements
																		.stream()
																		.map(element -> element.absUrl("href"))
																		.collect(Collectors.toList());
			myPages = new WebPages(myUrl, webPagesArrayList);
		
		
		return Objects.requireNonNull(myPages);
	}
	
	
	
}
