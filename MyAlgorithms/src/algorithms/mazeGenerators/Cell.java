package algorithms.mazeGenerators;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("serial")
public class Cell extends Position {
    private boolean visited;
    private List<Cell> neighbors;
    private Random random;

	public Cell(int columns, int rows, int dimension) {
		super(columns, rows, dimension);
        this.setVisited(false);
        this.neighbors = new LinkedList<Cell>();
        random = new Random();
	}

	public Cell(Position Point) {
		super(Point);
        this.setVisited(false);
        this.neighbors = new LinkedList<Cell>();
        random = new Random();
	}

    public void setVisited(boolean visited) {
        this.visited = visited;
}
    
    @Override
    public int hashCode(){
        int result = 17;
        result = 37 * result + this.getX();
        result = 37 * result + this.getY();
        return result;
    }

    @Override
    public boolean equals(Object other){
        Cell cell = (Cell)other;
        return cell.getX() == this.getX() && cell.getY() == this.getY();
    }

    @Override
    public String toString(){
        return "(" + getX() + "," + getY() + ")";
}
    
    public boolean isVisited() {
        return visited;
}

    public Cell getRandomNeighbor(){
        return this.neighbors.get(random.nextInt(neighbors.size()));
}
    public List<Cell> getNeighbors(){
        return this.neighbors;
}
    public void addNeighbor(Cell cell){
        if(!this.neighbors.contains(cell)){
            this.neighbors.add(cell);
        }
}
    public boolean hasUnvisitedNeighbors() {
        for(Cell cell : neighbors){
            if(!cell.isVisited()){
                return true;
            }
        }
        return false;
}
    
    public List<Cell> getUnvisitedNeighbors() {
        List<Cell> unvisited = new LinkedList<>();
        for(Cell cell : neighbors){
            if(!cell.isVisited()){
                unvisited.add(cell);
            }
        }
        return unvisited;
    }

    public void removeNeighbor(Cell current) {
        if(neighbors.contains(current)){
            neighbors.remove(current);
        }
}
    
}
