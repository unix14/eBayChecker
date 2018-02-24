package model.item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;

public class Item extends AbstractItem implements Callable <Boolean> {	

	final String word = "This listing has ended.";
	final String webPath = "http://www.ebay.com/itm/";

	File missingItemsFile;

	String outputFileName;

	
	public Item(String itemNumber) {
		try {
			setItemNumber(itemNumber);
			setFound(false);
			setSite(new URL(webPath+itemNumber));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		
}
	@Override
	public Boolean call() throws Exception {

			site = new URL(webPath+itemNumber);
		
			BufferedReader in = new BufferedReader(new InputStreamReader(site.openStream()));
			String line;
			StringBuilder htmlText=new StringBuilder();
					
			if(!site.equals(new URL(webPath))){
				while((line=in.readLine())!=null)
					htmlText.append(line);	
				setFound(!(htmlText.toString().contains(word)));
					
				if(isFound()){
					return true;
				}
			}
			in.close();
	return false;
	}

}


