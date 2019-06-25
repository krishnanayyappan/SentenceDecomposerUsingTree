package com.uta.testingtree.nodes.simplenodes;

import com.uta.testingtree.core.Node;
import com.uta.testingtree.core.Tree;

public class SimpleComa extends Tree {

	public SimpleComa() {
		super();
	}

	@Override
	public String processNode(Node currentNode) {
		
		int childrenSize = currentNode.children.size();
		String tagsString = currentNode.name;
		String result = "";
		Tree subTree = null;
		
		if(childrenSize==0) {
			return currentNode.value;
		}
		
		for(Node child : currentNode.children) {
			subTree = getSimpleClassNameForChild(child);
			if(subTree!=null) {
				result = result + " " +subTree.processNode(child);
			}
			tagsString = tagsString + " " +child.name.trim();
		} 
		
		nodeMappingString = tagsString.trim();
		wordsInNodeMapping = result.trim();
		
		subTree = getClassNameBasedOnNodeChildrenString();
		if(subTree!=null) {
			return subTree.processNode(currentNode);
		}
		
		return null;
	}
}
