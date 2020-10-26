package crawler;



import java.io.IOException;
import java.lang.invoke.VolatileCallSite;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.print.attribute.standard.Copies;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

//grab and index files for download
public class SiteGrabber implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(SiteGrabber.class.getName());

	private static CopyOnWriteArrayList<WebPage> pagesCollected = new CopyOnWriteArrayList<>();
	private WebPage startPage;
	private Integer threads;
	private ExecutorService executor;
	private final CompletionService<List<WebPage>> completionService;
	public SiteGrabber(WebPage startPage, Integer threads) {
		this.startPage = startPage;
		this.threads = threads;
		executor = Executors.newFixedThreadPool(1); 
		SiteGrabber.pagesCollected.add(startPage);
		completionService = new ExecutorCompletionService<>(executor);
	}

	@Override
	public void run()  {
		int maxLevel = 0;
		
		while (maxLevel < startPage.getMaxLevel()) {
			for (WebPage page : SiteGrabber.pagesCollected) {
				if (page.getLevel() > maxLevel)
					maxLevel = page.getLevel();
			}
			if (maxLevel >= startPage.getMaxLevel())break;
			List<WebPage> pagesToGet = this.getLevelCopy(maxLevel);
			System.out.println("maxxxxxxxxxxxx:  " + maxLevel + "  " + startPage.getMaxLevel());
			for (WebPage page : pagesToGet) {
					
						//System.out.println(page);
						Callable<List<WebPage>> pagesCallable = 
								new WebPageProcessor(page, threads);
						completionService.submit(pagesCallable);
						
			}
			Future<List<WebPage>> futureList;
			
			try {
				executor.awaitTermination(10, TimeUnit.SECONDS);
			while ((futureList = completionService.poll()) != null) {
				
					addPages(futureList.get());
				
			}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			System.out.println(SiteGrabber.pagesCollected);
		}
		System.out.println("we are offfff");
		System.out.println(SiteGrabber.pagesCollected);
		System.out.println("size:  " + SiteGrabber.pagesCollected.size());
	}
	
	private synchronized List<WebPage> getLevelCopy(int level) {
		List<WebPage> copiedList = SiteGrabber.pagesCollected.stream().filter(element -> element.getLevel() == level).collect(Collectors.toList());
		return copiedList;
	}

	/*
	 * private void startCrawlingLevel(WebPage urls, Integer crawlDepth) { if
	 * (this.crawlDepth > 0) {
	 * 
	 * urls.getMyLinksList().stream().forEach(WebPage -> { LOGGER.log(Level.INFO,
	 * "Going for " + WebPage.toString() + "  " + this.crawlDepth);
	 * Callable<List<WebPage>> collectPagesCallable = new WebPageProcessor(WebPage);
	 * Future<List<WebPage>> toProccessFuture =
	 * executorService.submit(collectPagesCallable); });
	 * 
	 * 
	 * 
	 * 
	 * 
	 * try {
	 * 
	 * List<WebPage> processed = toProccessFuture.get(); this.addPages(processed);
	 * 
	 * 
	 * 
	 * } catch (InterruptedException | ExecutionException e) { e.printStackTrace();
	 * } }
	 * 
	 * }
	 *//**
		 * add the given web page to the list of already processed pages
		 * 
		 * @param pages A WebPage object to add to the list
		 */
	
	private synchronized void addPages(List<WebPage> pages) {
		 for (WebPage page : pages) {
			 if (SiteGrabber.pagesCollected.contains(page)) {
				 continue;
			 }
			 SiteGrabber.pagesCollected.add(page);
		 }
	}
	 //**
		 /* check if the url has already been processed
		 * 
		 * @param url the web address to check
		 * @return true if it has been already processed false otherwise.
		 * 
		 *//*
			 * private synchronized boolean isProccessed(String url) { return
			 * !SiteGrabber.pagesCollected.stream() .filter(page ->
			 * page.getMyUrlString().equals(url)) .collect(Collectors.toList()).isEmpty();
			 * 
			 * 
			 * } //TODO implement a domain range checker. private synchronized boolean
			 * isInScope(String url) {
			 * 
			 * 
			 * 
			 * return true; }
			 */

}
