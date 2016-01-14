/**
 * @author Justin Espejo
 * Temperature GUI Class
 * CS212
 * This class is for initializing and printing the GUI.
 */
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.text.DecimalFormat;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyGUI extends JFrame {
/**
 * This is the  GUI. This class is used to display the GUI.
 */
	private static final long serialVersionUID = 1L;
	static String inFileName;
	static Container cPane;
	static TextArea t1, t2;
	static DecimalFormat df = new DecimalFormat("#.0");

	public MyGUI() {
		t1 = new TextArea("Input File\n");
		t2 = new TextArea("Output\n");
		t1.setEditable(false);
		t2.setEditable(false);

		// initialize();
		setSize(600, 400);
		setLocation(200, 200);
		setTitle("Project 4 - Count Words");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new GridLayout(1, 2));
		cPane = getContentPane();
		cPane.add(t1);
		cPane.add(t2);
		
		JMenuItem item;
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		FileMenuHandler fmh = new FileMenuHandler(this);

		item = new JMenuItem("Open"); // Open...
		item.addActionListener(fmh);
		fileMenu.add(item);

		fileMenu.addSeparator(); // add a horizontal separator line

		item = new JMenuItem("About");
		item.addActionListener(fmh);
		fileMenu.add(item);

		fileMenu.addSeparator(); // add a horizontal separator line

		item = new JMenuItem("Quit"); // Quit
		item.addActionListener(fmh);
		fileMenu.add(item);
	
		JMenu fileMenu2 = new JMenu("Edit");
		
		item = new JMenuItem("Add"); // Add...
		item.addActionListener(fmh);
		fileMenu2.add(item);
		
		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(fileMenu2);
		setVisible(true);
	}

	/**
	 * This method prints the GUI. it adds the DATA into the GUI so when the GUI
	 * is displayed it will print out all the data that was stored in the treemap
	 */
	public static void printToOutput(TreeMap<String, Integer> collectionOfWords) { // will probably be the map (needs to be edited)
		t2.setText("Output\n");
		Set<String> words = collectionOfWords.keySet();
		for(String word: words){
			t2.append(word + ": " + collectionOfWords.get(word) + "\n");
		}
//		for (int i = 0; i < collectionOfWords.length; i++) {
//			t2.append(collectionOfWords[i] + "\n"); // adds the linked
//															// list in the text
//															// area
//		}
		// myFrame.setLayout(new GridLayout(1, 2));
		// cPane = myFrame.getContentPane();	
		// cPane.add(t1);
		// cPane.add(t2);
		// myFrame.setVisible(true);
		

	}

	public static void addInputLine(String line) {
		t1.append(line);
	}

}
