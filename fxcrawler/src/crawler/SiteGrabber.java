package crawler;

import java.util.ArrayList;
import java.util.concurrent.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class SiteGrabber {
	
	private static final Logger LOGGER =
			Logger.getLogger(SiteGrabber.class.getName());

	private String theSite;
	public SiteGrabber(String theSite) {
		this.theSite = theSite;
		this.startCrawling();
	}

	
	
	private void startCrawling() {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		ArrayList<Future<WebPages>> toProccessFutures = 
				new ArrayList<>();
		
		Callable<WebPages> collectPagesCallable = 
				new WebPage(this.theSite);
		
		toProccessFutures.add(executorService.submit(collectPagesCallable));
		
		try {
			System.out.println(toProccessFutures.get(0).get().toString());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}

