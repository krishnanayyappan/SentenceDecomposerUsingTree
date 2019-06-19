package com.uta.testingtree.nodes.npnodes;

import com.uta.testingtree.core.Node;

public class ConjunctiveNP extends Node {

	public ConjunctiveNP() {
		super();
	}

	@Override
	public String processNode() {
		String tempNodeChildrenString = nodeMappingString.trim();
		String tempWordsOfParentNode = wordsInNodeMapping.trim();
		
		String[] stringArrayOfChildNodes = tempNodeChildrenString.trim().split("\\(");
		if(tempWordsOfParentNode.substring(0, 1).equals("#")) {
			tempWordsOfParentNode = tempWordsOfParentNode.substring(1);
		}
		String[] stringArrayOfWords = tempWordsOfParentNode.split("#");
		boolean foundCC = false;
		
		String nounPhrase = "";
		for(int i=2; i<stringArrayOfChildNodes.length;i++) {
			if(!stringArrayOfChildNodes[i].trim().equals("CC")) {
				if(i==2) {
					nounPhrase = stringArrayOfWords[i-2];
				} else if(foundCC) {
					nounPhrase = nounPhrase+stringArrayOfWords[i-2];
					foundCC = false;
				} else {
					nounPhrase = nounPhrase +" "+stringArrayOfWords[i-2];
				}
			} else {
				nounPhrase+="::";
				foundCC = true;
			}
		}	
		return nounPhrase;
	}
}
