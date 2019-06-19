package com.uta.testingtree.nodes.npnodes;

import com.uta.testingtree.core.Node;

public class NounPhrase extends Node {

	public NounPhrase() {
		super();
	}

	@Override
	public String processNode() {
		
		String tempWordsOfParentNode = wordsInNodeMapping;
		
		if(tempWordsOfParentNode.substring(0, 1).equals("#")) {
			tempWordsOfParentNode = tempWordsOfParentNode.substring(1);
		}
		
		String[] stringArrayOfWords = tempWordsOfParentNode.split("#");
		String nounPhrase = "";
		for(int i=0; i<stringArrayOfWords.length;i++) {
			if(i==0) {
				nounPhrase = stringArrayOfWords[i];
			} else {
				nounPhrase = nounPhrase +" "+stringArrayOfWords[i];
			}
		}	
		return nounPhrase;
	}
}
