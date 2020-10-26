package crawler;

import crawler.WebSite;


import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


import org.jsoup.*;
public class WebPageProcessor implements Callable<List<WebPage>> {
	
	private WebPage myPage;
	
	private Integer threadsAmount;
	private ExecutorService executorService;
	private List<WebPage> myPages = new ArrayList<>();
	private static final Logger LOGGER =
			Logger.getLogger(WebPageProcessor.class.getName());

	public WebPageProcessor(WebPage myPage,  Integer threadsAmount) {
		
		this.myPage = myPage;
		
		this.threadsAmount = threadsAmount;
		this.executorService = Executors.newFixedThreadPool(threadsAmount);
	}

	@Override
	public List<WebPage> call()  {
		
		LOGGER.log(Level.INFO, "Entered Thread: " + Thread.currentThread().toString() + " for url [" + myPage + "].");
		if (myPage.getMyLinksList().isEmpty())
			this.processSelf();
		this.myPage.getMyLinksList().stream().forEach(link ->  {
			if (!link.getDownloadSuccess()) {
				executorService.execute(new LinkExtractor(link));
			}
		});
		try {
			if(executorService.awaitTermination(20, TimeUnit.SECONDS))
				System.out.println("success");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return Objects.requireNonNull(myPages);
	}
	
	private void processSelf() {
		System.out.println("processing: " + myPage);
		Document myDocument;
		try {
			myDocument = Jsoup.connect(myPage.getMyUrlString()).get();
			Set<WebPage> duplicateFilterSet = new HashSet<>();
			Elements elements = myDocument.select("a[href]");
			ArrayList<WebPage> webPagesArrayList = 
					(ArrayList<WebPage>) elements
					.stream()
					.map(element -> new WebPage(
							element.absUrl("href"),
							element.attr("href"),
							myPage.getLevel() + 1,
							myPage.getMaxLevel()))
					.collect(Collectors.toList());
			for (int i = 0; i < webPagesArrayList.size(); i++) {
				if (webPagesArrayList.get(i).getMyUrlString().contains("#")) {
					webPagesArrayList.get(i).setMyUrlString(webPagesArrayList.get(i).getMyUrlString().split("#")[0]);						
				}
			}
			webPagesArrayList.stream().filter(i -> !duplicateFilterSet.add(i)).collect(Collectors.toList());
			myPage.setMyLinksList(webPagesArrayList);
			myPage.setDownloadSuccess(true);
		} catch (IOException e) {
			LOGGER.log(Level.INFO, e.getMessage() + "Error getting site: " + myPage);
			myPage.setDownloadSuccess(false);
		}
	}
	
	private synchronized void addPage(WebPage toAdd) {
		//System.out.println("adding: " + toAdd.toString());
		this.myPages.add(toAdd);
	}
	
	private class LinkExtractor implements Runnable {
		private WebPage myUrl;
		public LinkExtractor(WebPage myUrl) {
			this.myUrl = myUrl;
		}
		@Override
		public void run() {
			
			Document myDocument;
			try {
				myDocument = Jsoup.connect(myUrl.getMyUrlString()).get();
				
				Elements elements = myDocument.select("a[href]");
				Set<WebPage> duplicateFilterSet = new HashSet<>();
				ArrayList<WebPage> webPagesArrayList = 
						(ArrayList<WebPage>) elements
						.stream()
						.map(element -> new WebPage(
								element.absUrl("href"),
								element.attr("href"),
								myUrl.getLevel() + 1,
								myUrl.getMaxLevel()))
						.collect(Collectors.toList());
				for (int i = 0; i < webPagesArrayList.size(); i++) {
					if (webPagesArrayList.get(i).getMyUrlString().contains("#")) {
						webPagesArrayList.get(i).setMyUrlString(webPagesArrayList.get(i).getMyUrlString().split("#")[0]);						
					}
				}
				webPagesArrayList.stream().filter(i -> !duplicateFilterSet.add(i)).collect(Collectors.toList());
				myUrl.setMyLinksList(webPagesArrayList);
				myUrl.setDownloadSuccess(true);
			} catch (IOException e) {
				LOGGER.log(Level.INFO, e.getMessage() + "Error getting site: " + myUrl);
				myUrl.setDownloadSuccess(false);
			}
			addPage(myUrl);
			
		}
		
	}
	
	
	
}
