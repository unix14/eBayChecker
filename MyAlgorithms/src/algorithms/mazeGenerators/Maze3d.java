package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@SuppressWarnings("serial")
public class Maze3d implements Serializable{
	//Data Members
	
	//	private int  maxColumns,maxRows, Dimension;
	//x			//y 	//z
	private MazeArgInit Initial;
	private Position start,exit;
	private int [][][] mazeCells;
	
	//Functions
	public Maze3d(byte[] byteArray){
							//X Length			Y Length			Z Length
		mazeCells=new int[(int)byteArray[1]][(int)byteArray[2]][(int)byteArray[3]];
		this.start.setX((int)byteArray[4]);			//Set Start point
		this.start.setY((int)byteArray[5]);
		this.start.setZ((int)byteArray[6]);
		this.exit.setX((int)byteArray[7]);			//Set Exit point
		this.exit.setY((int)byteArray[8]);
		this.exit.setZ((int)byteArray[9]);
		int counter=10;
		for (int k = 0; k < (int)byteArray[3]; k++) {
			for (int j = 0; j < (int)byteArray[2]; j++) {
				for (int i = 0; i < (int)byteArray[1]; i++) {
					this.mazeCells[i][j][k]=(int)byteArray[counter];
					counter++;
				}
			}
		}
	}
    public Maze3d(MazeArgInit init){
    	this.mazeCells = new int[init.getColumns()][init.getDimension()][init.getRows()];
    }

    public Maze3d(Maze3d maze){		// copy constructor
		int x, y, z;
		x = maze.getXlength();
		y = maze.getYlength();
		z = maze.getZlength();
		mazeCells = new int[x][y][z];
		
		for (int k=0; k<x; k++){		//copy cells
			for(int j=0; j<y; j++){
				for (int i=0; i<z; i++){
					mazeCells [k][j][i] = maze.mazeCells[k][j][i];
				}
			}
		}
    }
	public Maze3d(int x, int y, int z) {
		// TODO Auto-generated constructor stub
	}
	public Position getStartPosition() {

		Position p = new Position(start.getX(), start.getY() ,start.getZ());
		return p;

	}

	public Position getGoalPosition() {

		Position p = new Position(exit.getX(), exit.getY() ,exit.getZ());
		return p;
}
    //Get&Set for data 
    
	public Position getStart(){
    	return start;
    }
    public Position getExit(){
    	return exit;
    }
    public void setStart(Position startPoint){
    	start=startPoint;
    }
    public void setExit(Position endPoint){
    	exit=endPoint;
    }
 	public int getMaxRows() {
		return Initial.getRows();
	}
	public void setMaxRows(int maxRows) {
		this.Initial.setRows(maxRows);
	}
	public int getMaxColumns() {
		return Initial.getColumns();
	}
	public void setMaxColumns(int maxColumns) {
		this.Initial.setColumns(maxColumns);
	}
	public int getDimension() {
		return Initial.getDimension();
	}
	public void setDimension(int dimension) {
		Initial.setDimension(dimension);
	}


	@Override
	public String toString() {
		return "Maze3d [maze3d=" + Arrays.deepToString(mazeCells) + "]";
}
	public int getXlength() {
		return mazeCells.length;
	}

	public int getYlength() {
		return mazeCells[0].length;
	}

	public int getZlength() {
		return mazeCells[0][0].length;
}

	public void insertValue(Position pos, int value) {
		mazeCells[pos.getX()][pos.getY()][pos.getZ()] = value;
	}


	public int getValue(Position pos) {
		int value;
		value = mazeCells[pos.getX()][pos.getY()][pos.getZ()];
		return value;
}
	//Print Maze Cells
	public void print(){
		for(int l=0;l<this.getZlength();l++){
			for(int k=0;k<this.getYlength();k++){
				for(int i=0;i<this.getXlength();i++){
					System.out.println(this.mazeCells[i][k][l]+" ");
				}
				System.out.print("\n");
			}
			System.out.println("----------------");
		}
	}
	public ArrayList<Position> getPossibledirections(Position p){		//getting all possible directions given a certain position
		int x = p.getX();
		int y = p.getY();
		int z = p.getZ();
		ArrayList<Position> directions = new ArrayList<Position>();
		//checks for each dimension if we reached the maximum or minimum value or if there isn't a wall there
			if ((x + 1) < getXlength() && mazeCells[x + 1][y][z] == 0) {
					directions.add(new Position(x + 1, y, z));
				}
			if ((y + 1) < getYlength() && mazeCells[x][y + 1][z] == 0) {
					directions.add(new Position(x, y + 1, z));
				}
			if ((z + 1) < getZlength() && mazeCells[x][y][z + 1] == 0) {
					directions.add(new Position(x, y, z + 1));
				}

			if (x > 0 && mazeCells[x - 1][y][z] == 0) {
					directions.add(new Position(x - 1, y, z));
				}
			if (y > 0 && mazeCells[x][y - 1][z] == 0) {
					directions.add(new Position(x, y - 1, z));
				}
			if (z > 0 && mazeCells[x][y][z - 1] == 0) {
					directions.add(new Position(x, y, z - 1));
	}
		
//		String[] possibledirections = new String[count];
//		for(int i=0 ; i < count ; i++)
//		{
//			possibledirections[i] = directions.get(i);
//		}
		
		return directions;
}
	
