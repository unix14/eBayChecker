package model;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;
import algorithms.mazeGenerators.Maze3d;
/**<b>SaveableMaze3d</b><br>
 * SaveableMaze store the maze by its name, making it easier to save and load mazes.
 *
 */
public class SaveableMaze3d implements Serializable {
	private static final long serialVersionUID = 1L;
	String name;
	Maze3d maze;

	public SaveableMaze3d(String name, Maze3d maze) {
		super();
		this.name = name;
		this.maze = maze;
	}

	public SaveableMaze3d(byte[] ary) {
		byte[]a=Arrays.copyOfRange(ary, ary.length-4, ary.length);		
		int b=ByteBuffer.wrap(a).getInt();
		byte[]m=Arrays.copyOfRange(ary, 0, ary.length-4-b);
		byte[]n=Arrays.copyOfRange(ary, b, ary.length-4);
		maze = new Maze3d(m);
		name= n.toString();
	}
	
	public byte[] toByteArray()
	{
		byte[] mazeByte = maze.toByteArray();
		byte[] nameByte = name.getBytes();
		byte[] totalBytesArr = new byte[mazeByte.length+nameByte.length+4];
		int a = nameByte.length;
		
		System.arraycopy(ByteBuffer.allocate(4).putInt(a).array(), 0, totalBytesArr, totalBytesArr.length-4, 4);
		System.arraycopy(mazeByte, 0, totalBytesArr, 0, mazeByte.length);
		System.arraycopy(nameByte, 0, totalBytesArr, mazeByte.length, nameByte.length);
		return totalBytesArr;
		
	}
	public Maze3d getMaze() {
		return maze;
	}
	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		Maze3d m = (Maze3d) obj;
		if(maze.equals(m))
			return true;
		else
			return false;
	}
	
}