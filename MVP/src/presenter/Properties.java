package presenter;

import java.io.Serializable;
/**
 * <b>Properties class </b><br>
 * holds the properties of the maze.
 */
@SuppressWarnings("serial")
public class Properties implements Serializable {
	private int numOfThreads = 1;
	private String interfaceView = null;
	private String searchAlgo = null;
	
	public Properties(int size, String intrView, String srchAlgo) {
		this.numOfThreads=size;
		this.interfaceView=intrView;
		this.searchAlgo=srchAlgo;
	}
	public void setNumOfThreads(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}
	public int getNumOfThreads() {
		return numOfThreads;
	}
	public String getInterfaceView() {
		return interfaceView;
	}
	public void setInterfaceView(String interfaceView) {
		this.interfaceView = interfaceView;
	}
	public String getSearchAlgo() {
		return searchAlgo;
	}
	public void setSearchAlgo(String searchAlgo) {
		this.searchAlgo = searchAlgo;
	}
	
}