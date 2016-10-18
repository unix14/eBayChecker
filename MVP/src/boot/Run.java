package boot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.MyModel;
import presenter.Command;
import presenter.Presenter;
import presenter.Properties;
import view.CLIView;
import view.GUIView;
import view.gui.StartWindow;

public class Run {
	private static final String propFile = new String("properties.xml");
	private static Properties properties;

	public static void initProperties() throws ParserConfigurationException, SAXException, IOException {
		File file = new File(propFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = (Document) dBuilder.parse(file);
		doc.getDocumentElement().normalize();
		
		properties = new Properties(Integer.parseInt(doc.getElementsByTagName("numOfThreads").item(0).getTextContent()),
				doc.getElementsByTagName("interfaceView").item(0).getTextContent(),
				doc.getElementsByTagName("searchAlgo").item(0).getTextContent());
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException,
			ParserConfigurationException, SAXException {

		/* loading properties file */
		initProperties();
		
		HashMap<String, Command> hmap = new HashMap<String, Command>();
		if (properties.getInterfaceView().equals("GUI")) {
			StartWindow s = new StartWindow(650, 620, properties.getSearchAlgo());
			MyModel m = new MyModel(properties.getNumOfThreads());
		
			GUIView v = new GUIView(hmap, s);
			Presenter p = new Presenter(m, v);

			s.addObserver(v);
			v.addObserver(p);
			m.addObserver(p);

			/* hash map creates all the commands here */
			hmap.put("dir", new Command() {

				@Override
				public void doCommand(String[] param) {
					m.dir(param[1]);
				}
			});

			hmap.put("generate", new Command() {

				@Override
				public void doCommand(String[] param) {
					// generate 3d maze
					m.generate3DMaze(param[3], Integer.parseInt(param[4]), Integer.parseInt(param[5]),
							Integer.parseInt(param[6]));

				}
			});

			hmap.put("display", new Command() {

				@Override
				public void doCommand(String[] param) {
					try {
						if (param[1].equals("cross"))
								m.displayCrossSection(param[4], Integer.parseInt(param[5]), param[6]);
						else if (param[1].equals("solution"))
							m.displaySolution(param[2]);
	
						else
							m.display(param[1]);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});

			hmap.put("save", new Command() {

				@Override
				public void doCommand(String[] param) {
					if (param[1].equals("solution"))
						try {
							m.saveSolution(param[2]);
						} catch (IOException e) {}

					else
						try {
							m.saveMaze(param[2], param[3]);
						} catch (IOException e) {}
				}
			});

			hmap.put("load", new Command() {

				@Override
				public void doCommand(String[] param) {
					try {
						m.loadMaze(param[2], param[3]);
					} catch (IOException e) {}

				}
			});

			hmap.put("maze", new Command() {

				@Override
				public void doCommand(String[] param) {
					m.mazeSize(param[2]);
				}
			});

			hmap.put("file", new Command() {

				@Override
				public void doCommand(String[] param) {
					m.fileSize(param[2]);
				}
			});

			hmap.put("solve", new Command() {

				@Override
				public void doCommand(String[] param) {
					m.solve(param[1], param[2]);
				}
			});

			hmap.put("exit", new Command() {

				@Override
				public void doCommand(String[] param) {
					m.exit();
				}
			});

			new Thread(s).start();
		}
		
		if(properties.getInterfaceView().equals("CLI")){
			
			MyModel m = new MyModel(properties.getNumOfThreads());
			
			CLIView v = new CLIView(hmap);
			Presenter p = new Presenter(m, v);

			v.addObserver(p);
			m.addObserver(p);

			/* hash map creates all the commands here */
			hmap.put("dir", new Command() {

				@Override
				public void doCommand(String[] param) {
					m.dir(param[1]);
				}
			});

			hmap.put("generate", new Command() {

				@Override
				public void doCommand(String[] param) {
					// generate 3d maze
					m.generate3DMaze(param[3], Integer.parseInt(param[4]), Integer.parseInt(param[5]),
							Integer.parseInt(param[6]));

				}
			});

			hmap.put("display", new Command() {

				@Override
				public void doCommand(String[] param) {
					try {
						if (param[1].equals("cross"))
								m.displayCrossSection(param[4], Integer.parseInt(param[5]), param[6]);
						else if (param[1].equals("solution"))
							m.displaySolution(param[2]);
	
						else
							m.display(param[1]);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			hmap.put("save", new Command() {

				@Override
				public void doCommand(String[] param) {
					if (param[1].equals("solution"))
						try {
							m.saveSolution(param[2]);
						} catch (IOException e) {}

					else
						try {
							m.saveMaze(param[2], param[3]);
						} catch (IOException e) {}
				}
			});

			hmap.put("load", new Command() {

				@Override
				public void doCommand(String[] param) {
					try {
						m.loadMaze(param[2], param[3]);
					} catch (IOException e) {}
				}
			});

			hmap.put("maze", new Command() {

				@Override
				public void doCommand(String[] param) {
					m.mazeSize(param[2]);
				}
			});

			hmap.put("file", new Command() {

				@Override
				public void doCommand(String[] param) {
					m.fileSize(param[2]);
				}
			});

			hmap.put("solve", new Command() {

				@Override
				public void doCommand(String[] param) {
					m.solve(param[1], param[2]);
				}
			});

			hmap.put("exit", new Command() {

				@Override
				public void doCommand(String[] param) {
					m.exit();
				}
			});

			new Thread(v).start();
			
		}			

	}

}
