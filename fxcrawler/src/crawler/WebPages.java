package crawler;

import java.util.ArrayList;

public class WebPages {

	private String myUrlString;
	private ArrayList<String> myLinksList = new ArrayList<>();
	
	public WebPages(String myUrlString, ArrayList<String> myLinksList) {
		this.myUrlString = myUrlString;
		this.myLinksList = myLinksList;
	}

	@Override
	public String toString() {
		return "WebPages [myUrlString=" + myUrlString + ", myLinksList=" + myLinksList.toString() + "]";
	}
	

}
