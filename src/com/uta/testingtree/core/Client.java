package com.uta.testingtree.core;

import java.io.File;
import java.io.FileNotFoundException;

public class Client {
	
	ClassLoader classLoader = getClass().getClassLoader();	
	File folder = new File(classLoader.getResource("test").getFile());

	public static void main(String[] args){
		Client client = new Client();
		String fileName = getFileToProcess(client.folder);
		BuildTree buildTree = new BuildTree();
		Node RootNode = buildTree.processTextToTree(fileName);
		Tree root = new Root();
		root.processNode(RootNode);
	 }
	 
	 public static String getFileToProcess(File folder){
		 String fileName = "";
		if(folder == null) {
			try {
				throw new FileNotFoundException("Folder " + folder + " does not exist.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		for (final File file : folder.listFiles()) {
			if (file.isDirectory()) {
				getFileToProcess(file);
			} else {		
				try{
					fileName = getFileWithRelativePath(folder, file);			
				  } catch (Exception e) {
					  e.printStackTrace();
				  }
			}
		}
		return fileName;
	 }
	 
	 private static String getFileWithRelativePath(final File folder, final File file) {
			return folder + "\\" + file.getName();
		}

}
