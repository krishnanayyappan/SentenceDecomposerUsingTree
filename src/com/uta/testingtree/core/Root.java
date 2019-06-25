package com.uta.testingtree.core;

import java.util.ArrayList;
import java.util.List;

public class Root extends Tree {
	
	
	Node rootNode;
	List<String> finalSimpleSentencesList = new ArrayList<String>();
	
	String tagsString = "";
	String result = "";
	
	Tree subTree = null;
	
	@Override
	public String processNode(Node node) {
		
		this.rootNode = node;
		
		unclassifiedNodesList.add("PRN");
		unclassifiedNodesList.add("PP");
		unclassifiedNodesList.add("ROOT");
	    
		rootNode.tagName = rootNode.name;		
		
		if(rootNode==null)
    		return null;		
		
		int childrenSize = rootNode.children.size();
    	if(childrenSize>0) {    		
    		tagsString=rootNode.name;
    		
    		for(Node child : rootNode.children) {
    			subTree = getSimpleClassNameForChild(child);
    			if(subTree!=null) {
    				result = result + " " + subTree.processNode(child);
    			}
    			tagsString = tagsString + " " +child.name.trim();
    		} 
    		nodeMappingString = tagsString.trim();
    		wordsInNodeMapping = result.trim();
    		subTree = getClassNameBasedOnNodeChildrenString();
    		if(subTree!=null) {
    			return subTree.processNode(rootNode);
    		}
    	}
		System.out.println( "Root is : "+ rootNode.value);
		buildSimpleSentences (rootNode.value);
		
		for(int i=0; i<finalSimpleSentences.size();i++) 
			System.out.println(finalSimpleSentences.get(i));		
		
		return null;
	}
	
	public void buildSimpleSentences(String resultFromProcessedNode) {
		String[] textProcessing = rootNode.value.split("\\|");
		System.out.println("\n\n");
		String[] partsOfTextProcessing = null;
		String resultToken;
		for(int i=0; i<textProcessing.length;i++) {
			//System.out.println(textProcessing[i]);
			partsOfTextProcessing = textProcessing[i].split("::");
			
			int countOfSimpleSentences = finalSimpleSentences.size();
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
		}
	}

	private void copyUnbuiltSentencesInArrayList(int countToduplicateUnBuiltSentences, int countOfSimpleSentences) {
		for(int i=0; i<countToduplicateUnBuiltSentences; i++) {
			for(int j=0; j<countOfSimpleSentences; j++) {
				finalSimpleSentences.add(finalSimpleSentences.get(j));
			}
		}
	}
}
