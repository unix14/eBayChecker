package view;


import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import controller.Controller;

public class StartWindow extends BasicWindow implements View{

	//MVC
	Controller controller;

	//User File's path
	String fileName = null;
	String outputFileName = null;
	final String aboutFileLoc = "README.txt";
	final String frameTitle = "eBay Checker";
	final String mainIcon = "icon.png";
	final String aboutIcon = "FAQ.png";
	final String myGithubLink = "https://github.com/unix14/";
	//Widgets
	public Text log;
	public Label infoLabel;
	Button runButton;
	Button saveButton;
	MenuItem fileSaveItem;
	MenuItem fileSaveLogItem;
	MenuItem fileRunItem;
	
	Label threadTextUser;
	Text numOfThreads;
	Label outFileText;
	Text outFile;
	Text inFile;
	Label inFileText;
	
	Button browseForFolder;
	Button browse;
	Button okButton;
	Boolean fixedThreads=false;
	
	Shell prop;

	//I.O.
	File introText = new File("intro.txt");
	StringBuilder consoleBuilder;
	int userNumOfThreads;		//DONT FORGET : initialize eBayListener
	
	public StartWindow(String title, int width, int height) throws IOException {
		super(title, width, height);
	    Image small = new Image(display,mainIcon);
	    shell.setImage(small);
	    shell.setLayout(new GridLayout(1,true));
	    consoleBuilder = new StringBuilder();
	}

		
	@SuppressWarnings("resource")
	@Override
	void initWidget() {
		
		
		Menu menuBar = new Menu(shell, SWT.BAR);
		
		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		Menu runMenu = new Menu(shell, SWT.DROP_DOWN);
		Menu viewMenu = new Menu(shell, SWT.DROP_DOWN);
		Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
		
		MenuItem cascadeFileMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeFileMenu.setText("&File");
		cascadeFileMenu.setMenu(fileMenu);
		
		MenuItem cascadeRunMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeRunMenu.setText("&Run");
		cascadeRunMenu.setMenu(runMenu);
		

		MenuItem fileViewItem = new MenuItem(menuBar, SWT.CASCADE);
		fileViewItem.setText("&View");
		fileViewItem.setMenu(viewMenu);
		
		MenuItem cascadeHelpMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeHelpMenu.setText("&Help");
		cascadeHelpMenu.setMenu(helpMenu);


		//View Menu
		MenuItem fontWindow = new MenuItem(viewMenu, SWT.PUSH);
		fontWindow.setText("&Font");
		fontWindow.addSelectionListener(fontChanger());
		
		MenuItem propertiesWindow = new MenuItem(viewMenu, SWT.PUSH);
		propertiesWindow.setText("&Properties");
		propertiesWindow.addSelectionListener(propWindow());
		
		
		//Help Menu
		MenuItem siteOpenItem = new MenuItem(helpMenu, SWT.PUSH);
		siteOpenItem.setText("&GitHub");
		
		MenuItem openAboutItem = new MenuItem(helpMenu, SWT.PUSH);
		openAboutItem.setText("&About");

		//Run Menu
		fileRunItem = new MenuItem(runMenu, SWT.PUSH);
		fileRunItem.setText("&Run");
		fileRunItem.setEnabled(false);

		
		//File Menu
		MenuItem fileLoadItem = new MenuItem(fileMenu, SWT.PUSH);
		fileLoadItem.setText("&Open File");

		fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
		fileSaveItem.setText("&Save");
		fileSaveItem.setEnabled(false);
		
		fileSaveLogItem = new MenuItem(fileMenu, SWT.PUSH);
		fileSaveLogItem.setText("&Save Log File");
		fileSaveLogItem.setEnabled(false);

		
		MenuItem fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
		fileExitItem.setText("&Exit");

		shell.setMenuBar(menuBar);

		
		
		
		Group group1 = new Group(shell, SWT.NONE);
		group1.setText(frameTitle);
		group1.setLayout(new GridLayout(2,false));
		group1.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,2,2));
		
		
		Button openFile = new Button(group1,SWT.PUSH);
		openFile.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		openFile.setText("Open");

		log = new Text(group1,SWT.BORDER | SWT.V_SCROLL| SWT.H_SCROLL);
		log.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,4));
		
		//Print Intro Text
		try {
			BufferedReader introViewer = new BufferedReader(new InputStreamReader(new FileInputStream(introText)));

			String introLine;
			while ((introLine=introViewer.readLine())!=null)
				consoleBuilder.append(introLine+"\n");
			log.setText(consoleBuilder.toString());
		} catch (IOException e1) {}
		
		//Create Buttons
		
		runButton = new Button(group1, SWT.PUSH);
		runButton.setText("Run");
		runButton.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		runButton.setEnabled(false);
		
		saveButton = new Button(group1, SWT.PUSH);
		saveButton.setText("Save");
		saveButton.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		saveButton.setEnabled(false);
		
		Button aboutButton = new Button(group1,SWT.PUSH);
		aboutButton.setText("About");
		aboutButton.setLayoutData(new GridData(SWT.FILL,SWT.BOTTOM,false,false,1,1));
		
		
		infoLabel = new Label(shell,SWT.NONE);
		infoLabel.setText(frameTitle+" Loaded");
		infoLabel.setLayoutData(new GridData(SWT.BOTTOM,SWT.NONE,false,false,1,1));

		
		
		//Add an event handler for pushing the button
		aboutButton.addSelectionListener(aboutWindow());
		openAboutItem.addSelectionListener(aboutWindow());
		
		runButton.addSelectionListener(runWork());
		fileRunItem.addSelectionListener(runWork());
		
		saveButton.addSelectionListener(saveToFile());
		fileSaveItem.addSelectionListener(saveToFile());
		
		fileSaveLogItem.addSelectionListener(saveLogFile());
		
		openFile.addSelectionListener(openFile());
		fileLoadItem.addSelectionListener(openFile());
		
		siteOpenItem.addSelectionListener(openGit());
		
		shell.addDisposeListener(closeShell());
		fileExitItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.dispose();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});


	}

	void openMessageBox(String title,String Message,int style){
		MessageBox errorMsg = new MessageBox(shell,style);
		errorMsg.setText(title);
		errorMsg.setMessage(Message);
		errorMsg.open();
	}
	String saveOrReadFile(Boolean saveDialog){
		FileDialog fd;
		if(saveDialog)
			fd = new FileDialog(shell,SWT.SAVE);
		else
			fd = new FileDialog(shell,SWT.OPEN);
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
		infoLabel.setText(frameTitle+" Finished");
	}

	@Override
	public void start() {
		run();		
	}
	
	public static void openWebpage(URI uri) throws IOException {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	            desktop.browse(uri);
	    }
	}

	public static void openWebpage(URL url) {
	    try {
	        openWebpage(url.toURI());
	    } catch (URISyntaxException | IOException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public SelectionListener openFile(){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				infoLabel.setText("Open a File");
				String ret;
				if((ret=saveOrReadFile(false))!=null){
					fileName=ret;
					setTitle(frameTitle+" - "+fileName);
					runButton.setEnabled(true);
					fileRunItem.setEnabled(true);
					infoLabel.setText("Item List Loaded");
					if(prop!=null){
						inFile.setText(ret);
						prop.setFocus();
					}
					else
						shell.setFocus();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		};
	}
	
	public SelectionListener openFileForBGTest(){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				infoLabel.setText("Open a File");
				String ret;
				if((ret=saveOrReadFile(false))!=null){
//					inFile.setText(ret);
					outputFileName=ret;
//					setTitle(frameTitle+" - Properties");
					infoLabel.setText("Background Checker Configured");
					if(!prop.isDisposed()){
						outFile.setText(ret);
					}
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		};
	}
	
	public SelectionListener runWork(){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(fileName != null){
					infoLabel.setText("Reading Item List");
					consoleBuilder.append("\n\nAvailable Items on eBay Right Now :\n\n");
					if(fixedThreads){
						controller.work(fileName,userNumOfThreads);
					}else
						controller.work(fileName);
					saveButton.setEnabled(true);
					fileSaveItem.setEnabled(true);
					fileSaveLogItem.setEnabled(true);
					infoLabel.setText("Press 'Save' to Export");
					
				}else{
					infoLabel.setText("Press 'Open' to Load");
					openMessageBox("Items list missing","No items list chosen.\nClick 'Open' in order to load a file.",SWT.ERROR );
					runButton.setEnabled(false);
					saveButton.setEnabled(false);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		};
	}
	
	public SelectionListener saveToFile(){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				infoLabel.setText("Getting ready to save");
				if(fileName!=null && ((outputFileName=saveOrReadFile(true))!=null)){
						infoLabel.setText("Saving Output File...");
						controller.save(outputFileName);
						infoLabel.setText("Saved");
				}else if(fileName !=null){
					infoLabel.setText("Press 'Save' to Export");
					openMessageBox("Error Saving Item","File Path Not Choosed\nPlease Try Again.\n",SWT.ERROR);
				}else{
					infoLabel.setText("Press 'Open' to Load");
					openMessageBox("Items List Missing","File Not Loaded\nPlease Try Again.\n",SWT.ERROR);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		};
	}
	
	public SelectionListener saveLogFile(){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				infoLabel.setText("Getting ready to save");
				if(fileName!=null && ((outputFileName=saveOrReadFile(true))!=null)){
						infoLabel.setText("Saving Log File...");
						try {
								File logOutputFile = new File(outputFileName);
								FileOutputStream outMissingFile = new FileOutputStream(logOutputFile);
								
								// if file doesnt exists, then create it
								if (!logOutputFile.exists()) {
										logOutputFile.createNewFile();
								}
								byte[] itemNumbersInBytes = log.getText().toString().getBytes();
								
								outMissingFile.write(itemNumbersInBytes);
								outMissingFile.flush();
								outMissingFile.close();
						} catch (IOException e) {}
							
							infoLabel.setText("Saved");
						
				}else if(fileName !=null){
					infoLabel.setText("Press 'Save' to Export");
					openMessageBox("Error Saving Item","File Path Not Choosed\nPlease Try Again.\n",SWT.ERROR);
				}else{
					infoLabel.setText("Press 'Open' to Load");
					openMessageBox("Items List Missing","File Not Loaded\nPlease Try Again.\n",SWT.ERROR);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		};
	}
	
	public SelectionListener aboutWindow(){
		return new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {

				Shell about = new Shell(display, SWT.CLOSE ); 
			    Image small = new Image(display,aboutIcon);
			    about.setImage(small);
				about.setSize(1000,300);
				about.setMinimumSize(1000,300);
				about.setText("About");
			    about.setLayout(new GridLayout());
			    
			    Text info = new Text(about, SWT.MULTI |  SWT.V_SCROLL|  SWT.H_SCROLL|SWT.READ_ONLY );
			    info.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
			    
			    
				StringBuilder newFile = new StringBuilder();
				FileInputStream aboutFile;
				try {
					aboutFile = new FileInputStream(new File(aboutFileLoc));
					int line;
					
					while((line = aboutFile.read())!=-1){
						newFile.append((char)line);
					}
					info.setText(newFile.toString());
					info.setFont(new Font(display,new FontData("Lucida Console", 14, SWT.NONE)));
				}catch(Exception e){}

			    about.open();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		};
	}
	
	public SelectionListener fontChanger(){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FontDialog font = new FontDialog(shell);
				font.setText("Pick a Font");
				FontData fdata = font.open();
				if(fdata!=null)
					log.setFont(new Font(display,fdata));
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		};
	}
	public SelectionListener propWindow(){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				prop = new Shell(display, SWT.CLOSE | SWT.NO_REDRAW_RESIZE); 
//			    Image small = new Image(display,aboutIcon);
//			    about.setImage(small);
				prop.setSize(350,350);
				prop.setMinimumSize(250,250);
				prop.setText("Properties");
				prop.setLayout(new GridLayout());
				
			    Group g2 = new Group(prop,SWT.NONE);
			    g2.setText("Configurations");
				g2.setLayout(new GridLayout(2,false));
				g2.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,4,4));
				
			    Group g1 = new Group(prop,SWT.NONE);
			    g1.setText("Thread Pool");
				g1.setLayout(new GridLayout(2,true));
				g1.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,5,5));
				
				Button cached = new Button(g1, SWT.RADIO);
				cached.setText("Cached");
				cached.addSelectionListener(cachedThreadNumber());
				cached.setSelection(true);
				
			    Button fixed = new Button(g1, SWT.RADIO);
			    fixed.setText("Fixed");
			    fixed.addSelectionListener(fixedThreadNumber());
			    
			    
			    threadTextUser = new Label(g1, SWT.NONE);
			    threadTextUser.setText("# of Threads");
			    threadTextUser.setVisible(false);
			    threadTextUser.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
			    
			    numOfThreads = new Text(g1, SWT.BORDER);
			    numOfThreads.setVisible(false);
			    


				

