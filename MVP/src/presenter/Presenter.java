package presenter;

import presenter.Command;

import java.util.Observable;
import java.util.concurrent.ExecutionException;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.Model;
import view.ObservableView;

public class Presenter {
	private ObservableView ui;
	private Model model;
	private Command command;
	public Presenter(Model m, ObservableView v) {
		this.model = m;
		this.ui = v;

}

	public void update(Observable o, Object arg) {
		if (o == ui) {			//if received notification from View

			command = (Command) arg;
			command.doCommand(ui.getUserCommand());

		}
		if (o == model) {//notification came from model
			String hint = (String) arg;
			if (hint.equals("done dir"))
				ui.displayDir((String[]) model.getData());
			else if (hint.equals("done generate maze"))
				ui.displayHint(hint);
			else if (hint.equals("display this"))
				try {
					ui.displayMaze((Maze3d) model.getFutureMaze());
				} catch (InterruptedException | ExecutionException e) {
				}
			else if (hint.equals("display"))
				ui.displayMaze((Maze3d) model.getMaze());
			else if (hint.equals("display cross section"))
				ui.displayCrossSecion((int[][]) model.getData());
			else if (hint.equals("maze is saved"))
				ui.displayHint(hint);
			else if (hint.equals("maze is loaded"))
				ui.displayHint(hint);
			else if (hint.equals("maze size"))
				ui.displaySize((int) model.getData());
			else if (hint.equals("file size"))
				ui.displaySize((int) model.getData());
			else if (hint.equals("maze is solved")) {
				ui.displayHint(hint);
			} else if (hint.equals("display solution"))
				ui.displaySolution((Solution<Position>) model.getSolution());
			else if (hint.equals("display solution this"))
				try {
					ui.displaySolution((Solution<Position>) model.getFutureSolution());
				} catch (InterruptedException | ExecutionException e) {
				}

			else if (hint.equals("solutions are saved"))
				ui.displayHint(hint);

		}
}
}
