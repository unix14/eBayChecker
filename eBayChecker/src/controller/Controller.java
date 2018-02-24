package controller;

public interface Controller {
	
	public void work(String fileName);
	
	public void work(String fileName,int numOfThreads);
	
	public void save(String outputfileName);
	
	public void display(String fileName);
	
	public void close();

}
