package scripts;

import java.io.*;
import java.util.ArrayList;
import java.util.Set;

public class GitClone {

	private String str = "hibernate-metamodelgen:org.hibernate.jpamodelgen.util:StringUtil:000000:String+name:boolean@boolean@is,Fully,Qualified";
	
	private ArrayList<String> listPj = new ArrayList<String>();
	private Set<String> setOfPj;
	
	/**
	 * Get git link from a line
	 * */
	public String getProjectGit(String inp) {
		String rs = "git clone https://trinhlk:rob1nho0d@github.com/";
		String pjName = "";
		String repoName = "";
		try {
			String[] nameRepo = inp.split(":", -1);
			repoName = nameRepo[0];
			
			String[] projectName = nameRepo[1].split("\\.", -1);
			pjName = projectName[1];
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		rs += pjName + "/" + repoName + ".git";
		
		//if (!checkExist(repoName, listPj)) {
		//	listPj.add(rs);
		//	System.out.println(rs);
		//}
			
		return rs;
	}
	
	/**
	 * check Existence
	 * */
	public boolean checkExist(String in, ArrayList<String> list) {
		
		for (int i = 0 ; i<list.size() ; i++) {
			if (list.get(i).equals(in))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Read file
	 * */
	public void readFile(String fileName) {
		BufferedReader br = null;
		FileReader fr = null;

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println(sCurrentLine);
				if (sCurrentLine.indexOf("<") >= 0) continue;
				
				if (!checkExist(getProjectGit(sCurrentLine), listPj)) {
					listPj.add(getProjectGit(sCurrentLine));
					System.out.println(getProjectGit(sCurrentLine));
				}
				
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	
	/**
	 * write File
	 * */
	public void writeFile(String fileName) {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			System.out.println("Starting to write into file\\n");
			//String content = "This is the content to write into file\n";

			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			
			for (String s : listPj) {
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
	
	
	public void printListGits() {
		for (String s : listPj) {
			System.out.println(s);
		}
	}
	
	public static void main(String[] args) {
		GitClone gc = new GitClone();
		gc.readFile("TrainingMethodInfo.txt");
		gc.writeFile("cloning.sh");
		gc.printListGits();
		//System.out.println(gc.getProjectGit("cloudstack:com.cloud.consoleproxy.vnc.packet.server:ServerCutText:getContent:null:String@String@get,Content\n"));
	}
	
}
