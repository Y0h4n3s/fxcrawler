package crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlHandler {

	private static final Logger LOGGER =
			Logger.getLogger(CrawlHandler.class.getName());
	
	private String url;
	private Integer threads;
	private Integer depth;
	
	public CrawlHandler(String url, Integer threads, Integer depth) {
		this.url = url;
		this.threads = threads;
		this.depth = depth;
	}
	
	public void start() {
			
		WebPage startingPage = new WebPage(url, 0, depth);
		
		
		
		SiteGrabber grabber = new SiteGrabber(startingPage, this.threads);
		Thread grabberThread = new Thread(grabber);
		grabberThread.start();
	}
	
	

}
