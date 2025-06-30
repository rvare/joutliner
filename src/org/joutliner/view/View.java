package org.joutliner.view;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;

public class View extends JFrame {
	private DefaultMutableTreeNode rootNode;
	private DefaultTreeModel treeModel;
	private JTree tree;
	private Toolkit toolKit;

	private JButton newButton;
	private JButton deleteButton;

	private static short DEFAULT_WIDTH = 500;
	private static short DEFAULT_HEIGHT = 500;
	private static String TITLE = "JOutliner";

	public View() {
		// Create buttons
		this.newButton = new JButton("New");
		this.deleteButton = new JButton("Delete");

		// Create button pane
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(this.newButton);
		buttonPanel.add(this.deleteButton);
		this.getContentPane().add(BorderLayout.NORTH, buttonPanel);

		// Create split panel
		// Left panel
		JPanel leftPanel = new JPanel();
		leftPanel.add(new JLabel("Left"));

		// Left panel
		JPanel rightPanel = new JPanel();
		rightPanel.add(new JLabel("Right"));

		// Establish splitpane
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(250);
		this.getContentPane().add(splitPane);

		// Finish frame
		this.setTitle(this.TITLE);
		this.setSize(this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT);
	}
}
