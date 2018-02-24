package boot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import controller.MyController;
import model.silent.SilentModel;
import view.BackgroundChecker;

public class SilentChecker {
	static SilentModel model;
	static BackgroundChecker view;
	static MyController controller;
	
	static File input;
	static File output;

	public static void main(String[] args) throws Exception {
		if(args.length==0|| args.length!= 2){
			Exception e = new IOException("No Input/Output Files specified");
			
			System.out.println(e.toString()+"\n\nTry 'Silent.exe itemList logFolderPath'");
			return;
		}
	    String inputFile = args[0]; 
	    String outputPath = args[1];
		
//	    String inputFile = "C:/Users/eyal/Java/eBayChecker/itemlist.txt"; 
//	    String outputPath = "C:/Users/eyal/Desktop/log/";
	    
	    input = new File(inputFile);
	    output = new File(outputPath);
	    
	    if(!input.exists()){
			Exception e =new FileNotFoundException("Items List not Found");
			System.out.println(e.toString()+"\nTry loading another file");
			throw e;
	    }
	    
		model = new SilentModel();
		view = new BackgroundChecker("eBay Background Checker", 350, 350);
		controller = new MyController(view, model);
		
		model.setController(controller);
		view.setController(controller);

		
		controller.work(inputFile);
		
		   
		
		if(model.itemMissing()){
			DateFormat dateFormat = new SimpleDateFormat("HHmmss");
			Date date = new Date();
			String nowTime = dateFormat.format(date);
			String OutputFileName = outputPath+"log_"+nowTime+".txt";
			

			//Notify User
			
			controller.save(OutputFileName);

			view.start();
				
			
		}
			
		controller.close();
		

	}

}
