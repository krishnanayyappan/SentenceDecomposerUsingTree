package com.uta.testingtree.nodes.unclassifiednodes;

import com.uta.testingtree.core.Node;

public class PrepositionPhrase extends Node {

	public PrepositionPhrase() {
		super();
	}

	@Override
	public String processNode() {
		String tempWordsOfParentNode = wordsInNodeMapping;
		
		String[] stringArrayOfWords = tempWordsOfParentNode.split("#");
		String prepositionPhrase = "";
		for(int i=0; i<stringArrayOfWords.length;i++) {
			if(i==0) {
				prepositionPhrase = stringArrayOfWords[i];
			} else {
				prepositionPhrase = prepositionPhrase +" "+stringArrayOfWords[i];
			}
		}	
		return prepositionPhrase;
	}

}
