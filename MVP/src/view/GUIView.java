package view;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Command;
import view.gui.StartWindow;


/**
 * GUIView extends ObservableView and implements View and Observer.<br>
 * Responsible for the GUI view. it notify the presenter
 * and get notified from the GUI.
 *
 */
public class GUIView extends ObservableView implements View, Observer  {
	private StartWindow sw;
	
	public GUIView(HashMap<String, Command> hmap, StartWindow sw) {
		super(hmap); // initializes the hash map in ObservableView
		this.sw = sw;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		//gets the command from GUI and notify Presenter.
		line = ((String) arg1).split(" ");
		command = hmap.get(line[0]);
		setChanged();
		notifyObservers(command);
		
	}

	@Override
	public void displayMaze(Maze3d maze) {
		sw.setMaze(maze);
	}

	@Override
	public void displayDir(String[] string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayHint(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayCrossSecion(int[][] maze2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySize(int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySolution(Solution<Position> solution) {
		sw.setSolution(solution);
		
	}

}
