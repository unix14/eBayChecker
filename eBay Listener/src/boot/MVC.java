package boot;

import java.io.IOException;

import controller.MyController;
import model.MyModel;
import view.StartWindow;

public class MVC {

	public static void main(String[] args) throws IOException {
		MyModel model = new MyModel();
		StartWindow view = new StartWindow("eBay Cheker", 350, 350);
		MyController controller = new MyController(view, model);
		
		model.setController(controller);
		view.setController(controller);
		
		view.start();
		
		controller.close();
	}

}
