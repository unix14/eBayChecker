package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class MyMaze3dGenerator extends AbstractGenerator {

	@Override
	public Maze3d generate(MazeArgInit initial) {

		// Randomized Prim's algorithm
		Maze3d maze = new Maze3d(initial);
		Random rand = new Random();
		Position p = null;
		ArrayList<Position> list = new ArrayList<Position>();
		ArrayList<Position> blackList = new ArrayList<Position>();

		int x=initial.getColumns();
		int y=initial.getRows();
		int z=initial.getDimension();
		/* initializing the maze's values to 1's */
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				for (int h = 0; h < z; h++) {
					Position temp = new Position(i, j, h);
					maze.insertValue(temp, 1);
				}
			}
		}

		int randomX = rand.nextInt(x - 2) + 1; // random position on x-axis
		int randomY = rand.nextInt(y - 2) + 1; // random position in y-axis
		int randomZ = rand.nextInt(z - 2) + 1; // random position in z-axis
		int randomWall = rand.nextInt(6); // chooses which wall will be the
											// enter point
		switch (randomWall) {
		case 0:
			randomX = 0;
			p=new Position(randomX, randomY, randomZ);
			maze.insertValue(p, 0); // enter point to
									// the maze
			maze.setStart(p);
			randomX++;
			p.setX(randomX);
			maze.insertValue(p, 0); // first step into
									// the maze
			break;
		case 1:
			randomY = 0;
			p=new Position(randomX, randomY, randomZ);
			maze.insertValue(p, 0);
			maze.setStart(p);
			randomY++;
			p.setY(randomY);
			maze.insertValue(p, 0);
			break;
		case 2:
			randomZ = 0;
			p=new Position(randomX, randomY, randomZ);
			maze.insertValue(p, 0);
			maze.setStart(p);
			randomZ++;
			p.setZ(randomZ);
			maze.insertValue(p, 0);
			break;
		case 3:
			randomZ = maze.getZlength() - 1;
			p=new Position(randomX, randomY, randomZ);
			maze.insertValue(p, 0);
			maze.setStart(p);
			randomZ--;
			p.setZ(randomZ);
			maze.insertValue(p, 0);
			break;
		case 4:
			randomY = maze.getYlength() - 1;
			p=new Position(randomX, randomY, randomZ);

			maze.insertValue(p, 0);
			maze.setStart(p);
			randomY--;
			p.setY(randomY);
			maze.insertValue(p, 0);
			break;
		case 5:
			randomX = maze.getXlength() - 1;
			p=new Position(randomX, randomY, randomZ);
			maze.insertValue(p, 0);
			maze.setStart(p);
			randomX--;
			p.setX(randomX);
			maze.insertValue(p, 0);
			break;
		}

		// maze creation starts here

		do {
			ArrayList<Position> walls = maze.getPossibledirections((Position)p);
			for (int i = 0; i < 6; i++) {
				if ((Position)walls.get(i) != null)
					if (list.contains(walls.get(i)) == true) {
						list.remove(walls.get(i));
						blackList.add(walls.get(i));
					} else if (blackList.contains(walls.get(i)) == false)
						list.add(walls.get(i));

			}

			if (list.size() > 0) {
				randomWall = rand.nextInt(list.size());
				p = list.get(randomWall);
				list.remove(randomWall);
				Position newPos = new Position(p.getX(), p.getY(), p.getZ());
				maze.insertValue(newPos, 0);
			}

		} while (list.isEmpty() == false);

		randomX = rand.nextInt(x - 2) + 1; // random position on x-axis
		randomY = rand.nextInt(y - 2) + 1; // random position in y-axis
		randomZ = rand.nextInt(z - 2) + 1; // random position in z-axis
		randomWall = rand.nextInt(6); // chooses which wall will be the end
										// point
		Position currPos = null;
		switch (randomWall) {
		case 0:
			randomX = 0;
			currPos = new Position(randomX, randomY, randomZ);
			maze.insertValue(currPos, 0); // enter point to
								// the maze
			maze.setExit(currPos);
			randomX++;
			currPos.setX(randomX);
			maze.insertValue(currPos, 0); // first step into
															// the maze
			break;
		case 1:
			randomY = 0;
			currPos = new Position(randomX, randomY, randomZ);
			maze.insertValue(currPos, 0);
			maze.setExit(currPos);
			randomY++;
			currPos.setY(randomY);
			maze.insertValue(currPos, 0);
			break;
		case 2:
			randomZ = 0;
			currPos = new Position(randomX, randomY, randomZ);
			maze.insertValue(currPos, 0);
			maze.setExit(currPos);
			randomZ++;
			currPos.setZ(randomZ);
			maze.insertValue(currPos, 0);
			break;
		case 3:
			randomZ = maze.getZlength() - 1;
			currPos = new Position(randomX, randomY, randomZ);
			maze.insertValue(currPos, 0);
			maze.setExit(currPos);
			randomZ--;
			currPos.setZ(randomZ);
			maze.insertValue(currPos, 0);
			break;
		case 4:
			randomY = maze.getYlength() - 1;
			currPos = new Position(randomX, randomY, randomZ);
			maze.insertValue(currPos, 0);
			maze.setExit(currPos);
			randomY--;
			currPos.setY(randomY);
			maze.insertValue(currPos, 0);
			break;
		case 5:
			randomX = maze.getXlength() - 1;
			currPos = new Position(randomX, randomY, randomZ);
			maze.insertValue(currPos, 0);
			maze.setExit(currPos);
			randomX--;
			currPos.setX(randomX);
			maze.insertValue(currPos, 0);
			break;
		}

		return maze;
	}

}