package controller;

import model.Model;
import view.View;

public class MyController implements Controller{

	View view;
	Model model;
	
	public MyController(View view ,Model model) {
		this.view = view;
		this.model = model;
	}
	@Override
	public void work(String fileName) {
		model.work(fileName);
	}

	@Override
	public void display(String fileName) {
		view.display(fileName);
		
	}
	@Override
	public void save(String outputfileName) {
		model.save(outputfileName);
		
	}
	@Override
	public void close() {
		model.close();
		
	}
	
}
