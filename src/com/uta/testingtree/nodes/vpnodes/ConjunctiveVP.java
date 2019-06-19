package com.uta.testingtree.nodes.vpnodes;

import com.uta.testingtree.core.Node;

public class ConjunctiveVP extends Node {

	public ConjunctiveVP() {
		super();
	}

	@Override
	public String processNode() {
		String tempNodeChildrenString = nodeMappingString.trim();
		String tempWordsOfParentNode = wordsInNodeMapping.trim();
		
		if(tempWordsOfParentNode.substring(0, 1).equals("#")) {
			tempWordsOfParentNode = tempWordsOfParentNode.substring(1);
		}
		
		
		
		String[] stringArrayOfChildNodes = tempNodeChildrenString.trim().split("\\(");
		String[] stringArrayOfWords = null;
		
		if(tempWordsOfParentNode.contains("#")) {
			if(tempWordsOfParentNode.substring(0, 1).equals("#")) {
				tempWordsOfParentNode = tempWordsOfParentNode.substring(1);
			}
			stringArrayOfWords = tempWordsOfParentNode.split("#");
		}
		if(tempWordsOfParentNode.contains("|")) {
			if(tempWordsOfParentNode.substring(0, 1).equals("|")) {
				tempWordsOfParentNode = tempWordsOfParentNode.substring(1);
			}
			stringArrayOfWords = tempWordsOfParentNode.split("\\|", stringArrayOfChildNodes.length-2);
		}
		
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
					nounPhrase = nounPhrase +"|"+stringArrayOfWords[i-2];
				}
			} else {
				nounPhrase+="::";
				foundCC = true;
			}
		}
		
		/*if(stringArrayOfWords.length > stringArrayOfChildNodes.length) {
			for(int i=stringArrayOfChildNodes.length; i<stringArrayOfWords.length; i++) {
				nounPhrase+=
			}
		}*/
		
		return nounPhrase;
	}

}
