package org.joutliner.view;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;

public class Editor extends JPanel {
	private JTextArea textEditor;

	public Editor() {
		super(new GridLayout(1,0)); // Makes sure that the right panel is completely filled
		this.textEditor = new JTextArea();
		this.textEditor.setLineWrap(true);
		this.add(new JScrollPane(this.textEditor));
	}

	// Getters
	public JTextArea getTextEditor() {
		return this.textEditor;
	}

	public String getTextEditorContent() { // Could be transferred to an interface
		return this.textEditor.getText();
	}

	// Setters
	public void setTextEditorContent(String content) {
		this.textEditor.setText(content);
	}
}
