package algorithms.mazeGenerators;

public class MazeArgInit {
	private int dimension,rows,columns;

	public MazeArgInit(int dimension, int rows, int columns) {
		this.dimension = dimension;
		this.rows = rows;
		this.columns = columns;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

}
