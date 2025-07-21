package org.joutliner.main;

import java.util.*;

import org.joutliner.view.*;
import org.joutliner.model.*;

public class MainJOutliner {
	public static void main(String[] args) {
		System.out.println("Init");
		Model model = new Model();
		View view = new View();
		view.setVisible(true);
	}
}
