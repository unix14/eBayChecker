package boot;

import java.io.IOException;

import view.StartWindow;

public class Run {

	public static void main(String[] args) throws IOException{
		try {
			new StartWindow("eBay Checker",350,350).run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
