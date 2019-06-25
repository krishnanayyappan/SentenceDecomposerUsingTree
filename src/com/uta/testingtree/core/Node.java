package com.uta.testingtree.core;

import java.util.List;

public abstract class Node {

	
	public String name; //(NP
	public String tagName; // (NP (NN (NN (CC (NN (NNP
	public String value; //Text value
	public Node parent; 
	public List<Node> children;
	
	public Node() { }
	
	public Node(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public Node(String name, String value, List<Node> children) {
		this.name = name;
		this.value = value;
		this.children = children;
	}
	
	public Node(String name, String value, List<Node> children, Node parent) {
		this.name = name;
		this.value = value;
		this.children = children;
		this.parent = parent;
	}
	
	public boolean hasChildren() {
		return children.size()>0 ;
	}
	
	public boolean removeChild(Node child) {
		return children.remove(child) ;
	}
}
