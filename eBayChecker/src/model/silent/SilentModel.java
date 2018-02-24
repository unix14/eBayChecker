package model.silent;

import controller.Controller;
import model.Model;

public class SilentModel implements Model {
	public SilentListener eBay;			//MyModel
	Controller controller;
	
	String missingItems;
	public void setController(Controller controller){
		this.controller = controller;
	}
	
	
	@Override
	public void work(String fileName) {
		eBay = new SilentListener();
		try {
			missingItems = eBay.doTask(fileName);
			controller.display(missingItems);
		} catch (Exception e) {}

	}
	@Override
	public void work(String fileName, int numOfThreads) {
//		eBay = new SilentListener();
//		try {
//			missingItems = eBay.doTask(fileName);
//			controller.display(missingItems);
//		} catch (Exception e) {}
		new Exception("Unused Method in model.silent.SilentModel");
//		throw e;
		
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
	public Boolean itemMissing() {
		return eBay.isItemsMissing();
	}



}
