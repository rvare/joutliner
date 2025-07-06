package org.joutliner.view;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

public class TreeOutline extends JPanel {
	protected DefaultMutableTreeNode rootNode;
	protected DefaultTreeModel treeModel;
	protected JTree tree;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();

	public TreeOutline() {
		super(new GridLayout(1,0)); // Makes sure that the left panel is completely filled

		this.rootNode = new DefaultMutableTreeNode("Root Node");
		this.treeModel = new DefaultTreeModel(this.rootNode);
		this.tree = new JTree(this.treeModel);
		this.tree.setEditable(true);
		this.tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setShowsRootHandles(true);

		this.add(new JScrollPane(tree));
	}

	public void removeSelectedNode() { // TODO: Remove nesting
		TreePath currentSelection = tree.getSelectionPath();
		if (currentSelection != null) {
			DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)(currentSelection.getLastPathComponent());
			MutableTreeNode parent = (MutableTreeNode)(currentNode.getParent());
			if (parent != null) {
				treeModel.removeNodeFromParent(currentNode);
				return;
			}
		}

		toolkit.beep(); // No selection or root was selected
	}

	public DefaultMutableTreeNode addObject(Object child) {
		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = tree.getSelectionPath();

		if (parentPath == null) {
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
			parent = rootNode;
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
