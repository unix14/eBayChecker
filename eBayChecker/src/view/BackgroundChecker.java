package view;


import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import controller.Controller;

public class BackgroundChecker extends BasicWindow implements View {
	Controller controller;
	final String mainIcon = "icon.png";
	public Text log;
	public Label text;
	
	public BackgroundChecker(String title, int width, int height) {
		super(title, width, height);
	    Image small = new Image(display,mainIcon);
	    shell.setImage(small);
	    shell.setLayout(new GridLayout(1,true));
	    
	    text = new Label(shell, SWT.NONE);
		log = new Text(shell,SWT.MULTI|SWT.V_SCROLL|SWT.READ_ONLY|SWT.DIALOG_TRIM|SWT.CLOSE);
		
		text.setLayoutData(new GridData(SWT.CENTER,SWT.CENTER,false,false,1,1));
		log.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
	}

	@Override
	public void display(String output) {
		log.setText(output);
	}

	@Override
	public void start() {
		run();
	}

	public void setController(Controller controller){
		this.controller = controller;
	}
	
	@Override
	void initWidget() {

//		log.setFont(new );
		text.setText("Missing items were found during background checks");
		text.setForeground(new Color(Display.getCurrent(),255,0,0));
		
	}

}
