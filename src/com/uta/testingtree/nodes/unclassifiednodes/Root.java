package com.uta.testingtree.nodes.unclassifiednodes;

import com.uta.testingtree.core.Node;

public class Root extends Node {

	public Root() {
		super();
	}

	@Override
	public String processNode() {
		String tempWordsOfParentNode = wordsInNodeMapping;
		
		String[] stringArrayOfWords = tempWordsOfParentNode.split("#");
		String rootPhrase = "";
		for(int i=0; i<stringArrayOfWords.length;i++) {
			if(i==0) {
				rootPhrase = stringArrayOfWords[i];
			} else {
				rootPhrase = rootPhrase +" "+stringArrayOfWords[i];
			}
		}	
		return rootPhrase;
	}


}
