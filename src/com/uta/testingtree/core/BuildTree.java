package com.uta.testingtree.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class BuildTree {
	
	static Map<String, Node> nodeObjectMap = new HashMap<>();
	static Node presentTag = null;
	protected static Stack<String> stack=new Stack<String>();
	
	public Node processTextToTree(String fileName) {
		
		String line = "";
		StringTokenizer stringTokens;
		BufferedReader br = null;

		Node RootTag = null;
		Node tag = null;
		
		try{
			String token = "";
			String tempToken = "";
			String prevToken = "(ROOT";
			br = new BufferedReader(new FileReader(new File(fileName)));
			while ((line=br.readLine())!=null){
		        stringTokens=new StringTokenizer(line);
		        while (stringTokens.hasMoreTokens()){
		            token=stringTokens.nextToken();
		            tempToken = token;
		            String top="";
		            if (token.indexOf(")")<0) {
		            	if (token.indexOf("(")>=0) {
		            		tag = new Node(token, "", new ArrayList<Node>());
		            		if(token.contains("NNP")) {
		            			System.out.println("abc is abc");
		            		}
		            		if(!tag.name.contains("ROOT")) {
		            			presentTag.children.add(tag);
		            			tag.parent = presentTag;
		            		} else {
		            			RootTag = tag;
		            			tag.parent = null;
		            		}
		            		presentTag = tag;
		            	} 
		            	if(!token.trim().equals(""))
		            		stack.push(token);
		                prevToken = tempToken;
		                
		            	continue; // with next input token
		            } else if(prevToken.indexOf("(")>=0 && token.indexOf(")")>=0) {
		            	
		            	int countClosedBrace = getClosedBraceCount(token);
		            	if(countClosedBrace>1) {
		            		top=stack.pop();
			                if (top.indexOf("(")>=0) {
			                	top = top.substring(1); // remove ( from top and push it for children
			                	if(!token.trim().equals(""))
			                		stack.push(top);
			                	
			                	presentTag.value = token.substring(0, token.indexOf(")")).trim();
				            	//presentTag.children.add(tag);
				            	presentTag = presentTag.parent;
				            	token = token.substring(token.indexOf(")") + 1).trim();
			                } 			 
			            	while (!stack.isEmpty()){ // loop though to get a new class name
				                top=stack.pop();
				                if (top.indexOf("(")>=0) { // In this case new class name will be generated
				                	top = top.substring(1);				                	
				                	if(!top.trim().equals(""))
				                		stack.push(top);
				                	if(token.contains("))")) {
				                		if(!token.substring(token.indexOf(")") + 1).trim().equals(""))	
				                			stack.push(token.substring(token.indexOf(")") + 1));
				                		
				                	}
				                	presentTag = presentTag.parent;
				                    break;
				                }
				            }
	            			String lastElementOfStack = stack.pop();
	            			while(lastElementOfStack.indexOf(")")>=0) {
	            				stack.push(lastElementOfStack);
	            				resolveAllPossibleParentChildrenNodes(); //resolve parents to the possible level
	            				lastElementOfStack = stack.pop();
	            			} 
	            			if(lastElementOfStack.indexOf(")")<0){
	            				stack.push(lastElementOfStack);
	            				//stack.push("|");
	            			}
		            	} else if(countClosedBrace==1) { //not end of children
		            		top=stack.pop();
		            		
			                if (top.indexOf("(")>=0) {
			                	token = token.substring(0, token.indexOf(")")).trim();
			                	presentTag.value = token;
			                	presentTag = presentTag.parent;
				                //presentTag = tag;
			                	top = top.substring(1); // remove ( from top and push it for children
			                	if(!top.trim().equals(""))
			                		stack.push(top);
			                }
			                
		            		prevToken = tempToken;
			                continue;
		            	}
		            }		            
		            prevToken = tempToken;
		       	}
		      }
		} catch (Exception e) {
			  e.printStackTrace();
		  } finally {
			  if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		  }
		return RootTag;
	}
	
	public void helper(Node root, List<String> list)
    {
    	if(root==null)
    		return;
    	list.add(root.value);
    	if(root.children!=null)
    	{
    		for(Node child : root.children)
    		{
    			helper(child, list);
    		}
    	}
    }

	static int getClosedBraceCount(String string) { 

        int count = 0;
        for (int i = 0; i < string.length(); i++) {  
			if (string.charAt(i) == ')')  
                count++;                  
        }
		return count; 
    } 
	
	private void resolveAllPossibleParentChildrenNodes() {
		//System.out.println("stack is "+stack);
		String top="";
		String tempTop="";
		int countClosedBrace = 0;
		boolean startAgain = true;
		
		while (!stack.isEmpty()){
			
            if(startAgain && !stackStillHasClosingBrace()) { //If it is a new parent and children nodes and stack is invalid
            	return; // stack invalid
            } else { //stack is still valid
            	top=stack.pop();
            	if (startAgain) { //If it is new parent and children nodes
            		startAgain = false;
		            if(top.indexOf(")")>=0) {
		            	countClosedBrace = getClosedBraceCount(top);
		            	if(countClosedBrace>1) {
		            		tempTop = top.substring(1);
		            		continue;
		            	} else if(countClosedBrace==1) {
		            		tempTop = "";
		            		continue;
		            	} 
		            } 
            	} else {
            		if (top.indexOf("(")>=0) {
		            	startAgain = true;
	            		
		            	top = top.substring(1); 
		            	stack.push(top);
		            	
		            	if(!tempTop.equals("")) {
		        			stack.push(tempTop);
		        		}
		            	presentTag = presentTag.parent;
            		}
            	} 
            }
        }
		//System.out.println("End of method");	
	}
	
	private boolean stackStillHasClosingBrace() {
		boolean hasClosingBrace = false;
		Iterator<String> value = stack.iterator(); 
		while (value.hasNext()){
			if(value.next().indexOf(")")>=0) {
				hasClosingBrace = true;
				break;
			}
		}
		return hasClosingBrace;
	}

}
