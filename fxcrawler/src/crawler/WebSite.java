package crawler;

import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.*;
import java.util.regex.*;
import java.sql.*;
import org.sqlite.jdbc4.JDBC4PreparedStatement;
import java.util.concurrent.CopyOnWriteArrayList;

public class WebSite {
	
	private static final Logger LOGGER = 
			Logger.getLogger(WebSite.class.getName());
	private static WebSite instance;
	private volatile CopyOnWriteArrayList<String> proccessedArrayList = new CopyOnWriteArrayList<>();
	
	private WebSite() {			
		}
		
	
	
	public static synchronized WebSite getInstance() {
		if (Objects.isNull(instance)) {
			return new WebSite();
		}
		else return instance;
	}
	
	public synchronized void addProccessedSite(String url) {
		LOGGER.log(Level.INFO, "Adding [" + url + "] To proccesed sites");
		this.proccessedArrayList.add(url);
	}
	
	
	public synchronized boolean hasBeenProccessed(String url) {
		
		Predicate<CopyOnWriteArrayList<String>> exists = list -> list.stream().filter(element -> element.equals(url)).count() > 0;
		LOGGER.log(Level.INFO, "Checking [" + url + "] in proccesed sites " + (exists.test(proccessedArrayList) ? "true" : "false"));
		return exists.test(this.proccessedArrayList);
		
	}

}
