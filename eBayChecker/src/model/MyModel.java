package model;

import controller.Controller;

public class MyModel implements Model {
	EbayListener eBay;			//MyModel
	Controller controller;
	
	public void setController(Controller controller){
		this.controller = controller;
	}
	
	//for Cached Thread Factory
	@Override
	public void work(String fileName) {
		eBay = new EbayListener();
		try {
			String ret = eBay.doTask(fileName);
			controller.display(ret);
		} catch (Exception e) {}
	}
	//for Fixed Thread Factory
	@Override
	public void work(String fileName,int numOfThreads) {
		
		eBay = new EbayListener(numOfThreads);
		try {
			String ret = eBay.doTask(fileName);
			controller.display(ret);
		} catch (Exception e) {}
	}
	@Override
	public void save(String outputfileName) {
		try {
			if(eBay!=null)
				eBay.saveToFile(outputfileName);
		} catch (Exception e) {}
		
	}
	
	@Override
	public void close() {
		try {
			if(eBay!=null)
				eBay.closeListener();
		} catch (Exception e) {}
		
	}
	
	@Override
	public Boolean itemMissing(){
		return eBay.isItemsMissing();
	}

}
