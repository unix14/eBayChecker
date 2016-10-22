package boot;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import controller.MyController;
import model.MyModel;
import view.StartWindow;

public class MyTest {
	MyModel model;
	StartWindow view;
	MyController controller;
	
	@Test
	public void test() {
		try {
			System.out.println("eBay Checker Item Test\n================\n");
			System.out.println("Creating Model\n");
			model = new MyModel();
			System.out.println("Creating View\n");
			view = new StartWindow("", 350, 350);
			System.out.println("Creating Controller\n");
			controller = new MyController(view, model);
			
			System.out.println("Managing Controller Connections\n");
			model.setController(controller);
			view.setController(controller);
			//view.start();
			
			System.out.println("Controller -> Work\n");
			controller.work("itemlist.txt");
			
			System.out.println("Controller -> Save to File\n");
			controller.save("outFile.txt");
			
			System.out.println("Controller -> Close\n");
			controller.close();
			
			System.out.println("test successfully passed");
			//view.infoLabel.setText("test successfully passed");
			
			
		} catch (IOException e) {
			fail("Test Failed");
			e.printStackTrace();
			//view.infoLabel.setText("test failed");
			System.out.println("test failed");
		}
		System.out.println("\n================\n\n\n\nThis Project Made By Unix14@gmail.com\n\nCheck My stuff at github.com/unix14/");
		
	}

}
