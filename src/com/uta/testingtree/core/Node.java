package com.uta.testingtree.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class Node {
	
	static String fileName = null;
	protected static Stack<String> stack=new Stack<String>();
	
	protected static String nodeMappingString = "";
	protected static String wordsInNodeMapping = "";
	
	protected static List<String> finalSimpleSentences = new ArrayList<String>();
	
  
	public abstract String processNode(); 
	
	
}
