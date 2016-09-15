package controller;

import java.util.Observable;
import java.util.Observer;

import javax.swing.text.View;

import model.Model;

public class Controller implements Observer{
	private Model m;
	private View v;
	private Command command;
	
	public Controller(Model m, View v) {
		this.m = m;
		this.v = v;

}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
