package org.joutliner.view;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

public class TreeOutline extends JPanel {
	private DefaultMutableTreeNode rootNode;
	private DefaultTreeModel treeModel;
	private JTree tree;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();

	public TreeOutline() {
		super(new GridLayout(1,0)); // Makes sure that the left panel is completely filled

		this.rootNode = new DefaultMutableTreeNode("Root Node");
		this.treeModel = new DefaultTreeModel(this.rootNode);
		this.tree = new JTree(this.treeModel);
		this.tree.setEditable(true);
		this.tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.tree.setShowsRootHandles(true);

		this.add(new JScrollPane(this.tree));
	}

	public void removeSelectedNode() {
		TreePath currentSelection = this.tree.getSelectionPath();

		if (currentSelection == null) { // Nothing selected
			this.toolkit.beep();
			return;
		}

		DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)currentSelection.getLastPathComponent();
		MutableTreeNode parent = (MutableTreeNode)currentNode.getParent();

		if (parent == null) { // Root node selected
			this.toolkit.beep();
			return;
		}

		this.treeModel.removeNodeFromParent(currentNode);
	}

	public DefaultMutableTreeNode addObject(Object child) {
		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = this.tree.getSelectionPath();

		if (parentPath == null) {
			parentNode = this.rootNode;
		}
		else {
			parentNode = (DefaultMutableTreeNode)parentPath.getLastPathComponent();
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

		this.treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

		if (shouldBeVisible) {
			this.tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		}

		return childNode;
	}

	// Is this needed?
	// Renamed to TreeModelHandler; not sure if that makes sense.
	class TreeModelHandler implements TreeModelListener {
		public void treeNodesChanged(TreeModelEvent evt) {
			System.out.println("in MyTreeModelListener");
			DefaultMutableTreeNode node;
			node = (DefaultMutableTreeNode)evt.getTreePath().getLastPathComponent();
			int index = evt.getChildIndices()[0];
			node = (DefaultMutableTreeNode)node.getChildAt(index);
        }
		public void treeNodesInserted(TreeModelEvent evt) {}
		public void treeNodesRemoved(TreeModelEvent evt) {}
		public void treeStructureChanged(TreeModelEvent evt) {}
	}
}
