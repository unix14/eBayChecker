package algorithms.mazeGenerators;

//Main Algorithm
public interface Maze3dGenerator {
	public Maze3d generate(MazeArgInit mazeArgs);
	public String measureAlgorithmTime(MazeArgInit mazeArgs);
}
