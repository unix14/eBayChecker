package algorithms.mazeGenerators;

public abstract class AbstractGenerator implements Maze3dGenerator {
	@Override
	public abstract Maze3d generate(MazeArgInit mazeArgs);
	
	@Override
	public String measureAlgorithmTime(MazeArgInit mazeArgs) {
		long start = System.currentTimeMillis();
		generate(mazeArgs);
		long end=System.currentTimeMillis()-start;
		return "Algorithm run time : "+(end)+" milisecnds.";
	}
}
