package model;

import controller.Controller;

public class MyModel implements Model {
	EbayListener eBay;			//MyModel
	Controller controller;
	
	public void setController(Controller controller){
		this.controller = controller;
	}
	
	@Override
	public void work(String fileName) {
		eBay = new EbayListener();
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

}
