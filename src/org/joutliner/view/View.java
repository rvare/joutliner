package org.joutliner.view;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import org.joutliner.view.TreeOutline;

public class View extends JFrame implements ActionListener {
	private DefaultMutableTreeNode rootNode;
	private DefaultTreeModel treeModel;
	private JTree tree;
	private Toolkit toolKit;

	private TreeOutline treeOutline;

	private JButton newButton;
	private JButton deleteButton;

	private static short DEFAULT_WIDTH = 500;
	private static short DEFAULT_HEIGHT = 500;
	private static String TITLE = "JOutliner";

	private static String ADD_COMMAND = "add";
	private static String REMOVE_COMMAND = "remove";

	public View() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		JPanel rightPanel = new JPanel();
		rightPanel.add(new JLabel("Right"));

		// Establish splitpane
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.treeOutline, rightPanel);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(250);
		this.getContentPane().add(splitPane);

		// Finish frame
		this.setTitle(this.TITLE);
		this.setSize(this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);
	}

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
