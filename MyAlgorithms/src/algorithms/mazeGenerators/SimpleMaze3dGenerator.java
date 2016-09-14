package algorithms.mazeGenerators;
import java.util.Random;

public class SimpleMaze3dGenerator extends AbstractGenerator {

	@Override
	public Maze3d generate(MazeArgInit mazeArgs) {
		Maze3d maze = new Maze3d(mazeArgs);
		int x= mazeArgs.getColumns();
		int y= mazeArgs.getDimension();
		int z= mazeArgs.getRows();
		Random rand=new Random();
		/*initializing the array's values to 1's*/
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				for(int h=0;h<z;h++){
					Position pos = new Position(i,j,h);
					maze.insertValue(pos, 1);
				}
			}
		}
		
		int randomX = rand.nextInt(x-2)+1; //random position on x-axis
		int randomY = rand.nextInt(y-2)+1; //random position in y-axis
		int randomZ = rand.nextInt(z-2)+1; //random position in z-axis
		int randomWall = rand.nextInt(6); //chooses which wall will be the enter point
		int errorMove = 0; //next move that cannot be performed (so the route generator won't go back)
		
		Position pos = new Position(randomX,randomY,randomZ);		//current Position
		switch (randomWall){
		case 0: 
			randomX = 0;
			pos.setX(randomX);
			maze.insertValue(pos , 0); //enter point to the maze
			maze.setStart(pos);
			randomX++;
			pos.setX(randomX);
			maze.insertValue(pos , 0); //first step into the maze
			errorMove = 5;
			break;
		case 1: 
			randomY = 0;
			pos.setY(randomY);
			maze.insertValue(pos, 0);
			maze.setStart(pos);
			randomY++;
			pos.setY(randomY);
			maze.insertValue(pos , 0);
			errorMove = 4;
			break;
		case 2: 
			randomZ = 0;
			pos.setZ(randomZ);
			maze.insertValue(pos , 0);
			maze.setStart(pos);
			randomZ++;
			pos.setZ(randomZ);
			maze.insertValue(pos, 0);
			errorMove = 3;
			break;
		case 3: 
			randomZ = maze.getZlength()-1;
			pos.setZ(randomZ);
			maze.insertValue(pos , 0);
			maze.setStart(pos);
			randomZ--;
			pos.setZ(randomZ);
			maze.insertValue(pos , 0);
			errorMove = 2;
			break;
		case 4: 
			randomY = maze.getYlength()-1;
			pos.setY(randomY);
			maze.insertValue(pos , 0);
			maze.setStart(pos);
			randomY--;
			pos.setY(randomY);
			maze.insertValue(pos , 0);
			errorMove = 1;
			break;
		case 5: 
			randomX = maze.getXlength()-1;
			pos.setX(randomX);
			maze.insertValue(pos , 0);
			maze.setStart(pos);
			randomX--;
			pos.setX(randomX);
			maze.insertValue(pos , 0);
			errorMove = 0;
			break;
		}
		
		/*while stops when the route generator reaches one of the cube's sides*/
		while (randomX != x-1 && randomY != y-1 && randomZ != z-1 && randomX != 0 && randomY != 0 && randomZ != 0){
			int randomMove = rand.nextInt(6); //move possible in the maze: 0-right, 1-up, 2-forward, 3-backwards, 4-down, 5-left.
			if (randomMove != errorMove) {
				switch (randomMove) {
				case 0:
					randomX++;
					pos.setX(randomX);
					maze.insertValue(pos, 0);
					errorMove = 5;
					break;
				case 1:
					randomY++;
					pos.setY(randomX);
					maze.insertValue(pos, 0);
					errorMove = 4;
					break;
				case 2:
					randomZ++;
					pos.setZ(randomZ);
					maze.insertValue(pos, 0);
					errorMove = 3;
					break;
				case 3:
					randomZ--;
					pos.setZ(randomZ);
					maze.insertValue(pos, 0);
					errorMove = 2;
					break;
				case 4:
					randomY--;
					pos.setY(randomZ);
					maze.insertValue(pos, 0);
					errorMove = 1;
					break;
				case 5:
					randomX--;
					pos.setX(randomZ);
					maze.insertValue(pos, 0);
					errorMove = 0;
					break;
				}
			} 
		} 
		
		maze.setExit(pos);
		
		/*generating random 1's or 0's in every empty cell in the maze*/
		for(int i=1;i<x-1;i++){
			for(int j=1;j<y-1;j++){
				for(int h=1;h<z-1;h++){
					Position currPos = new Position(i,j,h);
					if(maze.getValue(currPos)!=0)
						maze.insertValue(currPos, rand.nextInt(2));
				}
			}
		}
		
		
			
		return maze;
	}
}
