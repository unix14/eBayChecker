package algorithms.mazeGenerators;

import java.util.ArrayList;

public class GrowingTreeGenerator extends AbstractGenerator {

	@Override
	public Maze3d generate(MazeArgInit mazeArgs) {
		Maze3d newMaze = new Maze3d(mazeArgs);
		int x= mazeArgs.getColumns();
		int y= mazeArgs.getDimension();
		int z= mazeArgs.getRows();

		ArrayList <Cell> newlist =new ArrayList<Cell>();
		
		
		/*Initializing the array values to 1*/
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				for(int h=0;h<z;h++){
					Position pos = new Position(i,j,h);
					newMaze.insertValue(pos, 1);
				}
			}
		}
		newMaze.setStart(newMaze.getRandomCell());
		newMaze.setExit(newMaze.getRandomCell());
		
		for(int i=0;i<newMaze.getXlength();i++){
			for(int k=0;k<newMaze.getYlength();k++){
				for(int l=0;l<newMaze.getZlength();l++){

					Cell randPosition = new Cell(newMaze.getRandomCell());
					newlist.add(randPosition);
					newMaze.insertValue(randPosition, 0);
					while(randPosition.hasUnvisitedNeighbors()){
						Cell newNeighbor = randPosition.getRandomNeighbor();
						randPosition.addNeighbor(newNeighbor);
						newlist.add(newNeighbor);
						newMaze.insertValue(newNeighbor, 0);
					}
					newlist.remove(randPosition);
				}
			}
		}
		
		return newMaze;
	}


}
