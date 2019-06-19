package com.uta.classmappinggenerator.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileHelper extends BaseHelper {
	
	private static final Logger log = LoggerFactory.getLogger(FileHelper.class);
	
	public void saveToFile(String classNameMapping, String baseDir, String fileName) {

		verifyFileNameHasValue(fileName, "File name is null");
		FileWriter file = makeAbsoluteFileWriter(baseDir, fileName);
		
		try {
			if(!classNameMappingExistsInFile(classNameMapping, baseDir, fileName)) 
				file.append("\n"+classNameMapping); 
			 
            // closing writer connection 
            file.close(); 
		} catch (IOException|UnsupportedCharsetException e) {
			logErrorAndThrowException("Couldn't save input text to file: ", e);
		}
	}	
	
	public boolean classNameMappingExistsInFile(String classNameMapping, String baseDir, String fileName) {

		String newClassNameToInsert = classNameMapping.substring(0, classNameMapping.indexOf("'", 2)+1);
		String classNameFromLine;
		BufferedReader reader;
		try {
			reader = new BufferedReader(makeAbsoluteFileReader(baseDir, fileName));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				
				classNameFromLine = line.substring(0, line.indexOf("'", 2)+1);
				if(classNameFromLine.equals(newClassNameToInsert)) {
					reader.close();
					return true;
				}
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException|UnsupportedCharsetException e) {
			logErrorAndThrowException("Error occured while reading the file : ", e);
		}
		return false;
	}

	public String getClassNameFromMapping(String classNameMapping, String baseDir, String fileName) {

		//String newClassNameToInsert = classNameMapping.substring(0, classNameMapping.indexOf("'", 2)+1);
		String classNameFromLine;
		BufferedReader reader;
		try {
			reader = new BufferedReader(makeAbsoluteFileReader(baseDir, fileName));
			String line = reader.readLine();
			while (line != null) {
				if(line.contains("'")) {
					classNameFromLine = line.substring(1, line.indexOf("'", 2));
					if(classNameFromLine.trim().equals(classNameMapping.trim())) {
						reader.close();
						return line.substring(line.indexOf("'", 2)+3);
					}
				}
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException|UnsupportedCharsetException e) {
			logErrorAndThrowException("Error occured while reading the file : ", e);
		}
		return "";
	}
	public void verifyFileExists(File file) {
		
		if (! file.isFile()) {
			logErrorAndThrowException("File does not exist: " + file.getPath()); // + " (Absolutt sti: " + file.getAbsolutePath() + ")");
		}
	}

	public void verifyFileNameHasValue(String fileName, String errorMsg) {
		
		if (StringUtils.isBlank(fileName)) {
			logErrorAndThrowException(errorMsg);
		}
	}
	
	public FileWriter makeAbsoluteFileWriter(String baseDir, String fileName) {
		
		File absBaseDir = makeAbsoluteFile(baseDir);
		File file = new File(absBaseDir+fileName);
		
		/*try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		FileWriter outputfile = null;
		try {
			outputfile = new FileWriter(file, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return outputfile;
		
	}
	
	public FileReader makeAbsoluteFileReader(String baseDir, String fileName) {
		
		File absBaseDir = makeAbsoluteFile(baseDir);
		File file = new File(absBaseDir+fileName);
		
		/*try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return fileReader;
		
	}
	
	public File makeAbsoluteFile(String baseDir, String fileName) {
		
		File absBaseDir = makeAbsoluteFile(baseDir);
		File file = new File(absBaseDir+fileName);
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
		
	}

	public File makeAbsoluteFile(String folderName ) {
		
		if (folderName == null) {
			folderName = "";
		}
		
		Path currentRelativePath = Paths.get("");
		String absolutePath = currentRelativePath.toAbsolutePath().toString();
		
		final File outputRootDir = new File(absolutePath+"/resources/"+folderName);
		return outputRootDir;
	}

}
