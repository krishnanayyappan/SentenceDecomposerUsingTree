package com.uta.testingtree.nodes.unclassifiednodes;

import com.uta.testingtree.core.Node;
import com.uta.testingtree.core.Tree;

public class Parentheses extends Tree {

	public Parentheses() {
		super();
	}

	@Override
	public String processNode(Node currentNode) {
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