//				Button bgTimeText = new Button(g2, SWT.CHECK);
//				bgTimeText.setText("Keep in Background");
//				bgTimeText.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false,3,1));
////				bgTimeText.addSelectionListener(runInBackground());
//				bgTimeText.setVisible(false);
				
				
				inFileText = new Label(g2, SWT.NONE);
				inFileText.setText("Item List Location :");
				inFileText.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false,3,1));
				
				inFile = new Text(g2, SWT.BORDER);
				inFile.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false,2,1));
				if(fileName!=null)
					inFile.setText(fileName);

				browse = new Button(g2, SWT.PUSH);
				browse.setText("Browse");
				browse.setLayoutData(new GridData(SWT.RIGHT,SWT.FILL,false,false,3,1));
				browse.addSelectionListener(openFile());
				
				outFileText = new Label(g2, SWT.NONE);
				outFileText.setText("Log file path:");
				outFileText.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,3,1));
				
				outFile = new Text(g2, SWT.BORDER);
				outFile.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,2,1));
				if(outputFileName!=null)
					outFile.setText(outputFileName);
				
				browseForFolder = new Button(g2, SWT.PUSH);
				browseForFolder.setText("Browse");
				browseForFolder.setLayoutData(new GridData(SWT.RIGHT,SWT.FILL,false,false,3,1));
				browseForFolder.addSelectionListener(browseForFolder());
				
				okButton = new Button(prop, SWT.CLOSE);
				okButton.setText("OK");
				okButton.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						//Save options
						Boolean userChangedProps=false;
						if(threadTextUser.isEnabled() && !numOfThreads.getText().equals("")){
							userNumOfThreads = Integer.parseInt(numOfThreads.getText());
							fixedThreads=true;
							userChangedProps=true;
						}
						if(userChangedProps || (fileName!=null || outputFileName!=null))
							openMessageBox("Succsess", "Properties Succsesfully Saved.", SWT.OK);
						if(fileName == null) {
							consoleBuilder.append("\nPlease enter item list location in 'Open'\n");
						}
