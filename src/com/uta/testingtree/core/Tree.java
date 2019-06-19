package com.uta.testingtree.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.uta.classmappinggenerator.util.FileHelper;

public class Tree extends Node {
	
	static Map<String, Node> nodeObjectMap = new HashMap<>();
	Tag rootNode;
	String resultFromProcessedNode = "";
	List<String> finalSimpleSentencesList = new ArrayList<String>();
	
	List<String> unclassifiedNodesList = new ArrayList<String>();
	
	String tagsString = "";
	String value = "";
	
	public Tree(Tag rootNode) {
		this.rootNode = rootNode;
	}
	
	@Override
	public String processNode() {
		
		unclassifiedNodesList.add("PRN");
		unclassifiedNodesList.add("PP");
		unclassifiedNodesList.add("ROOT");
	    
		rootNode.tagName = rootNode.name;
		helper4(rootNode);
		System.out.println( "Root is : "+ rootNode.value);
		buildSimpleSentences (rootNode.value);
		
		for(int i=0; i<finalSimpleSentences.size();i++) 
			System.out.println(finalSimpleSentences.get(i));
		
		
		return null;
	}
	
	public void helper4(Tag root) {
		
    	if(root==null)
    		return;
    	
    	int childrenSize = root.children.size();
    	
    	if(childrenSize>0) {
    		if(root.parent != null)
    			root.tagName = root.name;
    		tagsString=root.name;
    		for(Tag child : root.children) {
    			helper4(child);
    		} 
    		
    		if(!root.value.equals("")) {
	    		
	    		tagsString = root.tagName;
	    		value = root.value;
	    		nodeMappingString = tagsString.trim();
	    		wordsInNodeMapping = value.trim();
	    		resultFromProcessedNode = processParentNode();
	    		
	    		//System.out.println( root.tagName +"    ###    "+ resultFromProcessedNode);
	    		
	    		if(root.parent != null && root.hasChildren()) {
	    			if(rootHasNonLeafChildren(root.parent)) {
	    				if(!root.parent.value.equals(""))
	    					root.parent.value = root.parent.value + "|" + resultFromProcessedNode;
	    	    		else
	    	    			root.parent.value = resultFromProcessedNode;
	    			} else {
	    				if(!root.parent.value.equals(""))
	    					root.parent.value = root.parent.value + "#" + resultFromProcessedNode;
	    				else
	    	    			root.parent.value = resultFromProcessedNode;
	    			}
    				root.value = root.value + resultFromProcessedNode;
    				root.tagName = root.tagName;
    				root.parent.tagName = root.parent.tagName+ " " +root.name;
    				
    			}
	    		if(root.parent != null)
	    			System.out.println( root.tagName +"    ###    "+ root.parent.value);
	    		
	    		value = "";
	    		tagsString = "";
    		}
    	} else {
    		if(rootHasNonLeafChildren(root.parent)) {
				if(!root.parent.value.equals(""))
					root.parent.value = root.parent.value + "|" + root.value;
	    		else
	    			root.parent.value = root.value;
			} else {
				if(!root.parent.value.equals(""))
					root.parent.value = root.parent.value + "#" + root.value;
				else
	    			root.parent.value = root.value;
			}
    		/*if(!root.parent.value.equals(""))
    			root.parent.value = root.parent.value + "#" + root.value;
    		else
    			root.parent.value = root.value;*/
    		root.parent.tagName = root.parent.tagName+ " " +root.name;
    	}
    	
    	
    }
	
	private boolean rootHasNonLeafChildren(Tag root) {
		if(root.hasChildren())
			for(Tag child : root.children) {
				if(child.hasChildren())
					return true;
			}
		return false;
	}

	static Tag lca = null;
	 
	private Tag leastCommonAncestor(Tag root, Tag node1, Tag node2)
	{
		if(node1.equals(root) || node2.equals(root))
			return root;
			
		int count = 0;
		lca = null;
		Tag res = null;
		for(Tag child : root.children)
		{
			res = leastCommonAncestor(child, node1, node2);
			if(res != null)
			{
				count++;
				lca = res;
			}
		}
		
		if(count == 2)
			return root;
			
		return lca;
	}
	
	private void buildSimpleSentences(String resultFromProcessedNode) {
		String[] textProcessing = rootNode.value.split("\\|");
		System.out.println("\n\n");
		String[] partsOfTextProcessing = null;
		String resultToken;
		for(int i=0; i<textProcessing.length;i++) {
			//System.out.println(textProcessing[i]);
			partsOfTextProcessing = textProcessing[i].split("::");
			
			int countOfSimpleSentences = finalSimpleSentences.size();
			if(partsOfTextProcessing.length > 1) {
				if(countOfSimpleSentences>0) {
					copyUnbuiltSentencesInArrayList(partsOfTextProcessing.length-1, countOfSimpleSentences);
					int count = 0;
					for(int j=0; j<partsOfTextProcessing.length; j++) {
						resultToken = partsOfTextProcessing[j];
						String tempString;
						for(int k=0; k<countOfSimpleSentences; k++) {
							tempString = finalSimpleSentences.get(k);
							finalSimpleSentences.remove(count);
							finalSimpleSentences.add(count, tempString+ " " +resultToken);
							count++;
						}
					}
				} else {
					for(int j=0; j<partsOfTextProcessing.length; j++)  {
						finalSimpleSentences.add(partsOfTextProcessing[j]);
					}
				}
			} else if(partsOfTextProcessing.length == 1) {

				resultToken = partsOfTextProcessing[0];
				if(countOfSimpleSentences>0) {
					String tempString;
					for(int j=0; j<countOfSimpleSentences; j++) {
						tempString = finalSimpleSentences.get(j);
						finalSimpleSentences.remove(j);
						finalSimpleSentences.add(j, tempString+ " " +resultToken);
					}
				} else {
					finalSimpleSentences.add(resultToken);
				}
			
				
			}
		}
	}

	private void copyUnbuiltSentencesInArrayList(int countToduplicateUnBuiltSentences, int countOfSimpleSentences) {
		for(int i=0; i<countToduplicateUnBuiltSentences; i++) {
			for(int j=0; j<countOfSimpleSentences; j++) {
				finalSimpleSentences.add(finalSimpleSentences.get(j));
			}
		}
	}

	private String processParentNode() {
		String result = "";
		Node node = getClassNameBasedOnNodeChildrenString();
		if(node!=null) {
			result = node.processNode();
		}
		return result;
	}

	private Node getClassNameBasedOnNodeChildrenString() {
		FileHelper fileHelper = new FileHelper();
		String outputFilename = "\\cse5328-training-data.arff";
		String parentNode = nodeMappingString.substring(1, (nodeMappingString.indexOf("(", 2)-1));
		
		String className = fileHelper.getClassNameFromMapping(nodeMappingString, "result", outputFilename);
		if(!className.equals("")) {
			return getNode(parentNode, className);
		}
		return null;
	}
	
	public Node getNode(String parentNode, String className) {
		
		Node nodeObject = nodeObjectMap.get(className);
		if(nodeObject!=null) {
			return nodeObject;
		}
		try {
			if(!unclassifiedNodesList.contains(parentNode)) {
				parentNode = parentNode.toLowerCase();
			} else {
				parentNode = "unclassified";
			}
			nodeObject = (Node) Class.
					forName("com.uta.testingtree.nodes."+parentNode+"nodes."+className).
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
