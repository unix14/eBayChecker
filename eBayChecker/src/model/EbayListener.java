package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import model.item.Item;

public class EbayListener {
	Item newItem;
	
	final Integer maxThreadCapacity = 4 ;
	protected static ExecutorService threadPool;
	Callable<String> callable;
	
	
	
	
	
	final String word = "This listing has ended.";
	final String itemNum = "http://www.ebay.com/itm/";

	File missingItemsFile;

	protected StringBuilder missingItems;
	
	String fileName;
	String outputFileName;
	
	StringBuilder logString ;
	
	protected String lineOut;
	
	List<Item> allItems;
	
	
	Item currentItem;
	
	Future<Boolean> future;
	
	public enum ThreadOptions{
		CACHED,
		FIXED 
		
	}
	public EbayListener() {
		threadPool = Executors.newCachedThreadPool();
		
		// buffer for storing file contents in memory
		logString = new StringBuilder();
		//for saving to a File
		missingItems=new StringBuilder();
		// for reading one line
		lineOut = null;
		
		allItems = new ArrayList<Item>();
		
	}

	public EbayListener(int threadCapacity) {
		threadPool = Executors.newFixedThreadPool(Math.min(maxThreadCapacity,threadCapacity));
		
		// buffer for storing file contents in memory
		logString = new StringBuilder();
		//for saving to a File
		missingItems=new StringBuilder();
		// for reading one line
		lineOut = null;
		
		allItems = new ArrayList<Item>();

}
	public String doTask(String fileName) throws Exception{
		BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
		StringBuilder retStringbuild = new StringBuilder();

				int i=0;
				// keep reading till readLine returns null
				try {
					while ((lineOut = buff.readLine()) != null) {
						i++;
						Future<Boolean> retString=null;
						
						Item currentItem = new Item(lineOut);
						retString = threadPool.submit(currentItem);
						
						retStringbuild.append(i+". "+lineOut+" - ");
						
						if(retString.get()){
							retStringbuild.append("Avialable");
						}else{
							retStringbuild.append("Sold...");
							missingItems.append(lineOut+"\n");
						}
						retStringbuild.append("\n");
						
					}
				} catch (IOException | InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				buff.close();
			return retStringbuild.toString();
}
	
	public Boolean isItemsMissing(){
		return missingItems.toString().equals(null)? false : true;
	}
	public void saveToFile(String outputFileName){
		try {
			
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
			
		} catch (Exception e) {}
	}
	
	public void closeListener(){
		threadPool.shutdown();
	}


}
