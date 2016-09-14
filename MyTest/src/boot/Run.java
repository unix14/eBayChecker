package boot;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MazeArgInit;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;

public class Run {
	private static void testMazeGenerator(Maze3dGenerator mg){
		MazeArgInit initialTest = new MazeArgInit(5, 5, 5);
		// prints the time it takes the algorithm to run
		System.out.println(mg.measureAlgorithmTime(initialTest));
		// generate another 3d maze
		Maze3d maze=mg.generate(initialTest);
		maze.print();
		// get the maze entrance
		Position p=maze.getStartPosition();
		// print the position
		System.out.println(p); // format "{x,y,z}"
		// get all the possible moves from a position
		//String[] moves=maze.getPossibledirections(p);
		ArrayList <Position> moves = maze.getPossibledirections(p);
		// print the moves
		for(Position move : moves)
			System.out.println(move.toString());
			
		// prints the maze exit position
		System.out.println(maze.getGoalPosition());
		try{
			// get 2d cross sections of the 3d maze
			System.out.println("Cross Section By X");
			int[][] maze2dx=maze.getCrossSectionByX(2);
			// TODO add code to print the array
			maze.printCrossSection(maze2dx);
			System.out.println("Cross Section By Y");			
			int[][] maze2dy=maze.getCrossSectionByY(1);
			// TODO add code to print the array
			maze.printCrossSection(maze2dy);
			System.out.println("Cross Section By Z");
			int[][] maze2dz=maze.getCrossSectionByZ(3);
			// TODO add code to print the array
			maze.printCrossSection(maze2dz);
			//Maze3d positionTest = new Maze3d(maze2dx);
			
			// this should throw an exception!
			maze.getCrossSectionByX(-1);
		}catch (IndexOutOfBoundsException e){
			System.out.println("IndexOutOfBoundsException");
		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		testMazeGenerator(new SimpleMaze3dGenerator());
		//testMazeGenerator(new GrowingTreeGenerator());
	}

}
