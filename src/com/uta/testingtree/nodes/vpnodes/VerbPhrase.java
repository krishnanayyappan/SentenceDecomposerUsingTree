package com.uta.testingtree.nodes.vpnodes;

import com.uta.testingtree.core.Node;

public class VerbPhrase extends Node {

	public VerbPhrase() {
		super();
	}

	@Override
	public String processNode() {
		String tempWordsOfParentNode = wordsInNodeMapping;
		
		if(tempWordsOfParentNode.substring(0, 1).equals("#")) {
			tempWordsOfParentNode = tempWordsOfParentNode.substring(1);
		}
		
		String[] stringArrayOfWords = tempWordsOfParentNode.split("#");
		String verbPhrase = "";
		for(int i=0; i<stringArrayOfWords.length;i++) {
			if(i==0) {
				verbPhrase = stringArrayOfWords[i];
			} else {
				verbPhrase = verbPhrase +" "+stringArrayOfWords[i];
			}
		}	
		return verbPhrase;
	}
}
