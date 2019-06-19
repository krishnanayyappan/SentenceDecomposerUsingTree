package com.uta.testingtree.nodes.snodes;

import com.uta.testingtree.core.Node;

public class SimpleSentence extends Node {

	public SimpleSentence() {
		super();
	}

	@Override
	public String processNode() {
		String tempWordsOfParentNode = wordsInNodeMapping;
		
		String[] stringArrayOfWords = tempWordsOfParentNode.split("#");
		String simpleSentence = "";
		for(int i=0; i<stringArrayOfWords.length;i++) {
			if(i==0) {
				simpleSentence = stringArrayOfWords[i];
			} else {
				simpleSentence = simpleSentence +" "+stringArrayOfWords[i];
			}
		}	
		return simpleSentence;
	}

}
