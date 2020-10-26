package crawler;

import java.util.ArrayList;

public class WebPage {

	private String myUrlString;
	private String myLocalString;
	private ArrayList<WebPage> myLinksList = new ArrayList<>();
	private Integer level;
	private Integer maxLevel;
	private Boolean downloadSuccess = false;
	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return the myUrlString
	 */
	public String getMyUrlString() {
		return myUrlString;
	}

	/**
	 * @param myUrlString the myUrlString to set
	 */
	public void setMyUrlString(String myUrlString) {
		this.myUrlString = myUrlString;
	}

	/**
	 * @return the myLinksList
	 */
	public ArrayList<WebPage> getMyLinksList() {
		return myLinksList;
	}

	/**
	 * @param myLinksList the myLinksList to set
	 */
	public void setMyLinksList(ArrayList<WebPage> myLinksList) {
		this.myLinksList = myLinksList;
	}

	public WebPage(String myUrlString, String myLocalString, ArrayList<WebPage> myLinksList) {
		this.myUrlString = myUrlString;
		this.myLinksList = myLinksList;
		this.myLocalString = myLocalString;
	}
	
	public WebPage(String myUrlString, String myLocalString, Integer level, Integer maxLevel) {
		this.myUrlString = myUrlString;
		this.myLocalString = myLocalString;
		this.level = level;
		this.maxLevel = maxLevel;
		
	}
	public WebPage(String myUrlString, Integer level, Integer maxLevel) {
		this.myUrlString = myUrlString;
		this.level = level;
		this.maxLevel = maxLevel;
		
	}
	
	
	public WebPage(String myUrlString) {
		this.myUrlString = myUrlString;
	}

	@Override
	public String toString() {
		String string = "WebPages [myUrlString=" + myUrlString + " myLocalString=" + myLocalString + ",\nmyLinksList=[" ;
		for (WebPage link : myLinksList)
			string += (link.getMyUrlString() + ",\n");
		 
		string+= "] \n + Level= " + level + " \nDownloaded= " + downloadSuccess + "]";
		return string;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		WebPage webPageObj = (WebPage) obj;
		return webPageObj.getMyUrlString().equals(this.myUrlString);
	}

	public Integer getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(Integer maxLevel) {
		this.maxLevel = maxLevel;
	}

	public Boolean getDownloadSuccess() {
		return downloadSuccess;
	}

	public void setDownloadSuccess(Boolean downloadSuccess) {
		this.downloadSuccess = downloadSuccess;
	}
	

}
