package model;

public interface Model {

	public void work(String fileName);
	public void work(String fileName,int numOfThreads);
	public void save(String outputfileName);
	public void close();
	public Boolean itemMissing();
}
