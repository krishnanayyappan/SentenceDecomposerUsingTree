package com.uta.testingtree.rootbackup;

import com.uta.testingtree.core.Node;
import com.uta.testingtree.core.Tree;

public class Root_backup extends Tree {

	@Override
	public String processNode(Node node) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*static Map<String, Tree> nodeObjectMap = new HashMap<>();
	Node rootNode;
	String resultFromProcessedNode = "";
	List<String> finalSimpleSentencesList = new ArrayList<String>();
	
	List<String> unclassifiedNodesList = new ArrayList<String>();
	
	String tagsString = "";
	String value = "";
	String result = "";
	
	Tree node = null;
	
	public Root(Node rootNode) {
		this.rootNode = rootNode;
	}
	
	@Override
	public String processNode(Node node) {
		
		unclassifiedNodesList.add("PRN");
		unclassifiedNodesList.add("PP");
		unclassifiedNodesList.add("ROOT");
	    
		rootNode.tagName = rootNode.name;		
		
		if(rootNode==null)
    		return null;		
		
		int childrenSize = rootNode.children.size();
    	
    	if(childrenSize>0) {    		
    		tagsString=rootNode.name;
    		
    		for(Node child : rootNode.children) {
    			node = getClassNameBasedOnNodeChildrenString();
    			if(node!=null) {
    				result = node.processNode();
    			}
    		} 
    		
    	}
		System.out.println( "Root is : "+ rootNode.value);
		buildSimpleSentences (rootNode.value);
		
		for(int i=0; i<finalSimpleSentences.size();i++) 
			System.out.println(finalSimpleSentences.get(i));		
		
		return null;
	}
	
	public void helper(Node root) {
		
    	if(root==null)
    		return;
    	
    	int childrenSize = root.children.size();
    	
    	if(childrenSize>0) {
    		if(root.parent != null)
    			root.tagName = root.name;
    		tagsString=root.name;
    		
    		for(Node child : root.children) {
    			helper(child);
    		} 
    		
    		if(!root.value.equals("")) {
	    		
	    		tagsString = root.tagName;
	    		value = root.value;
	    		nodeMappingString = tagsString.trim();
	    		wordsInNodeMapping = value.trim();
	    		resultFromProcessedNode = processParentNode();
	    		
	    		//System.out.println( root.tagName +"    ###    "+ resultFromProcessedNode);
		    		if(resultFromProcessedNode!=null && root.parent != null && root.hasChildren()) {
		    			if(rootHasNonLeafChildren(root.parent)) {
		    				if(!root.parent.value.equals(""))
		    					root.parent.value = root.parent.value + "|" + resultFromProcessedNode;
		    	    		else
		    	    			root.parent.value = resultFromProcessedNode;
		    			} else {
		    				if(!root.parent.value.equals(""))
		    					root.parent.value = root.parent.value + "#" + resultFromProcessedNode;
		    				else
		    	    			root.parent.value = resultFromProcessedNode;
		    			}
	    				root.value = root.value + resultFromProcessedNode;
	    				root.tagName = root.tagName;
	    				root.parent.tagName = root.parent.tagName+ " " +root.name;
	    				
	    			}
		    		/*if(resultFromProcessedNode!=null && root.parent != null)
		    			System.out.println( root.tagName +"    ###    "+ root.parent.value);*/
	    		
	    		/*value = "";
	    		tagsString = "";
    		}
    	} else {
    		if(rootHasNonLeafChildren(root.parent)) {
				if(!root.parent.value.equals(""))
					root.parent.value = root.parent.value + "|" + root.value;
	    		else
	    			root.parent.value = root.value;
			} else {
				if(!root.parent.value.equals(""))
					root.parent.value = root.parent.value + "#" + root.value;
				else
	    			root.parent.value = root.value;
			}
    		/*if(!root.parent.value.equals(""))
    			root.parent.value = root.parent.value + "#" + root.value;
    		else
    			root.parent.value = root.value;*/
    		/*root.parent.tagName = root.parent.tagName+ " " +root.name;
    	}
    	
    	
    }
	
	private boolean rootHasNonLeafChildren(Node root) {
		if(root.hasChildren())
			for(Node child : root.children) {
				if(child.hasChildren())
					return true;
			}
		return false;
	}

	static Node lca = null;
	 
	private Node leastCommonAncestor(Node root, Node node1, Node node2)
	{
		if(node1.equals(root) || node2.equals(root))
			return root;
			
		int count = 0;
		lca = null;
		Node res = null;
		for(Node child : root.children)
		{
			res = leastCommonAncestor(child, node1, node2);
			if(res != null)
			{
				count++;
				lca = res;
			}
		}
		
		if(count == 2)
			return root;
			
		return lca;
	}
	
	public void buildSimpleSentences(String resultFromProcessedNode) {
		String[] textProcessing = rootNode.value.split("\\|");
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

	private String processParentNode() {
		String result = "";
		Tree node = getClassNameBasedOnNodeChildrenString();
		if(node!=null) {
			result = node.processNode();
		}
		return result;
	}

	private Tree getClassNameBasedOnNodeChildrenString() {
		FileHelper fileHelper = new FileHelper();
		String outputFilename = "\\cse5328-training-data.arff";
		String parentNode = nodeMappingString.substring(1, (nodeMappingString.indexOf("(", 2)-1));
		
		String className = fileHelper.getClassNameFromMapping(nodeMappingString, "result", outputFilename);
		if(!className.equals("")) {
			return getNode(parentNode, className);
		}
		return null;
	}
	
	public Tree getNode(String parentNode, String className) {
		
		Tree nodeObject = nodeObjectMap.get(className);
		if(nodeObject!=null) {
			return nodeObject;
		}
		try {
			if(!unclassifiedNodesList.contains(parentNode)) {
				parentNode = parentNode.toLowerCase();
			} else {
				parentNode = "unclassified";
			}
			nodeObject = (Tree) Class.
					forName("com.uta.testingtree.nodes."+parentNode+"nodes."+className).
					newInstance();
			nodeObjectMap.put(className, nodeObject);
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} 
		return nodeObject;
	}*/
}
