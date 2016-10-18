package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Command;
/**
 * Responsible for the Command Line Interface view.<br>
 * CLIView extends ObservableView and implements View and Runnable Interfaces.<br>
 *  <u>View</u> - it notify the controller<br>
 * <u>Runnable</u> - it can run in a new thread.
 *
 */
public class CLIView extends ObservableView implements View, Runnable {

	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private PrintWriter out = new PrintWriter(System.out);

	public CLIView(HashMap<String, Command> map) {
		super(map);// initializes the hash map in ObservableView
	}
	/**
	* receiving commands from user.<br>
	* running when start() method is called from thread. 
	*/
	@Override
	public void run() {
		try {
			do {
				line = (in.readLine()).split(" ");
				command = hmap.get(line[0]);
				setChanged();
				notifyObservers(command);
			}while (!(line[0]).equals("exit"));

		} catch (IOException e) {
			out.println("Incorrect Input");
		}
	}

	@Override
	public void displayMaze(Maze3d maze) {
		for (int h = 0; h < maze.getZlength(); h++) {
			for (int j = 0; j < maze.getYlength(); j++) {
				for (int i = 0; i < maze.getXlength(); i++) {
					out.print(maze.getValue(i,j,h) + ", ");
				}
				out.print("\n");
			}
			out.println("----------------");
		}
		out.flush();
	}

	@Override
	public void displayDir(String[] string) {
		for (String s : string) {
			out.println(s);
		}
		out.flush();

	}

	/**
	 * <b>displayHint</b><br>
	 * <u>public void displayHint(String string)</u><br>
	 * receives a hint and prints it to the screen.<br>
	 * <i>used to inform the user about certain process.</i>
	 */
	public void displayHint(String string) {
		out.println(string);
		out.flush();

	}
	/**
	 * <b>displayCrossSecion</b><br>
	 * <u>	public void displayCrossSecion(int[][] crossSection)</u><br>
	 * Print out the 2D Maze given<br>
	 */
	@Override
	public void displayCrossSecion(int[][] crossSection) {

		for (int i = 0; i < crossSection[0].length; i++) {
			for (int j = 0; j < crossSection.length; j++) {
				out.print(crossSection[j][i] + ", ");
			}
			out.print("\n");
		}
		out.flush();

	}

	@Override
	public void displaySize(int size) {
		out.println(size + " bytes");
		out.flush();

	}

	@Override
	public void displaySolution(Solution<Position> solution) {
		out.println(solution);
		out.flush();

	}
}