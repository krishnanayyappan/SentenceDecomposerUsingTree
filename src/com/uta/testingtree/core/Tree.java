package com.uta.testingtree.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.uta.classmappinggenerator.util.FileHelper;

public abstract class Tree {
	
	static Map<String, Tree> nodeObjectMap = new HashMap<>();
	
	static String fileName = null;
	protected static Stack<String> stack=new Stack<String>();
	
	protected static String nodeMappingString = "";
	protected static String wordsInNodeMapping = "";
	
	protected static List<String> finalSimpleSentences = new ArrayList<String>();	
	List<String> unclassifiedNodesList = new ArrayList<String>();
  
	public abstract String processNode(Node node);
	
	public Tree getSimpleClassNameForChild(Node node){
		FileHelper fileHelper = new FileHelper();
		String outputFilename = "\\cse5328-training-data.arff";
		String nodeToProcess = node.name.trim();
		
		String className = fileHelper.getClassNameFromMapping(nodeToProcess, "result", outputFilename);
		if(!className.equals("")) {
			return getNode(nodeToProcess, className);
		}
		return null;
	}
	
	public Tree getClassNameBasedOnNodeChildrenString() {
		FileHelper fileHelper = new FileHelper();
		String outputFilename = "\\cse5328-training-data.arff";
		String nodeToProcess = nodeMappingString.substring(1, (nodeMappingString.indexOf("(", 2)-1));
		
		String className = fileHelper.getClassNameFromMapping(nodeMappingString, "result", outputFilename);
		if(!className.equals("")) {
			return getNode(nodeToProcess, className);
		}
		return null;
	}
	public Tree getNode(String nodeToProcess, String className) {
		
		Tree nodeObject = nodeObjectMap.get(className);
		if(nodeObject!=null) {
			return nodeObject;
		}
		try {
			if(nodeToProcess.contains("(")) {
				nodeToProcess = "simple";
			} else {
				if(!unclassifiedNodesList.contains(nodeToProcess)) {
					nodeToProcess = nodeToProcess.toLowerCase();
				} else {
					nodeToProcess = "unclassified";
				}
			}
			nodeObject = (Tree) Class.
					forName("com.uta.testingtree.nodes."+nodeToProcess+"nodes."+className).
					newInstance();
			nodeObjectMap.put(className, nodeObject);
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} 
		return nodeObject;
	}
	
}
