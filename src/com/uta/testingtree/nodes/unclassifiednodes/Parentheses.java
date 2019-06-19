package com.uta.testingtree.nodes.unclassifiednodes;

import com.uta.testingtree.core.Node;

public class Parentheses extends Node {

	public Parentheses() {
		super();
	}

	@Override
	public String processNode() {
		String tempWordsOfParentNode = wordsInNodeMapping;
		
		String[] stringArrayOfWords = tempWordsOfParentNode.split("#");
		String parenthesesString = "";
		for(int i=0; i<stringArrayOfWords.length;i++) {
			if(i==0) {
				parenthesesString = stringArrayOfWords[i];
			} else {
				parenthesesString = parenthesesString +" "+stringArrayOfWords[i];
			}
		}	
		return parenthesesString;
	}
}