//						String[] args = {inFile.getText(),outFile.getText()};
//							shell.dispose();
//							
//							Runtime rt = Runtime.getRuntime();
//			                try {
//								Process pr = rt.exec("Silent.exe");
//								
//				                BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//				                
//				                String line=null;
//				 
//				                while((line=input.readLine()) != null) {
//				                    System.out.println(line);
//				                }
//				 
//				                pr.waitFor();
//				                
//							} catch (IOException | InterruptedException e1) {
//								e1.printStackTrace();
//							}
//			                
//							new Runnable() {
//								
//								@Override
//								public void run() {
//								try {
//									SilentChecker.main(args);
//								} catch (Exception e) {
//									e.printStackTrace();
//								}
//									
//								}
//							}.run();
							;
						prop.dispose();
//						System.out.println(userNumOfThreads);
						//TODO: make thread changing throu options
					}
					
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {}
				});
//				disableButtonsOnPropWindow();
					prop.open();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		};
	}
	public void disableButtonsOnPropWindow(){
		inFileText.setEnabled(false);
		inFile.setEnabled(false);
		browse.setEnabled(false);
		browseForFolder.setEnabled(false);
		outFileText.setEnabled(false);
		outFile.setEnabled(false);
	}
	public SelectionListener fixedThreadNumber(){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			    threadTextUser.setVisible(true);
			    numOfThreads.setVisible(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		};
	}
	public SelectionListener runInBackground(){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Button btn = (Button)arg0.getSource();
				if(btn.getSelection()){
					inFileText.setEnabled(true);
					inFile.setEnabled(true);
					browse.setEnabled(true);
					browseForFolder.setEnabled(true);
					outFileText.setEnabled(true);
					outFile.setEnabled(true);
				}else
					disableButtonsOnPropWindow();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		};
	}
	public SelectionListener browseForFolder(){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(outputFileName!=null || fileName!=null){
					FileDialog fd;
					fd = new FileDialog (shell,SWT.SAVE);
					fd.setFilterPath("C:/Users/eyal/Desktop");
					String ret= fd.open();
					if(!prop.isDisposed()&& ret!=null){
						outFile.setText(ret);
						outputFileName=ret;
						saveButton.setEnabled(true);
					}
					
				}else{
					openMessageBox("Item List Missing..", "Please load firstly the item list location", SWT.OK);
				}
				prop.setFocus();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		};
	}
 	public SelectionListener cachedThreadNumber(){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			    threadTextUser.setVisible(false);
			    numOfThreads.setVisible(false);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		};
	}
	public SelectionListener openGit(){
		return new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					URL mySite = new URL(myGithubLink);
					openWebpage(mySite);
					
				} catch (MalformedURLException e) {}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		};
	}
 	public DisposeListener closeShell(){
		return new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				controller.close();
			}
		};
		
	}
 	
}
