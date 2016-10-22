package model.item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.Callable;

public class Item extends AbstractItem implements Callable <String> {

	final String word = "This listing has ended.";
	final String itemNum = "http://www.ebay.com/itm/";

	File missingItemsFile;

	StringBuilder missingItems;
	
	String fileName;
	String outputFileName;
	
	StringBuilder logString ;
	public Item(String fileName, String outputFileName) {
		this.fileName = fileName;
		this.outputFileName = outputFileName;
	}
	
	@Override
	public String call() throws Exception {
		// buffer for storing file contents in memory
		logString = new StringBuilder();
		//for saving to a File
		missingItems=new StringBuilder();
		// for reading one line
		String lineOut = null;
		
		try {
			BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
			
			// keep reading till readLine returns null
				while ((lineOut = buff.readLine()) != null) {
					found = false;
					
					String itemNo = lineOut;
					
					site = new URL(itemNum+itemNo);
					
					BufferedReader in = new BufferedReader(new InputStreamReader(site.openStream()));
					String line;
					StringBuilder htmlText=new StringBuilder();
					
				if(!site.equals(new URL(itemNum))){
					while((line=in.readLine())!=null)
						htmlText.append(line);	


					setFound(!(htmlText.toString().contains(word)));
					
					
					// keep appending last line read to buffer
					logString.append(lineOut+" - ");
					if(isFound()){
						logString.append("OK");
					}else{
						logString.append("Missing...");
						missingItems.append(lineOut+"\n");
					}
					logString.append("\n");
				}

					in.close();
					site = null;
				}
				buff.close();				
			} catch (IOException e) {}


		return logString.toString();
	}

	public void save(String outputFileName) throws Exception{
		missingItemsFile = new File(outputFileName);
		FileOutputStream outMissingFile = new FileOutputStream(missingItemsFile);

		// if file doesnt exists, then create it
		if (!missingItemsFile.exists()) {
			missingItemsFile.createNewFile();
		}
		byte[] itemNumbersInBytes = missingItems.toString().getBytes();
	
		outMissingFile.write(itemNumbersInBytes);
		outMissingFile.flush();
		outMissingFile.close();
		
	}
	}


