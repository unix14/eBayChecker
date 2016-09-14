package algorithms.demo;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MazeArgInit;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.searchables.SearchableMaze;
import algorithms.search.searchers.BFS;
import algorithms.search.searchers.DFS;
/**
 * 
 *this class is used to create a demonstration of the search algorithms. 
 *
 */
public class Demo {
	/**
	 * method run() generates and print a maze. It also prints the solution to the maze
	 * using the different search algorithms.
	 */
	public void run(){
		Maze3dGenerator mg = new MyMaze3dGenerator();
		MazeArgInit initial = new MazeArgInit(20,20, 5);
		Maze3d maze=mg.generate(initial);
		maze.print();			//print Maze
		
		Solution<Position> s1, s2;
		System.out.println("BFS:");
		BFS<Position> bfs=new BFS<Position>();
		s1=bfs.search(new SearchableMaze(maze));
		
		System.out.println(s1);
		System.out.println(bfs.getNumberOfNodesEvaluated());
		System.out.println("DFS:");
		
		DFS<Position> dfs=new DFS<Position>();
		s2=dfs.search(new SearchableMaze(maze));
		System.out.println(s2);
		System.out.println(dfs.getNumberOfNodesEvaluated());

	}
	
	
}