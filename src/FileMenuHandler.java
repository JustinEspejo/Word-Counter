import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author justinespejo This is the filemenu handler. This is the actions that
 *         will be performed when user clicks on one of the menu on the GUI.
 */
public class FileMenuHandler implements ActionListener {
	JFrame jframe;

	public FileMenuHandler(JFrame jf) {
		jframe = jf;
	}

	/**
	 * This is for "open" menu item. This open a file chosen by the user. It will read the values
	 * with the RegEx check if its a word, store it in a treemap also check if the word is already stored in the treemap.
	 */
	public void actionPerformed(ActionEvent event) {
		String menuName = event.getActionCommand();
		if (menuName.equals("Open")) {
			JFileChooser fc = new JFileChooser();
			fc.setAcceptAllFileFilterUsed(false);
			// Start in current directory
			fc.setCurrentDirectory(new File("."));
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fc.setFileFilter(new javax.swing.filechooser.FileFilter() {
				public boolean accept(File f) {
					String name = f.getName().toLowerCase();
					return name.endsWith(".txt") || f.isDirectory();
				}

				public String getDescription() {
					return "Text files";
				}
			});
			int result = fc.showOpenDialog(null);

			if (result == JFileChooser.CANCEL_OPTION)
				return;
			else {
				File f = fc.getSelectedFile();
				// We have the file. Now we need to read the file and count the words
				TextFileInput tfi = new TextFileInput(f.getPath());
				String line = tfi.readLine();// Create a pattern to match breaks
				TreeMap<String, Integer> collectionOfWords = new TreeMap<String, Integer>();
				while(line!=null){
					MyGUI.addInputLine(line + "\n");
					Pattern p = Pattern.compile("[a-zA-Z]+");
					Matcher m = p.matcher(line);
					while (m.find()) {
						String key = m.group().toUpperCase();
						if(collectionOfWords.containsKey(key)){
							int occurence = collectionOfWords.get(key);
							occurence++;
							collectionOfWords.put(key, occurence);
						} else {
							collectionOfWords.put(key, 1);
						}
					}
					line = tfi.readLine();
				}
				MyGUI.printToOutput(collectionOfWords);
			}
			
			/**
			 * This is for the "quit" item. This will terminate the program when
			 * the user clicks "quit"
			 */

		} else if (menuName.equals("Quit")) {
			boolean quit = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to quit?", "Make your selection",
					JOptionPane.YES_NO_OPTION) == 0;
			if (quit)
				System.exit(0);
			return;
		} else if (menuName.equals("About")) {
			JOptionPane.showMessageDialog(null,
					"Temperature in Object Array and Linked List\n"
							+ "Project 4\n\n"
							+ "©2014 Contributors. All rights reserved.\n"
							+ "CS212 Dr. K. Lord's Java\n"
							+ "All rights reserved.");
		}
		else {
				try {
					
				 
				} catch (Exception e) {
					JOptionPane
							.showMessageDialog(null,
									"There was an exception.");
				}
			}

		}
	} // actionPerformed
