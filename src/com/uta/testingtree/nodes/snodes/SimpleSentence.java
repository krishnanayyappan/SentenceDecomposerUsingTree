package com.uta.testingtree.nodes.snodes;

import com.uta.testingtree.core.Node;
import com.uta.testingtree.core.Tree;

public class SimpleSentence extends Tree {

	public SimpleSentence() {
		super();
	}

	@Override
	public String processNode(Node currentNode) {
		String tempWordsOfParentNode = wordsInNodeMapping;
		String tempNodeChildrenString = nodeMappingString.trim();
		
		String[] stringArrayOfWords = tempWordsOfParentNode.split("#");
		String simpleSentence = "";
		for(int i=0; i<stringArrayOfWords.length;i++) {
			if(i==0) {
				simpleSentence = stringArrayOfWords[i];
			} else {
				simpleSentence = simpleSentence +" "+stringArrayOfWords[i];
			}
		}	
		
		if(!tempNodeChildrenString.contains("NP")) {
			System.out.println( "Root is : "+ simpleSentence);
			buildSimpleSentences(simpleSentence);
			
			for(int i=0; i<finalSimpleSentences.size();i++) 
				System.out.println(finalSimpleSentences.get(i));
			finalSimpleSentences.removeAll(finalSimpleSentences);
			
			return null;
		}
			
		return simpleSentence;
	}
	
	public void buildSimpleSentences(String resultFromProcessedNode) {
		String[] textProcessing = resultFromProcessedNode.split("\\|");
		System.out.println("\n\n");
		String[] partsOfTextProcessing = null;
		String resultToken;
		for(int i=0; i<textProcessing.length;i++) {
			//System.out.println(textProcessing[i]);
			partsOfTextProcessing = textProcessing[i].split("::");
			
			int countOfSimpleSentences = finalSimpleSentences.size();
			if(partsOfTextProcessing.length > 1) {
				if(countOfSimpleSentences>0) {
					copyUnbuiltSentencesInArrayList(partsOfTextProcessing.length-1, countOfSimpleSentences);
					int count = 0;
					for(int j=0; j<partsOfTextProcessing.length; j++) {
						resultToken = partsOfTextProcessing[j];
						String tempString;
						for(int k=0; k<countOfSimpleSentences; k++) {
							tempString = finalSimpleSentences.get(k);
							finalSimpleSentences.remove(count);
							finalSimpleSentences.add(count, tempString+ " " +resultToken);
							count++;
						}
					}
				} else {
					for(int j=0; j<partsOfTextProcessing.length; j++)  {
						finalSimpleSentences.add(partsOfTextProcessing[j]);
					}
				}
			} else if(partsOfTextProcessing.length == 1) {

				resultToken = partsOfTextProcessing[0];
				if(countOfSimpleSentences>0) {
					String tempString;
					for(int j=0; j<countOfSimpleSentences; j++) {
						tempString = finalSimpleSentences.get(j);
						finalSimpleSentences.remove(j);
						finalSimpleSentences.add(j, tempString+ " " +resultToken);
					}
				} else {
					finalSimpleSentences.add(resultToken);
				}
			
				
			}
		}
	}
	
	private void copyUnbuiltSentencesInArrayList(int countToduplicateUnBuiltSentences, int countOfSimpleSentences) {
		for(int i=0; i<countToduplicateUnBuiltSentences; i++) {
			for(int j=0; j<countOfSimpleSentences; j++) {
				finalSimpleSentences.add(finalSimpleSentences.get(j));
			}
		}
	}


}
