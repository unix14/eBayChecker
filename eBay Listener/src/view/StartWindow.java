package view;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import controller.Controller;

public class StartWindow extends BasicWindow implements View{

	//MVC
	Controller controller;

	//User File's path
	String fileName = null;
	String outputFileName = "missing.txt";

	//Widgets
	public Text log;
	public Label infoLabel;
	
	//I.O.
	File introText = new File("intro.txt");
	StringBuilder consoleBuilder;
	
	public StartWindow(String title, int width, int height) throws IOException {
		super(title, width, height);
	    Image small = new Image(display,"eBay.ico");
	    shell.setImage(small);
	}

		
	@SuppressWarnings("resource")
	@Override
	void initWidget() {
		shell.setLayout(new GridLayout(2,false));
//		shell.
		
		Button openFile = new Button(shell,SWT.PUSH);
		openFile.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		openFile.setText("Open");

		log = new Text(shell,SWT.BORDER |  SWT.V_SCROLL| SWT.H_SCROLL);
		log.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,4));
		try {
			BufferedReader introViewer = new BufferedReader(new InputStreamReader(new FileInputStream(introText)));
			consoleBuilder = new StringBuilder();
			String introLine;
			while ((introLine=introViewer.readLine())!=null)
				consoleBuilder.append(introLine+"\n");
			log.setText(consoleBuilder.toString());
		} catch (IOException e1) {}
		
		Button runButton = new Button(shell, SWT.PUSH);
		runButton.setText("Run");
		runButton.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		runButton.setEnabled(false);
		
		Button saveButton = new Button(shell, SWT.PUSH);
		saveButton.setText("Save");
		saveButton.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		saveButton.setEnabled(false);
		
		infoLabel = new Label(shell,SWT.NONE);
		infoLabel.setText("eBay Checker Loaded");
		infoLabel.setLayoutData(new GridData(SWT.BOTTOM,SWT.FILL,false,false,2,5));


		runButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(fileName != null){
					infoLabel.setText("Reading Item List");
					consoleBuilder.append("\n===================\n\nAvailable Items on eBay Right Now :\n\n");
					controller.work(fileName);
					saveButton.setEnabled(true);
					infoLabel.setText("Press 'Save' to Export");
					
				}else{
					infoLabel.setText("Press 'Open' to Load");
					openMessageBox("Items list missing","No items list chosen.\nClick 'Open' in order to load a file.",SWT.ERROR );
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});

		
		saveButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				infoLabel.setText("Getting ready to save");
				if(fileName!=null && ((outputFileName=saveOrReadFile(true,SWT.SAVE))!=null)){
						infoLabel.setText("Saving Output File...");
						controller.save(outputFileName);
						infoLabel.setText("Saved");
				}else{
					infoLabel.setText("Press 'Open' to load");
					openMessageBox("Items List Missing","File Not Loaded\nPlease Try Again.\n",SWT.ERROR);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		openFile.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				infoLabel.setText("Open a File");
				if((fileName=saveOrReadFile(false,SWT.OPEN))!=null){
					runButton.setEnabled(true);
					infoLabel.setText("Item List Loaded");
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		shell.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				controller.close();
			}
		});;
		

	}

	void openMessageBox(String title,String Message,int style){
		MessageBox errorMsg = new MessageBox(shell,style);
		errorMsg.setText(title);
		errorMsg.setMessage(Message);
		errorMsg.open();
	}
	String saveOrReadFile(Boolean saveDialog ,int style){
		FileDialog fd = new FileDialog(shell,style);
		fd.setFilterPath("C:/Users/eyal/Desktop");
		String [] extensions = "*.txt *.rar *.bulbul *.420".split(" ");
		fd.setFilterExtensions(extensions);
		if(saveDialog)
			fd.setFileName(outputFileName);
		return fd.open();
	}

	public void setController(Controller controller){
		this.controller = controller;
	}
	
	@Override
	public void display(String fileName) {
		infoLabel.setText("Reading Finished");
		consoleBuilder.append(fileName+"\n===================");
		log.setText(consoleBuilder.toString());
		infoLabel.setText("eBay Checker Finished");
	}


	@Override
	public void start() {
		run();		
	}
	
}
