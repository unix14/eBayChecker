package model;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import model.item.Item;

public class EbayListener {
	Item newItem;
	
	final Integer maxThreadCapacity = 4 ;
	static ExecutorService threadPool;
	Callable<String> callable;
	
	
	public EbayListener() {
		threadPool = Executors.newFixedThreadPool(maxThreadCapacity);
	}
	
	public String doTask(String fileName) throws Exception{
		newItem = new Item(fileName, fileName);
		callable= newItem;

		Future<String> future = threadPool.submit(callable);

		return future.get();
	}
	
	
	public void saveToFile(String outputFileName){
		try {
			newItem.save(outputFileName);
		} catch (Exception e) {}
	}
	
	public void closeListener(){
		threadPool.shutdown();
	}


}
