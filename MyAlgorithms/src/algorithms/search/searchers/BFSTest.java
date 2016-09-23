package algorithms.search.searchers;

import static org.junit.Assert.fail;
import org.junit.Test;
import algorithms.mazeGenerators.Position;
import algorithms.search.searchables.SearchableMaze;

public class BFSTest {

	@Test
	public void test() {
		BFS<Position> bfs = new BFS<Position>();


	    try{
	    	bfs.search(null);
	    	bfs.search(new SearchableMaze(null));
	      fail("Exception");
	    }catch(Exception e){
	      e.printStackTrace();
	    }

	}
}
