package view;



import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class BasicWindow implements Runnable{

	Display display;
	Shell shell;

	public BasicWindow(String title,int width,int height) {
		display = new Display();
		shell = new Shell(display); 
		
		shell.setSize(width,height);
		shell.setMinimumSize(width,height);
		shell.setText(title);
		
	    RowLayout rowLayout = new RowLayout();
	    rowLayout.pack = false;
	    
	    shell.setLayout(rowLayout);
	}
	abstract void initWidget();
	public void setTitle(String title){
		shell.setText(title);
	}
	public void run(){
		//initialize window
		initWidget();
		
		//open shell
		shell.open();		//open GUI
		
		//main Event loop
		while(!shell.isDisposed()){		//window isn't close
			
		    // 1. read events, put then in a queue.
		    // 2. dispatch the assigned listener
			if(!display.readAndDispatch()){
				display.sleep();
			}
		} // shell is disposed
		
		//dispose OS components
		display.dispose();
	}

}
