package com.uta.testingtree.nodes.unclassifiednodes;

import com.uta.testingtree.core.Node;
import com.uta.testingtree.core.Tree;

public class Root extends Tree {

	public Root() {
		super();
	}

	@Override
	public String processNode(Node currentNode) {
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
