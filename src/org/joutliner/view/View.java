package org.joutliner.view;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import org.joutliner.view.TreeOutline;
import org.joutliner.view.Editor;

public class View extends JFrame implements ActionListener {
	private DefaultMutableTreeNode rootNode;
	private DefaultTreeModel treeModel;
	private JTree tree;
	private Toolkit toolKit;

	private TreeOutline treeOutline;

	private Editor editor;

	private JMenuBar menuBar;
	private JMenu menu;

	private JMenuItem openFileItem;
	private JMenuItem newFileItem;
	private JMenuItem saveFileItem;
	private JMenuItem saveAsFileItem;
	private JMenuItem exportItem;
	private JMenuItem aboutItem;
	private JMenuItem manualItem;

	private JButton newButton;
	private JButton deleteButton;

	private static final short DEFAULT_WIDTH = 500;
	private static final short DEFAULT_HEIGHT = 500;
	private static final String TITLE = "JOutliner";

	private static String ADD_COMMAND = "add";
	private static String REMOVE_COMMAND = "remove";

	public View() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create menu bar
		this.menuBar = new JMenuBar();
		this.setJMenuBar(this.menuBar);

		// Create file menu and its item
		JMenu fileMenu = new JMenu("File");
		this.menuBar.add(fileMenu);

		this.openFileItem = new JMenuItem("Open");
		fileMenu.add(this.openFileItem);

		this.newFileItem = new JMenuItem("New");
		fileMenu.add(this.newFileItem);

		this.saveFileItem = new JMenuItem("Save");
		fileMenu.add(this.saveFileItem);

		this.saveAsFileItem = new JMenuItem("Save As");
		fileMenu.add(this.saveAsFileItem);

		this.exportItem = new JMenuItem("Export");
		fileMenu.add(this.exportItem);

		// Create help menu
		JMenu helpMenu = new JMenu("Help");
		this.menuBar.add(helpMenu);

		this.manualItem = new JMenuItem("Manual");
		helpMenu.add(this.manualItem);

		this.aboutItem = new JMenuItem("About");
		helpMenu.add(this.aboutItem);

		// Create buttons
		this.newButton = new JButton("New");
		this.newButton.setActionCommand(ADD_COMMAND);
		this.newButton.addActionListener(this);

		this.deleteButton = new JButton("Delete");
		this.deleteButton.setActionCommand(REMOVE_COMMAND);
		this.deleteButton.addActionListener(this);

		// Create button pane
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(this.newButton);
		buttonPanel.add(this.deleteButton);
		this.getContentPane().add(BorderLayout.NORTH, buttonPanel);

		// Create split panel
		// Left panel
		this.treeOutline = new TreeOutline();

		// Right panel
		this.editor = new Editor();

		// Establish splitpane
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.treeOutline, this.editor);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(250);
		this.getContentPane().add(splitPane);

		// Finish frame
		this.setTitle(this.TITLE);
		this.setSize(this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);
	} // End constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (ADD_COMMAND.equals(command)) {
			System.out.println("New command");
			this.treeOutline.addObject("New Node");
		}
		else if(REMOVE_COMMAND.equals(command)) {
			System.out.println("Remove command");
			this.treeOutline.removeSelectedNode();
		}
	}
}
