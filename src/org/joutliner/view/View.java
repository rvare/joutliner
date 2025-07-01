package org.joutliner.view;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create buttons
		this.newButton = new JButton("New");
		this.newButton.setActionCommand("add");
		this.deleteButton = new JButton("Delete");
		this.deleteButton.setActionCommand("remove");

		// Create button pane
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(this.newButton);
		buttonPanel.add(this.deleteButton);
		this.getContentPane().add(BorderLayout.NORTH, buttonPanel);

		// Create split panel
		// Left panel
		this.rootNode = new DefaultMutableTreeNode("Root Node");
		this.treeModel = new DefaultTreeModel(this.rootNode);
		this.tree = new JTree(this.treeModel);
		this.tree.setEditable(true);
		this.tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.tree.setShowsRootHandles(true);

		JPanel leftPanel = new JPanel();
		// leftPanel.add(new JLabel("Left"));
		leftPanel.add(this.tree);

		// Right panel
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

	public void clear() {
		this.rootNode.removeAllChildren();
		this.treeModel.reload();
	}

	public void removeCurrentNode() {
		TreePath currentSelection = this.tree.getSelectionPath();
		if (currentSelection != null) {
			DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)(currentSelection.getLastPathComponent());
			MutableTreeNode parent = (MutableTreeNode)(currentNode.getParent());
			if (parent != null) {
				this.treeModel.removeNodeFromParent(currentNode);
				return;
			}
		}
		toolKit.beep();
	}

	public DefaultMutableTreeNode addObject(Object child) {
		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = tree.getSelectionPath();

		if (parentNode == null) {
			parentNode = rootNode;
		}
		else {
			parentNode = (DefaultMutableTreeNode)(parentPath.getLastPathComponent());
		}

		return addObject(parentNode, child, true);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child) {
		return addObject(parent, child, false);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child, boolean shouldBeVisible) {
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

		if (parent == null) {
			parent = this.rootNode;
		}

		treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

		if (shouldBeVisible) {
			tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		}
		return childNode;
	}

	class MyTreeModelListener implements TreeModelListener {
		public void treeNodesChanged(TreeModelEvent e) {
			DefaultMutableTreeNode node;
			node = (DefaultMutableTreeNode)(e.getTreePath().getLastPathComponent());
			int index = e.getChildIndices()[0];
			node = (DefaultMutableTreeNode)(node.getChildAt(index));
        }
		public void treeNodesInserted(TreeModelEvent e) {}
		public void treeNodesRemoved(TreeModelEvent e) {}
		public void treeStructureChanged(TreeModelEvent e) {}
	}
}