	public int[][] getCrossSectionByX(int x) throws Exception{
		if(x<0){
			throw new IndexOutOfBoundsException();
		}
		int[][] maze2d = new int[getZlength()][getYlength()];
		for (int i = 0; i < getYlength(); i++) {
			for (int j = 0; j < getZlength(); j++) {
				maze2d[j][i] = mazeCells[x][i][j];
			}
		}

		return maze2d;

	}
	public int[][] getCrossSectionByY(int y) throws Exception{
		if(y<0){
			throw new IndexOutOfBoundsException();
		}
		int[][] maze2d = new int[getXlength()][getZlength()];
		for (int i = 0; i < getZlength(); i++) {
			for (int j = 0; j < getXlength(); j++) {
				maze2d[j][i] = mazeCells[j][y][i];
			}
		}

		return maze2d;

	}
	public int[][] getCrossSectionByZ(int z) throws Exception{
		if(z<0){
			throw new IndexOutOfBoundsException();
		}
		int[][] maze2d = new int[getXlength()][getYlength()];
		for (int i = 0; i < getYlength(); i++) {
			for (int j = 0; j < getXlength(); j++) {
				maze2d[j][i] = mazeCells[j][i][z];
			}
		}

		return maze2d;

}
	public void printCrossSection(int[][] maze2d) {
		for (int i = 0; i < maze2d[0].length; i++) {
			for (int j = 0; j < maze2d.length; j++) {
				System.out.print(maze2d[j][i] + ", ");
			}
			System.out.print("\n");
		}
}
	
	public byte[] toByteArray() {
		byte[] byteArr = new byte[this.getXlength()*this.getYlength()*this.getZlength() + 10];
		
		int counter = 0 ; 
		byteArr[0] = (byte)10; 						//The number of variables
		byteArr[1] = (byte)this.getXlength();
		byteArr[2] = (byte)this.getYlength();
		byteArr[3] = (byte)this.getZlength();
		byteArr[4] = (byte)this.start.getX();			//start position
		byteArr[5] = (byte)this.start.getY();
		byteArr[6] = (byte)this.start.getZ();
		byteArr[7] = (byte)this.exit.getX();			//exit position
		byteArr[8] = (byte)this.exit.getY();
		byteArr[9] = (byte)this.exit.getZ();
		
		for (int k = 0; k < this.getZlength(); k++) {
			for (int j = 0; j < this.getYlength(); j++) {
				for (int i = 0; i < this.getXlength(); i++) {
					byteArr[counter] = (byte) this.mazeCells[i][j][k];
					counter++;
				}
			}
		}
		
		return byteArr;
}
	
	@Override
	public boolean equals(Object arg0) {
		if (this == arg0)
			return true;
		if (arg0 == null)
			return false;
		if (getClass() != arg0.getClass())
			return false;
		Maze3d maze = (Maze3d) arg0;
		if(Arrays.equals(this.toByteArray(), maze.toByteArray()))
			return true;
		return false;
		
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mazeCells == null) ? 0 : mazeCells.hashCode());
		return result;
	}


	
	
	//TODO check to see if functions beneath is required
	public int getCell(int x, int y, int z) {
		return mazeCells[x][y][z];
}
	public void setCell(int x , int y, int z , int value){
		mazeCells[x][y][z] = value;
}
	public Position getRandomUnvisitedCell(boolean[][][] visited){	//returns a position of a random unvisited cell
		int x = 0, y = 0 , z = 0;
		Random r = new Random();
		
		while(visited[x][y][z] == true){
			x = 1 + r.nextInt(this.Initial.getRows()-1);
			z = 1 + r.nextInt(this.Initial.getDimension()-1);
		}
		return new Position(x, y, z);
	}
	public Position getRandomCell(){			//getting a random Cell inside the maze
		Random r = new Random();
		int x = 0 , y = 0 , z = 0;
		
		while(this.mazeCells[x][y][z]!=0)
		{
			x = 1 + r.nextInt(this.Initial.getColumns()-1);
			y = 1 + r.nextInt(this.Initial.getRows()-1);
			z = 1 + r.nextInt(this.Initial.getDimension()-1);
		}
		return new Position(x,y,z);

}
}
