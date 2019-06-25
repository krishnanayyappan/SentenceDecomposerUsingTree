package com.uta.testingtree.nodes.npnodes;

import com.uta.testingtree.core.Node;
import com.uta.testingtree.core.Tree;

public class NounPhrase extends Tree {

	public NounPhrase() {
		super();
	}

	@Override
	public String processNode(Node currentNode) {
		
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
