package org.joutliner.view;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;

public class OutlineNode {
	private short level;
	private String name;
	private String content;
	private OutlineNode parent;
	private Vector<OutlineNode> children;

	public OutlineNode() {
		this.level = 0;
		this.name = null;
		this.content = null;
		this.parent = null;
		this.children = new Vector<OutlineNode>();
	}

	public OutlineNode(String name) {
		this.name = name;
		this.parent = null;
		this.children = new Vector<OutlineNode>();
	}

	// Getters
	public short getLevel() {
		return this.level;
	}

	public String getName() {
		return this.name;
	}

	public String getContent() {
		return this.content;
	}

	public OutlineNode getParent() {
		return this.parent;
	}

	public Vector<OutlineNode> getChildren() {
		return this.children;
	}

	public OutlineNode getChild(int i) {
		return this.children.get(i);
	}

	@Override
	public String toString() {
		return this.name;
	}

	// Setters
	public void setLevel(short level) {
		this.level = level;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setParent(OutlineNode parent) {
		this.parent = parent;
	}

	// Operations
	public void incrementLevel() {
		++this.level;
	}

	public void decrementLevel() {
		--this.level;
	}
}
