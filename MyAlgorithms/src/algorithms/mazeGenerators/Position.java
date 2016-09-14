package algorithms.mazeGenerators;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Position implements Serializable{
	private int x,y,z;
	public Position() {
		this.x=0;
		this.y=0;
		this.z=0;
	}
	public Position(int columns, int rows, int dimension) {
		super();
		this.x = columns;
		this.y = rows;
		this.z = dimension;
	}
	public Position(Position Point){
		this.x=Point.x;
		this.y=Point.y;
		this.z=Point.z;
	}
	public void SetPosition(int x,int y,int z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	
	public byte[] toByteArray() {
		byte[] b = new byte[3] ;
		int  i =0 ;
		
		b[i++] = (byte)this.getX();
		b[i++] = (byte)this.getY();
		b[i++] = (byte)this.getZ();
		

		return b;
}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
}
	
}
