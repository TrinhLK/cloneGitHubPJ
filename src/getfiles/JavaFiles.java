package getfiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class JavaFiles {
	public final String TYPE = ".java";
	public final File folder = new File(System.getProperty("user.home") + "/Desktop/Data2");
	private ArrayList<String> listJavaFiles = new ArrayList<String>();
	
	
	/**
	 * get all files from folders and sub-folders 
	 * */
	public void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	//if (fileEntry.getName().indexOf(TYPE) > 0) {
	        	//	System.out.println(fileEntry.getAbsolutePath());
	        		listJavaFiles.add(fileEntry.getAbsolutePath());
	        		
	        	//}79024
	        }
	    }
	    System.out.println(listJavaFiles.size());
	    System.out.println("Finished.");
	}

	/**
	 * write File
	 * */
	public void writeFile(String fileName) {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			System.out.println("Starting to write into file" + fileName + "\n");
			//String content = "This is the content to write into file\n";

			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			Collections.sort(listJavaFiles);
			for (String s : listJavaFiles) {
				bw.write(s + "\n");
			}
			//bw.write(content);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}
	
	public static void main(String[] args) {
		JavaFiles jf = new JavaFiles();
		jf.listFilesForFolder(jf.folder);
		jf.writeFile("javaFilesNew.txt");
	}
}
