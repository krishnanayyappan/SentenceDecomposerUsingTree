package com.uta.testingtree.core;

import java.util.List;

public class Tag {

	public String name; //(NP
	public String tagName; // (NP (NN (NN (CC (NN (NNP
	public String value; //Text value
	public Tag parent; 
	public List<Tag> children;
	
	public Tag() { }
	
	public Tag(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public Tag(String name, String value, List<Tag> children) {
		this.name = name;
		this.value = value;
		this.children = children;
	}
	
	public Tag(String name, String value, List<Tag> children, Tag parent) {
		this.name = name;
		this.value = value;
		this.children = children;
		this.parent = parent;
	}
	
	public boolean hasChildren() {
		return children.size()>0 ;
	}
	
	public boolean removeChild(Tag child) {
		return children.remove(child) ;
	}
}
